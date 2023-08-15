package com.preproject.stackOverflow.answer.mapper;

import com.preproject.stackOverflow.answer.dto.AnswerPatchDto;
import com.preproject.stackOverflow.answer.dto.AnswerPostDto;
import com.preproject.stackOverflow.answer.dto.AnswerResponseDto;
import com.preproject.stackOverflow.answer.dto.AnswerVoteDto;
import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {


    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);

    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answers);

    default Answer answerPostDtoToAnswer(long questionId, AnswerPostDto answerPostDto) {
        Answer answer = new Answer();
        answer.setContent(answerPostDto.getContent());

        Question question = new Question();
        question.setQuestionId(questionId);
        answer.setQuestion(question);

        return answer;
    }

    default AnswerResponseDto answerToAnswerResponseDto(Answer answer) {
        return new AnswerResponseDto(
                answer.getAnswerId(),
                answer.getAnswerStatus(),
                answer.getQuestion().getQuestionId(),
                answer.getContent(),
                answer.getVote(),
                answer.getCreatedAt(),
                answer.getModifiedAt()
        );
    }
}