package com.example.demospringboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
//@WebMvcTest({PersonFormatter.class, SampleController.class})
@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        this.mockMvc.perform(get("/hello/june"))
                .andDo(print())
                .andExpect(content().string("hello june"));
    }

    @Test
    public void hi() throws Exception {
        this.mockMvc.perform(get("/hi/lee"))
                .andDo(print())
                .andExpect(content().string("hi lee"));
    }

    @Test
    public void check() throws Exception {
        this.mockMvc.perform(get("/check").param("test","lee"))
                .andDo(print())
                .andExpect(content().string("check lee"));
    }
}