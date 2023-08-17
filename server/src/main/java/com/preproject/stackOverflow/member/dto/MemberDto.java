package com.preproject.stackOverflow.member.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MemberDto {

    @Getter
    @Setter
    public static class Post {
        @NotBlank(message = "공백이 아니여야 합니다.")
        @Email(message = "이메일 형식으로 작성해주세요.")
        private String email;

        @NotBlank(message = "공백이 아니여야 합니다.")
        private String name;

        @NotBlank(message = "공백이 아니여야 합니다.")
        @Size(min = 8, max = 20, message = "8글자 이상 20글자 이하로 작성해주세요.")
        private String password;
    }

    @Getter
    @Setter
    public static class Patch {
        private long memberId;

        @NotBlank(message = "공백이 아니여야 합니다.")
        @Email(message = "이메일 형식으로 작성해주세요.")
        private String email;

        @NotBlank(message = "공백이 아니여야 합니다.")
        @Size(min = 8, max = 20, message = "8자 이상 20자 이하로 작성해주세요.")
        private String password;

        @NotBlank(message = "공백이 아니여야 합니다.")
        private String name;
    }

    @Getter
    @Setter
    public static class Response {
        private long memberId;
        private String email;
        private String name;
        private LocalDateTime createdTime;
        private LocalDateTime modifiedTime;
        //private List<QuestionDto.QuestionMemberResponseForList> questions;
        //private List<AnswerDto.AnswerMemberResponseForList> answers;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class GetResponse {
        private long memberId;
        private String email;
        private String name;
        private List<MyPageQuestion> questions;
        private List<MyPageAnswer> answers;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class MyPageQuestion {
        private long questionId;
        private String title;
        private String content;
        private long vote;
        private List<String> tags;
        private LocalDateTime created_at;
        private LocalDateTime modified_at;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class MyPageAnswer {
        private long answerId;
        private long questionId;
        private String content;
        private long vote;
        private LocalDateTime created_at;
        private LocalDateTime modified_at;
    }


    }

