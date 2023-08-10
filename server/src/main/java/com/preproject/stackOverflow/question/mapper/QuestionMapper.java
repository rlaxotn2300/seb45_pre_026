package com.preproject.stackOverflow.question.mapper;


import com.preproject.stackOverflow.question.dto.QuestionDto;
import com.preproject.stackOverflow.question.entity.Question;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question questionPostDtoToQuestion(QuestionDto.Post questionPost);

    QuestionDto.Response questionToQuestionResponseDto(Question question);

    Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatch);

    List<QuestionDto.Response> questionsToQuestionResponseDtos(List<Question> question);


}
