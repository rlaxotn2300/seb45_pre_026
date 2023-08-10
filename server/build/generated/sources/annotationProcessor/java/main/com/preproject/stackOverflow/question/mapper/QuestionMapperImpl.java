package com.preproject.stackOverflow.question.mapper;

import com.preproject.stackOverflow.question.dto.QuestionDto.Patch;
import com.preproject.stackOverflow.question.dto.QuestionDto.Post;
import com.preproject.stackOverflow.question.dto.QuestionDto.Response;
import com.preproject.stackOverflow.question.entity.Question;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-11T01:30:17+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.19 (Azul Systems, Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPostDtoToQuestion(Post questionPost) {
        if ( questionPost == null ) {
            return null;
        }

        Question question = new Question();

        question.setTitle( questionPost.getTitle() );
        question.setContent( questionPost.getContent() );
        question.setTag( questionPost.getTag() );
        List<String> list = questionPost.getTagList();
        if ( list != null ) {
            question.setTagList( new ArrayList<String>( list ) );
        }
        question.setCreatedAt( questionPost.getCreatedAt() );

        return question;
    }

    @Override
    public Response questionToQuestionResponseDto(Question question) {
        if ( question == null ) {
            return null;
        }

        Response response = new Response();

        return response;
    }

    @Override
    public Question questionPatchDtoToQuestion(Patch questionPatch) {
        if ( questionPatch == null ) {
            return null;
        }

        Question question = new Question();

        return question;
    }

    @Override
    public List<Response> questionsToQuestionResponseDtos(List<Question> question) {
        if ( question == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( question.size() );
        for ( Question question1 : question ) {
            list.add( questionToQuestionResponseDto( question1 ) );
        }

        return list;
    }
}
