package com.preproject.stackOverflow.answer.mapper;

import com.preproject.stackOverflow.answer.dto.AnswerDto;
import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.member.dto.MemberDto.Response;
import com.preproject.stackOverflow.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-17T21:50:33+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.19 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerPatchDtoToAnswer(AnswerDto answerPatchDto) {
        if ( answerPatchDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setAnswerId( answerPatchDto.getAnswerId() );
        answer.setAnswerStatus( answerPatchDto.getAnswerStatus() );
        answer.setContent( answerPatchDto.getContent() );
        answer.setCreatedAt( answerPatchDto.getCreatedAt() );
        answer.setModifiedAt( answerPatchDto.getModifiedAt() );

        return answer;
    }

    @Override
    public List<AnswerDto> answersToAnswerResponseDtos(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerDto> list = new ArrayList<AnswerDto>( answers.size() );
        for ( Answer answer : answers ) {
            list.add( answerToAnswerResponseDto( answer ) );
        }

        return list;
    }

    @Override
    public Response memberToMemberResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        Response response = new Response();

        response.setMemberId( member.getMemberId() );
        response.setEmail( member.getEmail() );
        response.setName( member.getName() );

        return response;
    }
}
