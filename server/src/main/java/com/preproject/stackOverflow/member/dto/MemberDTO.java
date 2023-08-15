package com.preproject.stackOverflow.member.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDTO {

    @Getter
    @Setter
    public static class Post {
        @NotBlank(message = "공백이 아니여야 합니다.")
        private String name;

        @NotBlank(message = "공백이 아니여야 합니다.")
        @Size(min = 8, max = 20, message = "8글자 이상 20글자 이하로 작성해주세요.")
        private String password;

        @NotBlank(message = "공백이 아니여야 합니다.")
        @Email(message = "이메일 형식으로 작성해주세요.")
        private String email;
    }

    @Getter
    @Setter
    public static class Patch {
        private long memberId;

        @NotBlank(message = "공백이 아니여야 합니다.")
        private String name;

        @NotBlank(message = "공백이 아니여야 합니다.")
        @Size(min = 8, max = 20, message = "8자 이상 20자 이하로 작성해주세요.")
        private String password;

        @NotBlank(message = "공백이 아니여야 합니다.")
        @Email(message = "이메일 형식으로 작성해주세요.")
        private String email;
    }

    @Getter
    @Setter
    public static class Response {
        private long memberId;
        private String email;
        private String name;
    }

    @Getter
    @Builder
    public static class ResponseMyPage {
        private long memberId;
        private String name;
        private String email;
        private LocalDateTime createdTime;
        private LocalDateTime modifiedTime;
//        private List<QuestionResponseDto.QuestionMemberResponseForList> questions;
//        private List<AnswerDto.AnswerMemberResponseForList> answers;
    }

//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter
//    @Setter
//    public static class GetResponse {
//        private long memberId;
//        private String email;
//        private String name;
//        private List<MyPageQuestion> questions;
//        private List<MyPageAnswer> answers;
//    }
//
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter
//    @Setter
//    public static class MyPageQuestion {
//        private long questionId;
//        private String title;
//        private int likes;
//        private int answer_cnt;
//        private int views;
//        private LocalDateTime created_at;
//    }
//
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter
//    @Setter
//    public static class MyPageAnswer {
//        private long questionId;
//        private String title;
//        private int likes;
//        private int answer_cnt;
//        private int views;
//        private LocalDateTime created_at;
//    }

}
