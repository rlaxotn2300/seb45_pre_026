package com.preproject.stackOverflow.question.controller;


import com.preproject.stackOverflow.dto.MultiResponseDto;
import com.preproject.stackOverflow.dto.PageInfo;
import com.preproject.stackOverflow.dto.SingleResponseDto;
import com.preproject.stackOverflow.question.dto.QuestionDto;
import com.preproject.stackOverflow.question.entity.Question;
import com.preproject.stackOverflow.question.mapper.QuestionMapper;
import com.preproject.stackOverflow.question.service.QuestionService;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;

import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import java.util.Arrays;

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

    //@Secured("ROLE_USER")
    @PostMapping("/ask") //postman ok : 태그 여러개 해결함
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPost) {

//        로그인한 유저만 글 등록하는 로직 추가 예정
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPost));

        QuestionDto.Response response = mapper.questionToQuestionResponseDto(question);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }


    // @Secured("ROLE_USER")

    @PatchMapping("{question-id}") //postman  ok : 태그여러개가 해결 -> 필드에 questionId 입력해주어야 함

    public ResponseEntity patchQuestion(@RequestBody QuestionDto.Patch patchDto,
                                        @PathVariable("question-id")
                                        @Positive long questionId) {

        //작성자만 수정할 수 있는 로직 추가 예정
        Question response = questionService.patchQuestion(mapper.questionPatchDtoToQuestion(patchDto), questionId);
        QuestionDto.Response question = mapper.questionToQuestionResponseDto(response);

        return new ResponseEntity(new SingleResponseDto<>(question), HttpStatus.OK);

    }


    //질문 1개 조회
    @GetMapping("{question-id}") //postman ok
    public ResponseEntity findQuestion(@PathVariable("question-id")
                                       @Positive long questionId) {

        Question question = questionService.findQuestion(questionId);
        QuestionDto.Response response = mapper.questionToQuestionResponseDto(question);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }


    //모든 질문 조회 : http://localhost:8080/question/?page=1&size=10&tag=java,spring
    @GetMapping("/") //postman ok - taglist 도 해결
    public ResponseEntity findQuestions(@Positive @RequestParam(required = false) int page,
                                        @Positive @RequestParam int size) {
        Page<Question> pageQuestion = questionService.findQuestions(page - 1, size);
        PageInfo pageInfo = new PageInfo(page, size, pageQuestion.getTotalElements(), pageQuestion.getTotalPages());
        List<Question> questions = pageQuestion.getContent();
        List<QuestionDto.Response> responses = mapper.questionsToQuestionResponseDtos(questions);


        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromPath("/api/questions/") // 기본 경로 설정
                .queryParam("page", page)    // 쿼리 파라미터 추가
                .queryParam("size", size);

        return ResponseEntity.ok()
                .location(uriBuilder.build().toUri())
                .body(new MultiResponseDto<>(responses, pageInfo));
    }




/*  추천  //////////////////////////////////////// 사용 예정
    @PostMapping("/{question-id}/up") //no test
    public ResponseEntity upVote(@Valid @RequestBody User user,
                                 @PathVariable("question-id")
                                 @Positive long questionId){
        Question findQuestion = questionService.findQuestion(questionId); // ??
        questionService.upVote(questionId, user);


        return new ResponseEntity(HttpStatus.OK);
    }

 */






/*  비추천  //////////////////////////////////////// 사용 예정
    @PostMapping("/{question-id}/down") //no test
    public ResponseEntity downVote(@Valid @RequestBody User user,
                                   @PathVariable("question-id")
                                   @Positive long questionId){
        Question findQuestion = questionService.findQuestion(questionId); // ??
        questionService.downVote(questionId, user);

        return new ResponseEntity(HttpStatus.OK);
    }

 */


    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("{question-id}") //postman ok
    public ResponseEntity deleteQuestion(@PathVariable("question-id")
                                         @Positive long questionId) {

        //작성자만랑 관리자만 삭제 할 수 있는 기능 추가해야 함
//        if (!question.getMember().getMemberId().equals(member.getMemberId()) && !member.getEmail().equals("admin@gmail.com")) {
//            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);

        questionService.deleteQuestion(questionId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    //태그 검색
//    @GetMapping("/search")
//    public ResponseEntity<MultiResponseDto<QuestionDto.Response>> getQuestionsByTag(
//            @RequestParam @Positive int page,
//            @RequestParam @Positive int size,
//            @RequestParam String tag) {
//
//        Page<Question> pageQuestions = questionService.findAllByTagContaining(page - 1, size, tag);
//        List<QuestionDto.Response> responses = mapper.questionsToQuestionResponseDtos(pageQuestions.getContent());
//
//        UriComponentsBuilder uriBuilder = UriComponentsBuilder
//                .fromPath("/api/search/")
//                .queryParam("page", page)
//                .queryParam("size", size)
//                .queryParam("tag", tag);
//
//        PageInfo pageInfo = new PageInfo(page, size, pageQuestions.getTotalElements(), pageQuestions.getTotalPages());
//        MultiResponseDto multiResponseDto = new MultiResponseDto (
//                responses, new PageImpl<>(responses, PageRequest.of(page, size), pageQuestions.getTotalElements()));
//
//        return ResponseEntity.ok()
//                .location(uriBuilder.build().toUri())
//                .body(multiResponseDto);
//    }

    //태그검색 :http://localhost:8080/question/search/?page=1&size=10&tag=tag
    // 페이지번호는 0부터 시작해야 함.. postman ok
    @GetMapping("/search")
    public ResponseEntity getQuestionsByTag(@RequestParam @Positive int page,
                                            @RequestParam @Positive int size,
                                            @RequestParam String tag) {

        List<String> tags = Arrays.asList(tag.split(",")); // 태그 리스트로 변환

        Page<Question> tagPage = questionService.findAllByTags(tag, page - 1, size);

        PageInfo pageInfo = new PageInfo(page, size, tagPage.getTotalElements(), tagPage.getTotalPages());

        List<Question> questions = tagPage.getContent();
        List<QuestionDto.Response> responses = mapper.questionsToQuestionResponseDtos(questions);


        MultiResponseDto<QuestionDto.Response> multiResponseDto = new MultiResponseDto<>(responses, pageInfo);

        return ResponseEntity.ok()
                .body(multiResponseDto);
    }


//        UriComponentsBuilder uriBuilder =
//                UriComponentsBuilder
//                .fromPath("/api/search/") // 기본 경로 설정
//                .queryParam("page", page)    // 쿼리 파라미터 추가
//                .queryParam("size", size)
//                        .queryParam("tag", tag);
//
//
//
//        return ResponseEntity.ok()
//                .location(uriBuilder.build().toUri())
//                .body(new MultiResponseDto<>(responses, pageInfo));


}


/* 태그중복될때
public Question createQuestion(Question question, String memberEmail){

        Member member = memberService.findExistMemberByEmail(memberEmail);
        question.setMember(member);
        member.addQuestion(question);

        Vote vote = new Vote();
        vote.setQuestion(question);
        question.setVote(vote);


        List<QuestionTag> questionTags = question.getQuestionTags();
        for (int i = 0; i < questionTags.size(); i++) {
            boolean alreadyExistTag = findTag(questionTags.get(i).getQuestionTagName());

            if(alreadyExistTag == true){
                Tag tag = findTagByName(questionTags.get(i).getQuestionTagName());
                tag.setUsageCount(tag.getUsageCount() + 1);
//                tag.addQuestionTag(questionTags.get(i));
                questionTags.get(i).setTag(tag);
            }
            else{
                Tag tag = new Tag(questionTags.get(i).getQuestionTagName(), 1);
                tag.addQuestionTag(questionTags.get(i));
                questionTags.get(i).setTag(tag);
            }
        }

 */