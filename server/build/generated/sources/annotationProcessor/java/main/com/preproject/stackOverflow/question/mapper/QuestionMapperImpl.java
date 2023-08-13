package com.preproject.stackOverflow.question.mapper;

<<<<<<< HEAD
import com.preproject.stackOverflow.question.dto.QuestionDto.Patch;
=======
>>>>>>> 9fd609c513c1476ded8cca48ea5369cf17346b1d
import com.preproject.stackOverflow.question.dto.QuestionDto.Response;
import com.preproject.stackOverflow.question.entity.Question;
import com.preproject.stackOverflow.question.entity.Question.QuestionStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-08-13T18:05:00+0900",
=======
    date = "2023-08-13T00:18:11+0900",
>>>>>>> 9fd609c513c1476ded8cca48ea5369cf17346b1d
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.19 (Azul Systems, Inc.)"
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
        String tag = null;
<<<<<<< HEAD
=======
        List<String> tags = null;
        int vote = 0;
>>>>>>> 9fd609c513c1476ded8cca48ea5369cf17346b1d
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;
        QuestionStatus questionStatus = null;

        questionId = response.getQuestionId();
        title = response.getTitle();
        content = response.getContent();
        tag = response.getTag();
<<<<<<< HEAD
=======
        List<String> list = response.getTags();
        if ( list != null ) {
            tags = new ArrayList<String>( list );
        }
        vote = response.getVote();
>>>>>>> 9fd609c513c1476ded8cca48ea5369cf17346b1d
        createdAt = response.getCreatedAt();
        modifiedAt = response.getModifiedAt();
        questionStatus = response.getQuestionStatus();

<<<<<<< HEAD
        List<String> tags = null;
        int vote = 0;

        Response response1 = new Response( questionId, title, content, tag, tags, vote, createdAt, modifiedAt, questionStatus );

        return response1;
    }

    @Override
    public Question questionPatchDtoToQuestion(Patch questionPatch) {
        if ( questionPatch == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionPatch.getQuestionId() );
        question.setTitle( questionPatch.getTitle() );
        question.setContent( questionPatch.getContent() );
        question.setTag( questionPatch.getTag() );
        List<String> list = questionPatch.getTagList();
        if ( list != null ) {
            question.setTagList( new ArrayList<String>( list ) );
        }

        return question;
    }
=======
        Response response1 = new Response( questionId, title, content, tag, tags, vote, createdAt, modifiedAt, questionStatus );

        return response1;
    }
>>>>>>> 9fd609c513c1476ded8cca48ea5369cf17346b1d
}
