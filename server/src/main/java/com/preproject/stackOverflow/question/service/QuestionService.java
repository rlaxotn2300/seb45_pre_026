package com.preproject.stackOverflow.question.service;


import com.preproject.stackOverflow.exception.BusinessLogicException;
import com.preproject.stackOverflow.exception.ExceptionCode;
import com.preproject.stackOverflow.question.entity.Question;
import com.preproject.stackOverflow.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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


    public Question createQeustion(Question question) {
//        로그인한 유저만 작성할 수 있게 차후 수정 필요
//        question.setUser(userService.getLoginUser());

        String tag = question.getTag();
        List<String> tagList = new ArrayList<>(Arrays.asList(tag.split(", ")));
        question.setTag(question.getTag());
        question.setQuestionStatus(Question.QuestionStatus.QUESTION_ASKED);
        question.setCreatedAt(question.getCreatedAt());

        return questionRepository.save(question);

    }

    public Question updateQuestion(Question question, long questionId) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());
//        로그인 유저와 질문 작성 유저와 비교해서 같다면 수정, 아니라면 접근 금지 예외 발생
//        if(findQuestion.getMember().getMemberId() != memberService.getLoginMember().getMemberId()) {
//            throw new BusinessLogicException(ExceptionCode.ACCESS_FORBIDDEN);

        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContent())
                .ifPresent(content -> findQuestion.setContent(content));

        String tag = question.getTag();
        List<String> tagList = new ArrayList<>(Arrays.asList(tag.split(", ")));
        question.setTagList(tagList);
        findQuestion.setQuestionStatus(Question.QuestionStatus.QUESTION_MODIFIED);
        findQuestion.setModifiedAt(question.getModifiedAt());


        return questionRepository.save(findQuestion);

    }



    public Question findQuestion(long questionId){
        return findVerifiedQuestion(questionId);
    }


    public Page<Question> findQuestions(int page, int size, String sort){
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by(sort).descending())); //날짜순으로 변경해야 함
    }


    public Question upVote(Question question, long questionId){
        Question findQuestion = findVerifiedQuestion(questionId);
        findQuestion.setVote(question.getVote());
        return questionRepository.save(findQuestion);
    }


    public Question downVote(Question question, long questionId){
        Question findQuestion = findVerifiedQuestion(questionId);
        findQuestion.setVote(findQuestion.getVote());

        return questionRepository.save(findQuestion);
    }


    //질문 삭제시 태그들도 같이 삭제 됨
    public void deleteQuestion(long questionId){
        Optional<Question> findQuestion = questionRepository.findById(questionId);
        if(findQuestion.isPresent()){
            questionRepository.deleteById(questionId);
            questionRepository.deleteByTagList(questionId);
        }
    }


    //태그 검색
    public Page<Question> findAllByTag(String tag, int page, int size){
        return questionRepository.findAllByTagContaining(tag,
                PageRequest.of(page, size, Sort.by("vote").descending())); //질문에 대한 투표순으로 정렬
    }



    public Question findVerifiedQuestion(long questionId){
        Optional<Question> optional = questionRepository.findById(questionId);
        return optional.orElseThrow(()->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }






}

