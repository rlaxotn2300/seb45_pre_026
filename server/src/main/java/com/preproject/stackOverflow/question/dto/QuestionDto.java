package com.preproject.stackOverflow.question.dto;


import com.preproject.stackOverflow.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


public class QuestionDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {

        @NotBlank
        private String title;
        private String content;
        private String tag;
        private List<String> tagList;
        private LocalDateTime createdAt;
        private Question.QuestionStatus QUESTION_ASKED;

    }

    public static class Patch {
        private long questionId;
        private String title;
        private String content;
        private String tag;
        private List<String> tagList;
        private LocalDateTime modifiedAt;
        private Question.QuestionStatus  QUESTION_MODIFIED;

    }


    public static class Response {
        private long questionId;
        private String title;
        private String content;
        private String tag;
        private List<String> tags;
        private int voteCount;
        private String questionStatus;
        private LocalDateTime createdAt;

    }
}
