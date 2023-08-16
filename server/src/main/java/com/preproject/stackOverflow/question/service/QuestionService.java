package com.preproject.stackOverflow.question.service;


import com.preproject.stackOverflow.exception.BusinessLogicException;
import com.preproject.stackOverflow.exception.ExceptionCode;
import com.preproject.stackOverflow.question.entity.Question;
import com.preproject.stackOverflow.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    //질문등록
    public Question createQuestion(Question question) {
//        로그인한 유저만 작성할 수 있게 차후 수정 필요
//        question.setUser(userService.getLoginUser());
        String tag = question.getTag();
        List<String> tagList = new ArrayList<>(Arrays.asList(tag.split("\\s*,\\s*")));
        question.setTags(tagList);
        question.setQuestionStatus(Question.QuestionStatus.QUESTION_ASKED);
        question.setCreatedAt(question.getCreatedAt());

        return questionRepository.save(question);
    }



    //질문수정
    public Question patchQuestion(Question question, long questionId) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());
//        로그인 유저와 질문 작성 유저와 비교해서 같다면 수정, 아니라면 접근 금지 예외 발생
        //if(findQuestion.getMember().getMemberId() != memberService.getLoginMember().getMemberId()) {
            //throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);

        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContent())
                .ifPresent(content -> findQuestion.setContent(content));
        Optional.ofNullable(question.getTag())
                .ifPresent(tag -> findQuestion.setTag(tag));

        String tag = question.getTag();
        List<String> tagList = new ArrayList<>(Arrays.asList(tag.split(", ")));

        List<String> tagsToRemove = new ArrayList<>(findQuestion.getTags());
        tagsToRemove.removeAll(tagList);

        findQuestion.getTags().removeAll(tagsToRemove);
        findQuestion.setTags(tagList);

        //question.setTags(tagList);
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



    //질문추천
    public Question upVote(long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        verifiedVote(questionId); //중복투표방지
        findQuestion.setVote(findQuestion.getVote() + 1);
        return questionRepository.save(findQuestion);
    }


    //질문 비추천
    public Question downVote(long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        verifiedVote(questionId);  //중복투표방지
        findQuestion.setVote(findQuestion.getVote() - 1);
        return questionRepository.save(findQuestion);
    }



    //질문 삭제(태그도 함께 삭제됨)
    public void deleteQuestion(long questionId) {
        Optional<Question> findQuestion = questionRepository.findById(questionId);
        if (findQuestion.isPresent()) {
            questionRepository.deleteById(questionId);
        }
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
    public Question verifiedVote(long questionId) {
        Question question = findVerifiedQuestion(questionId);

        if (question.getVote() == 1) {
            throw new BusinessLogicException(ExceptionCode.ALREADY_UP_VOTED);
        } else if (question.getVote() == -1) {
            throw new BusinessLogicException(ExceptionCode.ALREADY_DOWN_VOTED);
        }

        return question;
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
