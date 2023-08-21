package com.preproject.stackOverflow.question.service;


import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.answer.service.AnswerService;
import com.preproject.stackOverflow.auth.userdetails.CustomersDetailsService;
import com.preproject.stackOverflow.exception.BusinessLogicException;
import com.preproject.stackOverflow.exception.ExceptionCode;
import com.preproject.stackOverflow.member.entity.Member;
import com.preproject.stackOverflow.member.repository.MemberRepository;
import com.preproject.stackOverflow.member.service.MemberService;
import com.preproject.stackOverflow.question.entity.Question;
import com.preproject.stackOverflow.question.repository.QuestionRepository;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private MemberRepository memberRepository;
    private MemberService memberService;

    public QuestionService(QuestionRepository questionRepository,
                           MemberRepository memberRepository,
                           MemberService memberService) {
        this.questionRepository = questionRepository;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }


    //질문등록
    public Long createQuestion(Question question, Long memberId) {                      //8.21 ok
        //Question findQuestion = findVerifiedQuestion(question.getQuestionId());
        //Member questionAuthor = findQuestion.getMember();
        Member loggedInMember = memberService.findVerifiedMember(memberId);
        question.setMember(loggedInMember);

//        if (questionAuthor.getMemberId() != loggedInMember.getMemberId()) {
//            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
//        }

        String tag = question.getTag();
        List<String> tagList = new ArrayList<>(Arrays.asList(tag.split("\\s*,\\s*")));
        question.setTags(tagList);
        question.setQuestionStatus(Question.QuestionStatus.QUESTION_ASKED);
        question.setCreatedAt(question.getCreatedAt());

        return questionRepository.save(question).getQuestionId();

    }




    //질문수정

    public Question patchQuestion(Question question, long memberId) {           //8.21 ok
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());
//        Member questionAuthor = findQuestion.getMember();
//        Member loggedInMember = memberService.findVerifiedMember(memberId);
//
//        if (questionAuthor.getMemberId() != loggedInMember.getMemberId()) {
//            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
//        }
//
        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContent())
                .ifPresent(content -> findQuestion.setContent(content));
        Optional.ofNullable(question.getTag())
                .ifPresent(tag -> findQuestion.setTag(tag));


        String tag = question.getTag();
        List<String> tags = new ArrayList<>(Arrays.asList(tag.split(", ")));
//
//        List<String> tagsToRemove = new ArrayList<>(findQuestion.getTags());
//        tagsToRemove.removeAll(tags);
//
//        findQuestion.getTags().removeAll(tagsToRemove);
//        findQuestion.setTags(tags);

        question.setTags(tags);
        findQuestion.setQuestionStatus(Question.QuestionStatus.QUESTION_MODIFIED);
        findQuestion.setModifiedAt(question.getModifiedAt());

        return questionRepository.save(findQuestion);
    }


    //질문1개 조회
    public Question findQuestion(long questionId) {
        return findVerifiedQuestion(questionId);
    }


    //질문 전체 조회
    @Transactional(readOnly = true)
    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt")));
    }



    // 추천
    public void upVote(long questionId, long memberId) {

        memberService.findMember(memberId);
        Question question = findVerifiedQuestion(questionId);
        QuestionService.VoteStatus voteStatus = getMemberVoteStatus(question, memberId);
        long voteCount = question.getVote();

        if (voteStatus == QuestionService.VoteStatus.NONE) { //투표가 처음이면 +1카운트
            question.upVotedMemberId.add(memberId);
            voteCount++;
        } else if (voteStatus == (QuestionService.VoteStatus.ALREADY_UP_VOTED)){
            throw new BusinessLogicException(ExceptionCode.ALREADY_UP_VOTED);
        } else if (voteStatus == (QuestionService.VoteStatus.ALREADY_DOWN_VOTED)){
            question.downVotedMemberId.remove(memberId);
            voteCount++;
        }
        question.setVote(voteCount);

        questionRepository.save(question);
    }


    //질문 비추천
    public void downVote(long questionId, long memberId) {

        memberService.findMember(memberId);

        Question question = findVerifiedQuestion(questionId);
        QuestionService.VoteStatus voteStatus = getMemberVoteStatus(question, memberId);
        long voteCount = question.getVote();

        if (voteStatus == QuestionService.VoteStatus.NONE) { //투표가 처음이면 +1카운트
            question.downVotedMemberId.add(memberId);
            voteCount--;

        } else if (voteStatus.equals(QuestionService.VoteStatus.ALREADY_UP_VOTED)){
            question.upVotedMemberId.remove(memberId);
            voteCount--;

        } else if (voteStatus == VoteStatus.ALREADY_DOWN_VOTED){
            throw new BusinessLogicException(ExceptionCode.ALREADY_DOWN_VOTED);
        }
        question.setVote(voteCount);


        questionRepository.save(question);

    }



    //질문 삭제(태그도 함께 삭제됨)
    public void deleteQuestion(String email, long questionId) {

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)
        );

        Question findQuestion = questionRepository.findById(questionId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND)
        );

        if(findQuestion.getMember().getMemberId() != member.getMemberId()){
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }

        questionRepository.deleteById(questionId);
    }


    //태그 검색
    public Page<Question> findAllByTags(String tag, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return questionRepository.findByTagContaining(tag, pageable);
    }



    //질문존재여부검증
    public Question findVerifiedQuestion(long questionId) {
        Optional<Question> optional = questionRepository.findById(questionId);
        return optional.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }


    //투표여부검증
    public QuestionService.VoteStatus getMemberVoteStatus(Question question, Long memberId) {
        if (question.getUpVotedMemberId().contains(memberId)) {
            return QuestionService.VoteStatus.ALREADY_UP_VOTED;
        } else if (question.getDownVotedMemberId().contains(memberId)) {
            return QuestionService.VoteStatus.ALREADY_DOWN_VOTED;
        } else {
            return VoteStatus.NONE;
        }
    }



    public long getVote(long questionId) {

        long voteCount = findQuestion(questionId).getVote();
        return voteCount;
    }


    public enum VoteStatus {
        ALREADY_UP_VOTED(1, "already upVoted"),
        NONE(2, "none"),
        ALREADY_DOWN_VOTED(3, "already downVoted");

        @Getter
        private int status;

        @Getter
        private String message;


        VoteStatus(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }
}


/*===================================================================================


    public Page<Question> findAllByTag(int page, int size, String tag) {
        //만약 search가 앞 뒤로 [ ]로 감싸져서 온다면 태그 검색
        if(tag.charAt(0) =='[' && tag.charAt(tag.length()-1) ==']') {
            return questionRepository.findByTagContainingOrderByTagDesc(
                    PageRequest.of(page, size, Sort.by(tag).descending()),
                    tag.substring(1, tag.length()-1));
        }
        return questionRepository.findByTagContainingOrderByTagDesc(PageRequest.of(page, size, Sort.by("tag").descending()), tag);
    }



             만약에 질문 상세 조회 필요하게 될때 참고
        @Transactional(readOnly = true)
    public ResponseEntity detailFindQuestion(long questionId) {
        QuestionDto.Response question = repository.findById(questionId)
        .map( QuestionDto.Response::result).orElseThrow(() ->
         new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return new ResponseEntity<>(question, HttpStatus.OK);
    }




 */