package com.preproject.stackOverflow.answer.controller;

import com.preproject.stackOverflow.answer.dto.AnswerPatchDto;
import com.preproject.stackOverflow.answer.dto.AnswerPostDto;
import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.answer.mapper.AnswerMapper;
import com.preproject.stackOverflow.answer.service.AnswerService;
import com.preproject.stackOverflow.dto.MultiAnsResponseDto;
import com.preproject.stackOverflow.dto.SingleResponseDto;
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

    public AnswerController(AnswerService answerService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping("{question-id}")  // 답변 등록 -> OK
    public ResponseEntity postAnswer(@PathVariable("question-id") @Positive long questionId,
                                     @Valid @RequestBody AnswerPostDto answerPostDto) {
        // 사용자 인증 상태 확인 로직 필요

        Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(questionId, answerPostDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.CREATED);

    }

    @PatchMapping("{question-id}/answer/{answer-id}") // 답변 수정 -> OK
    public ResponseEntity patchAnswer(@PathVariable("question-id") @Positive long questionId,
                                      @PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerPatchDto answerPatchDto) {

        // 사용자 인증 상태 확인 로직 필요

        answerPatchDto.setAnswerId(answerId);
        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(answerPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)), HttpStatus.OK);
    }

    @GetMapping("{question-id}/answer") // 답변 조회
    public ResponseEntity getAnswers(@PathVariable("question-id") @Positive long questionId) {
        List<Answer> answers = answerService.findAnswers(questionId);

        return new ResponseEntity<>(
                new MultiAnsResponseDto<>(mapper.answersToAnswerResponseDtos(answers)),
                HttpStatus.OK);
    }


    @DeleteMapping("/{question-id}/answer/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/{question-id}/answer/{answer-id}/upvote")
    public ResponseEntity upVoteAnswer(@PathVariable("question-id") @Positive long questionId,
                                       @PathVariable("answer-id") @Positive long answerId,
                                       @Valid @RequestBody Map<String, Long> request) {
        long memberId = request.get("memberId");

        answerService.upVoteAnswer(answerId, memberId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(
                        answerService.getVote(answerId)),HttpStatus.OK);

    }

    @PostMapping("/{question-id}/answer/{answer-id}/downvote")
    public ResponseEntity downVoteAnswer(@PathVariable("question-id") @Positive long questionId,
                                         @PathVariable("answer-id") @Positive long answerId,
                                         @Valid @RequestBody Map<String, Long> request) {

        long memberId = request.get("memberId");

        answerService.downVoteAnswer(answerId, memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(
                        answerService.getVote(answerId)), HttpStatus.OK);

    }




}
