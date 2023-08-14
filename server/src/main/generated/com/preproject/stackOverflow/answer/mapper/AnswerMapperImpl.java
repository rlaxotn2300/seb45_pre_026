package com.preproject.stackOverflow.answer.mapper;

import com.preproject.stackOverflow.answer.dto.AnswerPatchDto;
import com.preproject.stackOverflow.answer.dto.AnswerResponseDto;
import com.preproject.stackOverflow.answer.dto.AnswerVoteDto;
import com.preproject.stackOverflow.answer.entity.Answer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-14T10:12:49+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.19 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerVoteDtoToanswer(AnswerVoteDto answervoteDto) {
        if ( answervoteDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setVote( answervoteDto.getVote() );
        answer.setContent( answervoteDto.getContent() );

        return answer;
    }

    @Override
    public AnswerVoteDto answerToanswerVoteDto(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerVoteDto answerVoteDto = new AnswerVoteDto();

        return answerVoteDto;
    }

    @Override
    public Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto) {
        if ( answerPatchDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setAnswerId( answerPatchDto.getAnswerId() );
        answer.setContent( answerPatchDto.getContent() );

        return answer;
    }

    @Override
    public List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerResponseDto> list = new ArrayList<AnswerResponseDto>( answers.size() );
        for ( Answer answer : answers ) {
            list.add( answerToAnswerResponseDto( answer ) );
        }

        return list;
    }
}
