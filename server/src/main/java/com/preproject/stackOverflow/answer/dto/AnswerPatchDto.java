package com.preproject.stackOverflow.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  AnswerPatchDto {
    private long answerId;
    private String content;

    @NotEmpty(message = "내용을 입력해야 합니다.")
    @Size(min = 10, message = "10자 이상 입력해야 합니다.")

    public void setAnswerId(long answerId) {

        this.answerId = answerId;
    }

}
