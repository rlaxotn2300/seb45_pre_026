package com.preproject.stackOverflow.question.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
        private List<String> tagList;
        private LocalDateTime createdAt;
       // private Question.QuestionStatus QUESTION_ASKED;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {

       // private long memberId;
        private long questionId;
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;
        @NotBlank(message = "내용을 입력하세요.")
        private String content;
        //private String tag;
        private List<String> tagList;
        private Question.QuestionStatus  QUESTION_MODIFIED;

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private long memberId;
        //private Answer answer;
        private long questionId;
        private String title;
        private String content;
        //private String tag;
        private List<String> tags;
        private long vote;
        private LocalDateTime createdAt;
        //private LocalDateTime modifiedAt;
        //private Question.QuestionStatus questionStatus;

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Vote{
        private long questionId;
        private long vote;

    }


//    @Getter
//    @Setter
//    @AllArgsConstructor
//    public static  class QuestionMemberResponseForList{
//        private long questionId;
//        private String title;
//        private String content;
//        private List<String> tags;
//        private long vote;
//        private Question.QuestionStatus questionStatus;
//    }



}
