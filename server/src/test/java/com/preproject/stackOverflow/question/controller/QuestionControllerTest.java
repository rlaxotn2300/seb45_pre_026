//package com.preproject.stackOverflow.question.controller;
//
//import com.google.gson.Gson;
//import com.preproject.stackOverflow.question.dto.QuestionDto;
//import com.preproject.stackOverflow.question.entity.Question;
//import com.preproject.stackOverflow.question.mapper.QuestionMapper;
//import com.preproject.stackOverflow.question.repository.QuestionRepository;
//import com.preproject.stackOverflow.question.service.QuestionService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest //@WevMvcTest
//@AutoConfigureMockMvc
//@EnableWebMvc
//class QuestionControllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private QuestionService questionService;
//
//    @Autowired
//    private QuestionMapper questionMapper;
//
//    @Autowired
//    private QuestionRepository questionRepository;
//
//    @Autowired
//    private Gson gson;
//
//
//
//    @Test
//    public void postQuestionTest() throws Exception {
//        //given
//        QuestionDto.Post request = new QuestionDto.Post();
//        request.setTitle("title");
//        request.setContent("content");
////        request.setCreatedAt(LocalDateTime.now());
//        request.setQUESTION_ASKED(Question.QuestionStatus.QUESTION_ASKED);
//        request.setTagList(List.of("tag1", "tag2"));
//
//        QuestionDto.Response response = new QuestionDto.Response(
//                1L,
//                request.getTitle(),
//                request.getContent(),
//                request.getTag(),
//                request.getTagList(),
//                0,
//                request.getCreatedAt(),
//                request.getCreatedAt(),
//                request.getQUESTION_ASKED()
//        );
//
//        Question question = new Question();
//        question.setQuestionId(1L);
//        question.setTitle(request.getTitle());
//        question.setContent(request.getContent());
//        question.setQuestionStatus(request.getQUESTION_ASKED());
//        question.setTags(request.getTagList());
//
//        given(questionService.createQuestion(any(Question.class))).willReturn(question);
//
//        ResultActions resultActions = mockMvc.perform(
//                post("/question/ask")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(gson.toJson(request))
//        );
//
//        resultActions.andExpect(jsonPath("$.data.title").value(response.getTitle()));
//
//
//    }
//}
//
