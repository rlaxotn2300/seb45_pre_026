package com.preproject.stackOverflow.answer.mapper;

import com.preproject.stackOverflow.answer.dto.AnswerDto;
import com.preproject.stackOverflow.answer.entity.Answer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-20T22:09:15+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.19 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

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
}
