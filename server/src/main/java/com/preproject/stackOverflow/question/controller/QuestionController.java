package com.preproject.stackOverflow.question.controller;


import com.preproject.stackOverflow.dto.MultiResponseDto;
import com.preproject.stackOverflow.dto.SingleResponseDto;
import com.preproject.stackOverflow.question.dto.QuestionDto;
import com.preproject.stackOverflow.question.entity.Question;
import com.preproject.stackOverflow.question.mapper.QuestionMapper;
import com.preproject.stackOverflow.question.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;


    public QuestionController(QuestionService questionService, QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }


    @Secured("ROLE_USER")
    @PostMapping("/ask")
    public ResponseEntity postQuestion(QuestionDto.Post questionPost){

//        로그인한 유저만 글 등록하는 로직 추가 예정
       Question question = questionService.createQeustion(mapper.questionPostDtoToQuestion(questionPost));
        QuestionDto.Response response = mapper.questionToQuestionResponseDto(question);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }



    @PatchMapping("{question-id}")
    public ResponseEntity patchQuestion(QuestionDto.Patch patchDto, long questionId){

        //작성자만 수정할 수 있는 로직 추가 예정
        Question question = questionService.updateQuestion(mapper.questionPatchDtoToQuestion(patchDto), questionId);
        QuestionDto.Response respone = mapper.questionToQuestionResponseDto(question);

        return new ResponseEntity(new SingleResponseDto<>(respone), HttpStatus.OK);

    }

    //질문 1개 조회
    @GetMapping("{question-id}")
    public ResponseEntity findQeustion(long questionId){
        Question question = questionService.findQuestion(questionId);
        QuestionDto.Response response = mapper.questionToQuestionResponseDto(question);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }


    //모든 질문 조회
    @GetMapping("/")
    public ResponseEntity findQuestions(int page, int size, String sort){
        Page<Question> pageQuestion = questionService.findQuestions(page - 1, size, sort);
        List<Question> list = pageQuestion.getContent();
        List<QuestionDto.Response> responses = mapper.questionsToQuestionResponseDtos(list);

        return new ResponseEntity(new MultiResponseDto<>(responses, pageQuestion), HttpStatus.OK);

    }


    @PostMapping("{question-id}")
    public ResponseEntity upVote(Question question, long questionId){
        Question findQuestion = questionService.findQuestion(questionId);
        questionService.upVote(findQuestion, questionId);

        return new ResponseEntity(HttpStatus.OK);
    }



    @PostMapping("{question-id}")
    public ResponseEntity downVote(Question question, long questionId){
        Question findQuestion = questionService.findQuestion(questionId);
        questionService.downVote(findQuestion, questionId);

        return new ResponseEntity(HttpStatus.OK);
    }



    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("{question-id}")
    public ResponseEntity deleteQuestion(long questionId){
        //작성자만랑 관리자만 삭제 할 수 있는 기능 추가해야 함
//        if (!question.getMember().getMemberId().equals(member.getMemberId()) && !member.getEmail().equals("admin@gmail.com")) {
//            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);

         questionService.deleteQuestion(questionId);

         return new ResponseEntity(HttpStatus.NO_CONTENT);
    }




    //태그 검색
    @GetMapping("/search")
    public ResponseEntity getQuestionByTag(String tag, int page, int size){
        Page<Question> pageQuestions = questionService.findAllByTag(tag, page -1, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity(
                new MultiResponseDto<>(mapper.questionsToQuestionResponseDtos(questions), pageQuestions),
                        HttpStatus.OK);
    }

}
