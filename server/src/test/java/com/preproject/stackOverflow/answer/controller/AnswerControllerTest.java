package com.preproject.stackOverflow.answer.controller;

import com.google.gson.Gson;
import com.preproject.stackOverflow.answer.mapper.AnswerMapper;
import com.preproject.stackOverflow.answer.service.AnswerService;
import com.preproject.stackOverflow.question.service.QuestionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AnswerControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

//    @MockBean
//    private MemberRepository memberRepository;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private AnswerMapper mapper;

    @Autowired
    private Gson gson;

//    @Autowired
//    private JwtTokenizer jwtTokenizer;
//
//    private String accessToken;

//    @BeforeAll
//    public void init() {
//        accessToken = StubData.MockSecurity.getValidAccessToken(jwtTokenizer.getSecretKey());
//    }


    @Test
    @DisplayName("Answer Create Test")
    public void postAnswerTest() throws Exception {
        //given

        AnswerPostDto.Post post = new AnswerPostDto.Post();
        post.setQuestionId(1L);
        post.setAnswerContent("Answer.Post 테스트");
        String content = gson.toJson(post);

        given(mapper.answerPostToAnswer(Mockito.any(Answerdto.Post.class))).willReturn(new Answer());
    }

}