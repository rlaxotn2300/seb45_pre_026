package com.preproject.stackOverflow.answer.mapper;


import com.preproject.stackOverflow.answer.dto.AnswerDto;
import com.preproject.stackOverflow.answer.entity.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    default Answer answerPatchDtoToAnswer(AnswerDto answerPatchDto) {
        Answer answer;
        if (answerPatchDto == null) {
            return null;
        } else {
            answer = new Answer();
            answer.setAnswerId(answerPatchDto.getAnswerId());
            answer.setContent(answerPatchDto.getContent());
            answer.setVote(answerPatchDto.getVoteCount());

        }

        return answer;
    }

    List<AnswerDto> answersToAnswerResponseDtos(List<Answer> answers);

    default Answer answerPostDtoToAnswer(Long questionId, AnswerDto answerDto) {
        if (answerDto == null) {
            return null;
        }

        Answer answer = new Answer();
        //answer.setAnswerStatus(answerDto.getAnswerStatus());
        //answer.setMember(answerDto.getMember());
        answer.setContent(answerDto.getContent());
        answer.setCreatedAt(answerDto.getCreatedAt());
        answer.setModifiedAt(answerDto.getModifiedAt());

        return answer;
    }



    default AnswerDto answerToAnswerResponseDto(Answer answer) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerId(answer.getAnswerId());
       // answerDto.setAnswerStatus(answer.getAnswerStatus());
        answerDto.setQuestionId(answer.getQuestion().getQuestionId());
        answerDto.setContent(answer.getContent());
        answerDto.setCreatedAt(answer.getCreatedAt());
        answerDto.setModifiedAt(answer.getModifiedAt());

        return answerDto;
    }

}

//    MemberDto.Response memberToMemberResponseDto(Member member);