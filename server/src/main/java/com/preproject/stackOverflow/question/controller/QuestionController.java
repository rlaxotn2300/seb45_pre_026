package com.preproject.stackOverflow.question.controller;


import com.preproject.stackOverflow.dto.MultiResponseDto;
import com.preproject.stackOverflow.dto.PageInfo;
import com.preproject.stackOverflow.dto.SingleResponseDto;
import com.preproject.stackOverflow.exception.BusinessLogicException;
import com.preproject.stackOverflow.exception.ExceptionCode;
import com.preproject.stackOverflow.member.service.MemberService;
import com.preproject.stackOverflow.question.dto.QuestionDto;
import com.preproject.stackOverflow.question.entity.Question;
import com.preproject.stackOverflow.question.mapper.QuestionMapper;
import com.preproject.stackOverflow.question.service.QuestionService;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.net.URI;
import java.util.List;



@Validated
@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;
    private final MemberService memberService;


    public QuestionController(QuestionService questionService, QuestionMapper mapper, MemberService memberService) {
        this.questionService = questionService;
        this.mapper = mapper;
        this.memberService = memberService;
    }


    //질문 등록
    @Secured("ROLE_USER")
    @PostMapping("/questions")
    public ResponseEntity<Void> postQuestion(@Valid @RequestBody QuestionDto.Post questionPost,
                                             @RequestParam @Positive Long memberId) {

        String memberName = SecurityContextHolder.getContext().getAuthentication().getName();
        questionPost.setMember(memberName);
        questionPost.setMemberId(memberId);

        Long questionId = questionService.createQuestion((mapper.questionPostDtoToQuestion(questionPost)), memberId) ;

        URI uri = URI.create("/questions/" + questionId);

        return ResponseEntity.created(uri).build();
    }


    //질문 수정
    @Secured("ROLE_USER")
    @PatchMapping("/{question-id}")
    public ResponseEntity<Question> patchQuestion(@RequestBody QuestionDto.Patch patchDto,
                                                  @PathVariable("question-id")
                                                  @Positive long questionId ) {



        Question response = questionService.patchQuestion(mapper.questionPatchDtoToQuestion(patchDto), questionId);
        QuestionDto.Response question = mapper.questionToQuestionResponseDto(response);

        return new ResponseEntity(new SingleResponseDto<>(question), HttpStatus.OK);

    }


    //질문 1개 조회
    @GetMapping("/{question-id}")
    public ResponseEntity<Question> findQuestion(@PathVariable("question-id")
                                                 @Positive long questionId) {

        Question question = questionService.findQuestion(questionId);
        QuestionDto.Response response = mapper.questionToQuestionResponseDto(question);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }


    //전체 질문 조회 : http://localhost:8080/question/?page=1&size=10&tag=java,spring
    @GetMapping("/")
    public ResponseEntity<Question> findQuestions(@Positive @RequestParam int page,
                                                  @Positive @RequestParam int size) {

        Page<Question> pageQuestion = questionService.findQuestions(page - 1, size);
        PageInfo pageInfo = new PageInfo(page, size, pageQuestion.getTotalElements(), pageQuestion.getTotalPages());

        List<Question> questions = pageQuestion.getContent();
        List<QuestionDto.Response> responses = mapper.questionsToQuestionResponseDtos(questions);

        return new ResponseEntity(responses, HttpStatus.OK);
    }


    //질문추천
    @PostMapping("/{question-id}/upvote")
    public ResponseEntity<SingleResponseDto> upVote(@PathVariable("question-id")  @Positive Long questionId,
                                                    @Positive Long memberId,
                                                    QuestionDto.Vote vote) {

        questionService.upVote(questionId, memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionVoteToQuestion(vote)), HttpStatus.OK
        );
    }


    //질문비추천
    @PostMapping("/{question-id}/downvote")
    public ResponseEntity<SingleResponseDto> downVote(@PathVariable("question-id") @Positive long questionId,
                                                      @Positive Long memberId,
                                                      QuestionDto.Vote vote) {

        questionService.downVote(questionId, memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionVoteToQuestion(vote)), HttpStatus.OK
        );
    }


    //질문 삭제
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id")

                                         @Positive long questionId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        questionService.deleteQuestion(email, questionId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }



    @GetMapping("/search")
    public ResponseEntity getQuestionsByTag ( @RequestParam @Positive int page,
                                              @RequestParam @Positive int size,
                                              @RequestParam String tag){


        Page<Question> tagPage = questionService.findAllByTags(tag, page - 1, size);

        if (tagPage.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND);
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





             UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromPath("/api/questions/")
                .queryParam("page", page)
                .queryParam("size", size);

        return ResponseEntity.ok()
                .location(uriBuilder.build().toUri())
                .body(new MultiResponseDto<>(responses, pageInfo));
     */