package com.preproject.stackOverflow.answer.mapper;

import com.preproject.stackOverflow.answer.dto.AnswerDto;

import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    Answer answerPatchDtoToAnswer(AnswerDto answerPatchDto);

    List<AnswerDto> answersToAnswerResponseDtos(List<Answer> answers);

    default Answer answerPostDtoToAnswer(long questionId, AnswerDto answerPostDto) {
        Answer answer = new Answer();
        answer.setContent(answerPostDto.getContent());

        Question question = new Question();
        question.setQuestionId(questionId);
        answer.setQuestion(question);

        return answer;
    }

    default AnswerDto answerToAnswerResponseDto(Answer answer) {
        return new AnswerDto(
                answer.getAnswerId(),
                answer.getAnswerStatus(),
                answer.getQuestion().getQuestionId(),
                answer.getContent(),
                answer.getVote(),
                answer.getCreatedAt(),
                answer.getModifiedAt());
    }
}
//    MemberDto.Response memberToMemberResponseDto(Member member);
