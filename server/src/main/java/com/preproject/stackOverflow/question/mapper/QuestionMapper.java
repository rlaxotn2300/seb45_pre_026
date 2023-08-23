package com.preproject.stackOverflow.question.mapper;


import com.preproject.stackOverflow.question.dto.QuestionDto;
import com.preproject.stackOverflow.question.entity.Question;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

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
            List<String> list = questionPost.getTags();
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
            question.setTag(questionPatch.getTag());
            List<String> list = questionPatch.getTags();
            if (list != null) {
                question.setTags(new ArrayList<>(list));;
            }

            return question;
        }
    }


    default List<QuestionDto.Response> questionsToQuestionResponseDtos(List<Question> questions) {
        return questions.stream()
                .map(this::questionToQuestionResponseDto)
                .collect(Collectors.toList());
    }


    default QuestionDto.Vote questionVoteToQuestion(QuestionDto.Vote questionVote){
        QuestionDto.Vote vote = new QuestionDto.Vote();
            questionVote.setVote(questionVote.getVote());

            return vote;

    }

}