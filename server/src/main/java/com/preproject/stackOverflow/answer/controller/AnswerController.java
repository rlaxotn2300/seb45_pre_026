package com.preproject.stackOverflow.answer.controller;


import com.preproject.stackOverflow.answer.dto.AnswerDto;
import com.preproject.stackOverflow.answer.dto.AnswerVoteDto;
import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.answer.mapper.AnswerMapper;
import com.preproject.stackOverflow.answer.service.AnswerService;
import com.preproject.stackOverflow.auth.userdetails.CustomersDetailsService;
import com.preproject.stackOverflow.dto.MultiAnsResponseDto;
import com.preproject.stackOverflow.dto.SingleResponseDto;
import com.preproject.stackOverflow.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final AnswerMapper mapper;

    public AnswerController(AnswerService answerService, MemberService memberService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping("/{question-id}")
    public ResponseEntity postAnswer(@PathVariable("question-id") @Positive long questionId,
                                     @Valid @RequestBody AnswerDto answerDto) {

        Long memberId = CustomersDetailsService.getAccountId();
        Answer answer = answerService.createAnswer(
                mapper.answerPostDtoToAnswer(questionId, answerDto), memberId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.CREATED);

    }

    @PatchMapping("/{question-id}/answer/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("question-id") @Positive long questionId,
                                      @PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto answerDto) {

        Long memberId = CustomersDetailsService.getAccountId();

        answerDto.setAnswerId(answerId);
        Answer answer = answerService.updateAnswer(
                mapper.answerPatchDtoToAnswer(answerDto), memberId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)), HttpStatus.OK);

    }

    @GetMapping("/{question-id}/answer")
    public ResponseEntity getAnswers(@PathVariable("question-id") @Positive long questionId) {

        List<Answer> answers = answerService.findAnswers(questionId);

        return new ResponseEntity<>(
                new MultiAnsResponseDto<>(mapper.answersToAnswerResponseDtos(answers)),
                HttpStatus.OK);

    }


    @DeleteMapping("/{question-id}/answer/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {

        Long memberId = CustomersDetailsService.getAccountId();

        answerService.deleteAnswer(answerId, memberId.toString());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/{question-id}/answer/{answer-id}/upvote")
    public ResponseEntity upVoteAnswer(@PathVariable("question-id") @Positive long questionId,
                                       @PathVariable("answer-id") @Positive long answerId,
                                       @Valid @RequestBody Map<String, Long> request) {
        Long memberId = CustomersDetailsService.getAccountId();

        answerService.upVoteAnswer(answerId, memberId);

        AnswerVoteDto answerVoteDto = new AnswerVoteDto(answerService.getVote(answerId));

        return new ResponseEntity<>(answerVoteDto, HttpStatus.OK);

    }

    @PostMapping("/{question-id}/answer/{answer-id}/downvote")
    public ResponseEntity downVoteAnswer(@PathVariable("question-id") @Positive long questionId,
                                         @PathVariable("answer-id") @Positive long answerId,
                                         @Valid @RequestBody Map<String, Long> request) {

        Long memberId = CustomersDetailsService.getAccountId();

        answerService.downVoteAnswer(answerId, memberId);

        AnswerVoteDto answerVoteDto = new AnswerVoteDto(answerService.getVote(answerId));

        return new ResponseEntity<>(answerVoteDto, HttpStatus.OK);
    }

}
