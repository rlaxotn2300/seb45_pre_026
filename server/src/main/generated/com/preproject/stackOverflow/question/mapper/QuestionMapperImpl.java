package com.preproject.stackOverflow.question.mapper;

import com.preproject.stackOverflow.question.dto.QuestionDto.Response;
import com.preproject.stackOverflow.question.entity.Question;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-16T21:39:26+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.19 (Azul Systems, Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Response questionToQuestionResponseDto(Question response) {
        if ( response == null ) {
            return null;
        }

        long questionId = 0L;
        String title = null;
        String content = null;
        List<String> tags = null;
        long vote = 0L;
        LocalDateTime createdAt = null;

        questionId = response.getQuestionId();
        title = response.getTitle();
        content = response.getContent();
        List<String> list = response.getTags();
        if ( list != null ) {
            tags = new ArrayList<String>( list );
        }
        vote = response.getVote();
        createdAt = response.getCreatedAt();

        Response response1 = new Response( questionId, title, content, tags, vote, createdAt );

        return response1;
    }
}
