package com.preproject.stackOverflow.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerPostDto {
    @NotEmpty(message = "내용을 입력해야 합니다.")
    private String content;

}