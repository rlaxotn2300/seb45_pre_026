package com.preproject.stackOverflow.answer.dto;

import com.preproject.stackOverflow.answer.entity.Answer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class AnswerResponseDto {
    private long answerId;
    private Answer.AnswerStatus answerStatus;
    private long questionId;
    private String content;
    private long voteCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public AnswerResponseDto(long answerId, Answer.AnswerStatus answerStatus, long questionId,
                             String content, long voteCount, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.answerId = answerId;
        this.answerStatus = answerStatus;
        this.questionId = questionId;
        this.content = content;
        this.voteCount = voteCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    public static AnswerResponseDto createFromAnswer(Answer answer) {
        return new AnswerResponseDto(
                answer.getAnswerId(),
                answer.getAnswerStatus(),
                answer.getQuestion().getQuestionId(),
                answer.getContent(),
                answer.getVote(),
                answer.getCreatedAt(),
                answer.getModifiedAt()
        );
    }
}

