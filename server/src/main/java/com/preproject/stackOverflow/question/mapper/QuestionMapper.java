package com.preproject.stackOverflow.question.mapper;


import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.question.dto.QuestionDto;
import com.preproject.stackOverflow.question.entity.Question;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface QuestionMapper {

    default Question questionPostDtoToQuestion(QuestionDto.Post questionPost) {
        if (questionPost == null) {
            return null;
        } else {
            Question question = new Question();
            question.setTitle(questionPost.getTitle());
            question.setContent(questionPost.getContent());
            question.setTag(questionPost.getTag());
            List<String> list = questionPost.getTagList();
            if (list != null) {

                question.setTags(new ArrayList(list));
            }

            return question;
        }
    }


    QuestionDto.Response questionToQuestionResponseDto(Question response);




    @Mapping(target = "tags", source = "tag")
    default Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatch) {
        if (questionPatch == null) {
            return null;
        } else {
            Question question = new Question();
            question.setQuestionId(questionPatch.getQuestionId());
            question.setTitle(questionPatch.getTitle());
            question.setContent(questionPatch.getContent());
            //question.setTag(questionPatch.getTag());
            List<String> list = questionPatch.getTagList();
            if (list != null) {
                question.setTags(new ArrayList<>(list));;
            }

            return question;
        }
    }

//    default List<QuestionDto.Response> questionsToQuestionResponseDtos(List<Question> question) {
//        List<QuestionDto.Response> response = new ArrayList<>();
//        if (question == null) {
//            return null;
//        }
//        return response;
//    }


    default List<QuestionDto.Response> questionsToQuestionResponseDtos(List<Question> questions) {
        return questions.stream()
                .map(this::questionToQuestionResponseDto)
                .collect(Collectors.toList());
    }


    Question questionVoteToQuestion(QuestionDto.Vote questionVote);




//    default QuestionDto.Response questionToResponseDto(Question question) {
//        QuestionDto.Response response =
//                new QuestionDto.Response(question.getQuestionId(),
//                        question.getTitle(),
//                        question.getContent(),
//                        question.getTag(),
//
//                        question.getTags(),
//                        question.getVote(),
//                        question.getCreatedAt(),
//                        question.getModifiedAt(),
//                        question.getQuestionStatus());
//
//        response.setTitle(question.getTitle());
//        response.setContent(question.getContent());
//        response.setContent(question.getContent());
//        response.setTag(question.getTag());
//
//        response.setTags(question.getTags());
//        response.setVote(question.getVote());
//        response.setCreatedAt(question.getCreatedAt());
//        response.setModifiedAt(question.getModifiedAt());
//        response.setQuestionStatus(response.getQuestionStatus());
//
//        return response;
//    }
}
