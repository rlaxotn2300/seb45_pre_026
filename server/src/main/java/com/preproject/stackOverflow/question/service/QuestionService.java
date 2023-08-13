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


    public Question createQuestion(Question question) {
//        로그인한 유저만 작성할 수 있게 차후 수정 필요
//        question.setUser(userService.getLoginUser());

        String tag = question.getTag();
        List<String> tagList = new ArrayList<>(Arrays.asList(tag.split("\\s*,\\s*")));
        question.setTagList(tagList);
        question.setQuestionStatus(Question.QuestionStatus.QUESTION_ASKED);
        question.setCreatedAt(question.getCreatedAt());

        return questionRepository.save(question);

    }

    public Question patchQuestion(Question question, long questionId) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());
//        로그인 유저와 질문 작성 유저와 비교해서 같다면 수정, 아니라면 접근 금지 예외 발생
//        if(findQuestion.getMember().getMemberId() != memberService.getLoginMember().getMemberId()) {
//            throw new BusinessLogicException(ExceptionCode.ACCESS_FORBIDDEN);

        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContent())
                .ifPresent(content -> findQuestion.setContent(content));
        Optional.ofNullable(question.getTag())
                .ifPresent(tag -> findQuestion.setTag(tag));

//        String tag = question.getTag();
//        List<String> tagList = new ArrayList<>(Arrays.asList(tag.split(", ")));
//        question.setTagList(tagList);
        findQuestion.setQuestionStatus(Question.QuestionStatus.QUESTION_MODIFIED);
        findQuestion.setModifiedAt(question.getModifiedAt());


        return questionRepository.save(findQuestion);

    }



    public Question findQuestion(long questionId){
        return findVerifiedQuestion(questionId);
    }


    //날짜순으로 정렬

//    @Transactional(readOnly = true)
//    public Page<Question> findQuestions(int page, int size){
//        return questionRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));
//    }


//    public Question upVOTE(Question question, long questionId){
//        Question findQuestion = findVerifiedQuestion(questionId);
//        findQuestion.setVOTE(question.getVOTE());
//        return questionRepository.save(findQuestion);
//    }


    /*
    public Question upVOTE(long questionId, User user) {
        Question findQuestion = findVerifiedQuestion(questionId);
        findVerifiedQuestionVote(questionId, member);
        //비추천 누른 질문에 전체 추천 수 카운트
        findQuestion.setVOTE(findQuestion.getVOTE() + 1);
        return questionRepository.save(findQuestion);
    }
*/



//    public Question downVOTE(Question question, long questionId){
//        Question findQuestion = findVerifiedQuestion(questionId);
//        findQuestion.setVOTE(question.getVOTE());
//
//        return questionRepository.save(findQuestion);
//    }

/*
    public Question downVOTE(long questionId, User user) {
        Question findQuestion = findVerifiedQuestion(questionId);
        findVerifiedQuestionVote(questionId, user);
        //비추천 누른 질문에 전체 추천 수 카운트
        findQuestion.setVOTE(findQuestion.getVOTE() - 1);
        return questionRepository.save(findQuestion);
    }

 */


    public Page<Question> findQuestions(int page, int size){
        return questionRepository.findAll(PageRequest.of(page, size)); //날짜순으로 변경해야 함
    }





    //질문 삭제시 태그들도 같이 삭제 됨
    public void deleteQuestion(long questionId){
        Optional<Question> findQuestion = questionRepository.findById(questionId);
        if(findQuestion.isPresent()){
            questionRepository.deleteById(questionId);

        }
    }


    //태그 검색
    public Page<Question> findAllByTag(String tag, int page, int size){
        return questionRepository.findByTagContainingOrderByTagDesc(
                PageRequest.of(page, size, Sort.by("tag").descending()), tag); //질문에 대한 투표순으로 정렬
    }


//    public Page<Question> findAllByTag(int page, int size, String tag) {
//        //만약 search가 앞 뒤로 [ ]로 감싸져서 온다면 태그 검색
//        if(tag.charAt(0) =='[' && tag.charAt(tag.length()-1) ==']') {
//            return questionRepository.findByTagContainingOrderByTagDesc(
//                    PageRequest.of(page, size, Sort.by(tag).descending()),
//                    tag.substring(1, tag.length()-1));
//        }
//        return questionRepository.findByTagContainingOrderByTagDesc(PageRequest.of(page, size, Sort.by("tag").descending()), tag);
//    }



    public Question findVerifiedQuestion(long questionId){
        Optional<Question> optional = questionRepository.findById(questionId);
        return optional.orElseThrow(()->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }






}




    /* 만약에 질문 상세 조회 필요하게 될때 참고
        @Transactional(readOnly = true)
    public ResponseEntity detailFindQuestion(long questionId) {
        QuestionDto.Response question = repository.findById(questionId)
        .map( QuestionDto.Response::result).orElseThrow(() ->
         new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return new ResponseEntity<>(question, HttpStatus.OK);
    }
     */
