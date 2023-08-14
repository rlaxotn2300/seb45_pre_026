//package com.preproject.stackOverflow.question.controller;
//
//import com.google.gson.Gson;
//import com.preproject.stackOverflow.StackOverflowApplication;
//import com.preproject.stackOverflow.question.dto.QuestionDto;
//import com.preproject.stackOverflow.question.entity.Question;
//import com.preproject.stackOverflow.question.mapper.QuestionMapper;
//import com.preproject.stackOverflow.question.repository.QuestionRepository;
//import com.preproject.stackOverflow.question.service.QuestionService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.mock;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@TestExecutionListeners(inheritListeners = false, listeners =
//        {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
//@ContextConfiguration(classes = StackOverflowApplication.class)
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
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
//   public void postQuestionTest() throws Exception {
//        // 준비: POST 요청에 사용할 QuestionDto.Post 객체 생성
//        Question question = new Question();
//
//        String content = gson.toJson(question);
//
//        given(questionMapper.questionPostDtoToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());
//        given(questionService.createQuestion(Mockito.any(Question.class))).willReturn(question);
//
//        ResultActions resultActions = mockMvc.perform(
//                post("/question/ask")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//        );
//
//        resultActions.andExpect(jsonPath("$.title").value(question.getTitle()));
//
//
//    }
//}
//
//
//
//
