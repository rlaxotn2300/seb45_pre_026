package com.preproject.stackOverflow.question.mapper;

import com.preproject.stackOverflow.question.dto.QuestionDto.Response;
import com.preproject.stackOverflow.question.dto.QuestionDto.Vote;
import com.preproject.stackOverflow.question.entity.Question;
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

    @Override
    public Question questionVoteToQuestion(Vote questionVote) {
        if ( questionVote == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionVote.getQuestionId() );
        question.setVote( questionVote.getVote() );

        return question;
    }
}
