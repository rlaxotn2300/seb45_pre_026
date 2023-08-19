package com.preproject.stackOverflow.answer.dto;

import com.preproject.stackOverflow.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    private long answerId;
    private Answer.AnswerStatus answerStatus;
    private long questionId;
    private String email;
    private Long memberId;

    @NotEmpty(message = "내용을 입력해야 합니다.")
    @Size(min = 10, message = "10자 이상 입력해야 합니다.")
    private String content;

    private long voteCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public AnswerDto(Long answerId,
                     Answer.AnswerStatus answerStatus,
                     long questionId,
                     String content,
                     long vote,
                     LocalDateTime createdAt,
                     LocalDateTime modifiedAt) {
    }

    public static AnswerDto createFromAnswer(Answer answer) {
        return new AnswerDto(
                answer.getAnswerId(),
                answer.getAnswerStatus(),
                answer.getQuestion().getQuestionId(),
                answer.getMember().getEmail(),
                answer.getMember().getMemberId(),
                answer.getContent(),
                answer.getVote(),
                answer.getCreatedAt(),
                answer.getModifiedAt()
        );
    }

}