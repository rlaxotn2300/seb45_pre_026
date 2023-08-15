package com.preproject.stackOverflow.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnswerVoteDto {

    private long voteCount;

    public AnswerVoteDto(long voteCount) {
        this.voteCount = voteCount;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;

    }
}
