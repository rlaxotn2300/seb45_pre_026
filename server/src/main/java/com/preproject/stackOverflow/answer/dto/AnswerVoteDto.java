package com.preproject.stackOverflow.answer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnswerVoteDto {
    private String content;
    private String voteStatus;
    private int vote;
}