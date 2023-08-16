package com.preproject.stackOverflow.question.controller;


import com.preproject.stackOverflow.dto.MultiResponseDto;
import com.preproject.stackOverflow.dto.PageInfo;
import com.preproject.stackOverflow.dto.SingleResponseDto;
import com.preproject.stackOverflow.exception.BusinessLogicException;
import com.preproject.stackOverflow.exception.ExceptionCode;
import com.preproject.stackOverflow.member.entity.Member;
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


    //질문 등록
    //@Secured("ROLE_USER")
    @PostMapping("/questions")
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPost) {

        //member 의 username 받기
        //String memberName = SecurityContextHolder.getContext().getAuthentication().getName();

//        로그인한 유저만 글 등록하는 로직 추가 예정
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPost));
        QuestionDto.Response response = mapper.questionToQuestionResponseDto(question);

        return new ResponseEntity(//new SingleResponseDto<>(response),
                HttpStatus.CREATED);
    }


    //질문 수정
    // @Secured("ROLE_USER")
    @PatchMapping("{question-id}")
    public ResponseEntity patchQuestion(@RequestBody QuestionDto.Patch patchDto,
                                        @PathVariable("question-id")
                                        @Positive long questionId) {

        //작성자만 수정할 수 있는 로직 추가 예정
        Question response = questionService.patchQuestion(mapper.questionPatchDtoToQuestion(patchDto), questionId);
        QuestionDto.Response question = mapper.questionToQuestionResponseDto(response);

        return new ResponseEntity(new SingleResponseDto<>(question), HttpStatus.OK);

    }


    //질문 1개 조회
    @GetMapping("{question-id}")
    public ResponseEntity findQuestion(@PathVariable("question-id")
                                       @Positive long questionId) {
        Question question = questionService.findQuestion(questionId);
        QuestionDto.Response response = mapper.questionToQuestionResponseDto(question);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }


    //전체 질문 조회 : http://localhost:8080/question/?page=1&size=10&tag=java,spring
    @GetMapping("/")
    public ResponseEntity findQuestions(@Positive @RequestParam(required = false) int page,
                                        @Positive @RequestParam int size) {
        Page<Question> pageQuestion = questionService.findQuestions(page - 1, size);
        PageInfo pageInfo = new PageInfo(page, size, pageQuestion.getTotalElements(), pageQuestion.getTotalPages());
        List<Question> questions = pageQuestion.getContent();
        List<QuestionDto.Response> responses = mapper.questionsToQuestionResponseDtos(questions);


        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromPath("/api/questions/")
                .queryParam("page", page)
                .queryParam("size", size);

        return ResponseEntity.ok()
                .location(uriBuilder.build().toUri())
                .body(new MultiResponseDto<>(responses, pageInfo));
    }


    //질문추천
    @PostMapping("/{question-id}/up")
    public ResponseEntity upVote(@PathVariable("question-id")
                                 @Positive long questionId) {
       // Question findQuestion = questionService.findQuestion(questionId);
        Question vote = questionService.upVote(questionId);
        QuestionDto.Response response = mapper.questionToQuestionResponseDto(vote);

        return new ResponseEntity(response, HttpStatus.OK);
    }


    //질문비추천
    @PostMapping("/{question-id}/down")
    public ResponseEntity downVote(@PathVariable("question-id")
                                   @Positive long questionId) {
        Question findQuestion = questionService.findQuestion(questionId);
        Question vote = questionService.downVote(questionId);
        QuestionDto.Response response = mapper.questionToQuestionResponseDto(vote);

        return new ResponseEntity(response, HttpStatus.OK);
    }


    //질문 삭제
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id")
                                         @Positive long questionId,
                                         Question question, Member member) {

//        if (question.getMember().getMemberId() == member.getMemberId() && !member.getEmail().equals("admin@gmail.com")) { //작성자만 삭제 할 수 있음
//            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
//        }
            questionService.deleteQuestion(questionId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        }



        //태그검색 :http://localhost:8080/question/search/?page=1&size=10&tag=tag
        // 페이지번호는 0부터 시작해야 함..
        @GetMapping("/search")
        public ResponseEntity getQuestionsByTag ( @RequestParam @Positive int page,
        @RequestParam @Positive int size,
        @RequestParam String tag){

            //List<String> tags = Arrays.asList(tag.split(",")); // 태그 리스트로 변환

            Page<Question> tagPage = questionService.findAllByTags(tag, page - 1, size);

            if (tagPage.isEmpty()) {
                throw new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND); // 예외 발생
            }

            PageInfo pageInfo = new PageInfo(page, size, tagPage.getTotalElements(), tagPage.getTotalPages());

            List<Question> questions = tagPage.getContent();
            List<QuestionDto.Response> responses = mapper.questionsToQuestionResponseDtos(questions);

            MultiResponseDto<QuestionDto.Response> multiResponseDto = new MultiResponseDto<>(responses, pageInfo);

            return ResponseEntity.ok().body(multiResponseDto);
        }
    }




    /* ================================================================


        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder
                .fromPath("/api/search/")
                .queryParam("page", page)
                .queryParam("size", size)
                        .queryParam("tag", tag);



        return ResponseEntity.ok()
                .location(uriBuilder.build().toUri())
                .body(new MultiResponseDto<>(responses, pageInfo));


}


                     태그중복될때
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





                태그 검색
    @GetMapping("/search")
    public ResponseEntity<MultiResponseDto<QuestionDto.Response>> getQuestionsByTag(
            @RequestParam @Positive int page,
            @RequestParam @Positive int size,
            @RequestParam String tag) {

        Page<Question> pageQuestions = questionService.findAllByTagContaining(page - 1, size, tag);
        List<QuestionDto.Response> responses = mapper.questionsToQuestionResponseDtos(pageQuestions.getContent());

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromPath("/api/search/")
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("tag", tag);

        PageInfo pageInfo = new PageInfo(page, size, pageQuestions.getTotalElements(), pageQuestions.getTotalPages());
        MultiResponseDto multiResponseDto = new MultiResponseDto (
                responses, new PageImpl<>(responses, PageRequest.of(page, size), pageQuestions.getTotalElements()));

        return ResponseEntity.ok()
                .location(uriBuilder.build().toUri())
                .body(multiResponseDto);


     */