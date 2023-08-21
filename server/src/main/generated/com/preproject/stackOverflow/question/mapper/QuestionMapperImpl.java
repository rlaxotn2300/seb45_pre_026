package com.preproject.stackOverflow.question.mapper;

import com.preproject.stackOverflow.question.dto.QuestionDto.Response;
import com.preproject.stackOverflow.question.entity.Question;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T19:39:50+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.19 (Azul Systems, Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Response questionToQuestionResponseDto(Question response) {
        if ( response == null ) {
            return null;
        }

        Response response1 = new Response();

        response1.setQuestionId( response.getQuestionId() );
        response1.setTitle( response.getTitle() );
        response1.setContent( response.getContent() );
        List<String> list = response.getTags();
        if ( list != null ) {
            response1.setTags( new ArrayList<String>( list ) );
        }
        response1.setVote( response.getVote() );
        response1.setCreatedAt( response.getCreatedAt() );

        return response1;
    }
}
