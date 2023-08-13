package com.preproject.stackOverflow.question.controller;


import com.preproject.stackOverflow.dto.MultiResponseDto;
import com.preproject.stackOverflow.dto.PageInfo;
import com.preproject.stackOverflow.dto.SingleResponseDto;
import com.preproject.stackOverflow.question.dto.QuestionDto;
import com.preproject.stackOverflow.question.entity.Question;
import com.preproject.stackOverflow.question.mapper.QuestionMapper;
import com.preproject.stackOverflow.question.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
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
    @PostMapping("/ask") //postman ok : 태그 여러개가 안됨
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPost){

//        로그인한 유저만 글 등록하는 로직 추가 예정
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPost));
        QuestionDto.Response response = mapper.questionToQuestionResponseDto(question);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }



    // @Secured("ROLE_USER")
    @PatchMapping("{question-id}") //postman  ok : 태그여러개가 안됨
    public ResponseEntity patchQuestion(@RequestBody QuestionDto.Patch patchDto,
                                        @PathVariable("question-id")
                                        @Positive long questionId){

        //작성자만 수정할 수 있는 로직 추가 예정
        Question response = questionService.patchQuestion(mapper.questionPatchDtoToQuestion(patchDto), questionId);
        QuestionDto.Response question = mapper.questionToQuestionResponseDto(response);

        return new ResponseEntity(new SingleResponseDto<>(question), HttpStatus.OK);

    }

    //질문 1개 조회
    @GetMapping("{question-id}") //postman ok
    public ResponseEntity findQuestion(@PathVariable("question-id")
                                       @Positive long questionId){
        Question question = questionService.findQuestion(questionId);
        QuestionDto.Response response = mapper.questionToQuestionResponseDto(question);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }


    //모든 질문 조회
    @GetMapping("/") //postman ok - taglist가 안됨
    public ResponseEntity findQuestions(@Positive @RequestParam(required=false) Integer page,
                                        @Positive @RequestParam int size){
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


    /*
    @PostMapping("/{question-id}/up") //postman ok - 근데 카운팅이 안됨
    public ResponseEntity UPVOTE(@Valid @RequestBody Question question,
                                 @PathVariable("question-id")
                                 @Positive long questionId){
        Question findQuestion = questionService.findQuestion(questionId);
        questionService.upVOTE(findQuestion, questionId);

        return new ResponseEntity(HttpStatus.OK);
    }
    */






    /*
    @PostMapping("/{question-id}/down") //postman ok - 근데 카운팅이 안됨
    public ResponseEntity DOWNVOTE(@Valid @RequestBody Question question,
                                   @PathVariable("question-id")
                                   @Positive long questionId){
        Question findQuestion = questionService.findQuestion(questionId);
        questionService.downVOTE(findQuestion, questionId);

        return new ResponseEntity(HttpStatus.OK);
    }

     */




    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("{question-id}") //postman ok
    public ResponseEntity deleteQuestion(@PathVariable("question-id")
                                         @Positive long questionId){
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
    @GetMapping("/search") //postman은 ok인데... 이게 맞는건지...ㅠㅠ
    public ResponseEntity getQuestionsByTag(@RequestParam @Positive int page,
                                            @RequestParam @Positive int size,
                                            @RequestParam String tag) {

        Page<Question> tagPage = questionService.findAllByTag(tag, page-1, size);
        PageInfo pageInfo = new PageInfo(page, size, tagPage.getTotalElements(), tagPage.getTotalPages());

        List<Question> questions = tagPage.getContent();
        List<QuestionDto.Response> responses = mapper.questionsToQuestionResponseDtos(questions);

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

        return new ResponseEntity(new MultiResponseDto(responses, pageInfo), HttpStatus.OK);
    }

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