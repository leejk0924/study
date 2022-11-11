package com.example.hodol.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/get 요청 시 Hello World 를 출력")
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("get : Hello World"));
    }
    // form 타입
/*    @Test
    @DisplayName("/post 요청 시 Hello World 를 출력")
    void post() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title","글 제목입니다.")
                        .param("content", "글 내용입니다.")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("post : Hello World"))
                .andDo(MockMvcResultHandlers.print());
    }*/

    // json 타입
    @Test
    @DisplayName("/post 요청 시 Hello World 를 출력")
    void post() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다.\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("post : Hello World"))
                .andDo(MockMvcResultHandlers.print());
    }
}