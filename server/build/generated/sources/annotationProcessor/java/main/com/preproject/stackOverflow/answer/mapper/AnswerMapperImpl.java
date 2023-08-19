package com.preproject.stackOverflow.answer.mapper;

import com.preproject.stackOverflow.answer.dto.AnswerDto;
import com.preproject.stackOverflow.answer.entity.Answer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-08-18T23:53:05+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.19 (Azul Systems, Inc.)"
=======
    date = "2023-08-19T13:51:26+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.20 (Azul Systems, Inc.)"
>>>>>>> 1aee9f415e204688095dcdeacb413df1de9e943d
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
}
