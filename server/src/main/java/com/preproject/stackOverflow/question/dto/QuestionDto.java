package com.preproject.stackOverflow.question.dto;


import com.preproject.stackOverflow.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


public class QuestionDto {


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Post {
        private String member;
        private Long memberId;
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;
        @NotBlank(message = "내용을 입력하세요.")
        private String content;
        private String tag;
        private List<String> tags;
        private LocalDateTime createdAt;
        // private Question.QuestionStatus QUESTION_ASKED;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {

        private Long memberId;
        private long questionId;
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;
        @NotBlank(message = "내용을 입력하세요.")
        private String content;
        private String tag;
        private List<String> tags;
        private Question.QuestionStatus QUESTION_MODIFIED;

    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long memberId;
        private long questionId;
        private String title;
        private String content;
        private List<String> tags;
        private long vote;
        private LocalDateTime createdAt;

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Vote {
        private long questionId;
        private long memberId;
        private long vote;

        public Vote(long vote, long memberId) {
            this.vote = vote;
            this.memberId = memberId;
        }
    }
}







