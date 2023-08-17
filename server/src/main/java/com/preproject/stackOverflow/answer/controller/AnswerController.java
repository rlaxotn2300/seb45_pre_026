package com.preproject.stackOverflow.answer.controller;

import com.preproject.stackOverflow.answer.dto.AnswerDto;
import com.preproject.stackOverflow.answer.dto.AnswerVoteDto;
import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.answer.mapper.AnswerMapper;
import com.preproject.stackOverflow.answer.service.AnswerService;
import com.preproject.stackOverflow.dto.MultiAnsResponseDto;
import com.preproject.stackOverflow.dto.SingleResponseDto;
import com.preproject.stackOverflow.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/question")
public class AnswerController {
    private final AnswerService answerService;
    private final MemberService memberService;

    private final AnswerMapper mapper;

    public AnswerController(AnswerService answerService, MemberService memberService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("{question-id}")
    public ResponseEntity postAnswer(@PathVariable("question-id") @Positive long questionId,
                                     @Valid @RequestBody AnswerDto answerDto,
                                     @Positive long memberId) {

        String memberName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!memberService.findMember(memberId).equals(memberName)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Answer answer = answerService.createAnswer(
                    mapper.answerPostDtoToAnswer(questionId, answerDto), memberId);

            return new ResponseEntity<>(
                    new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                    HttpStatus.CREATED);

    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("{question-id}/answer/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("question-id") @Positive long questionId,
                                      @PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto answerDto,
                                      @Positive long memberId) {

        answerDto.setAnswerId(answerId);
        Answer answer = answerService.updateAnswer(
                mapper.answerPatchDtoToAnswer(answerDto), memberId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)), HttpStatus.OK);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{question-id}/answer")
    public ResponseEntity getAnswers(@PathVariable("question-id") @Positive long questionId) {

            List<Answer> answers = answerService.findAnswers(questionId);

        return new ResponseEntity<>(
                new MultiAnsResponseDto<>(mapper.answersToAnswerResponseDtos(answers)),
                HttpStatus.OK);

    }


    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{question-id}/answer/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        answerService.deleteAnswer(answerId, email);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{question-id}/answer/{answer-id}/upvote")
    public ResponseEntity upVoteAnswer(@PathVariable("question-id") @Positive long questionId,
                                       @PathVariable("answer-id") @Positive long answerId,
                                       @Valid @RequestBody Map<String, Long> request) {
        long memberId = request.get("memberId");

        answerService.upVoteAnswer(answerId, memberId);

        AnswerVoteDto answerVoteDto = new AnswerVoteDto(answerService.getVote(answerId));

        return new ResponseEntity<>(answerVoteDto, HttpStatus.OK);

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{question-id}/answer/{answer-id}/downvote")
    public ResponseEntity downVoteAnswer(@PathVariable("question-id") @Positive long questionId,
                                         @PathVariable("answer-id") @Positive long answerId,
                                         @Valid @RequestBody Map<String, Long> request) {

        long memberId = request.get("memberId");
        answerService.downVoteAnswer(answerId, memberId);

        AnswerVoteDto answerVoteDto = new AnswerVoteDto(answerService.getVote(answerId));

        return new ResponseEntity<>(answerVoteDto, HttpStatus.OK);
    }

}

