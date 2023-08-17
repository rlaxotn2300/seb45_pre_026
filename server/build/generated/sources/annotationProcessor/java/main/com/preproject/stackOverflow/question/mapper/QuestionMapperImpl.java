package com.preproject.stackOverflow.question.mapper;

import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.question.dto.QuestionDto.Response;
import com.preproject.stackOverflow.question.dto.QuestionDto.Vote;
import com.preproject.stackOverflow.question.entity.Question;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-08-17T19:43:16+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.19 (Azul Systems, Inc.)"
=======
    date = "2023-08-17T17:51:27+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.20 (Azul Systems, Inc.)"
>>>>>>> fe3c497297c3c8404fe44297aba546c706977a66
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

        Answer answer = null;

        Response response1 = new Response( answer, questionId, title, content, tags, vote, createdAt );

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
