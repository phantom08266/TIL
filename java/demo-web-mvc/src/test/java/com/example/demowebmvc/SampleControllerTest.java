package com.example.demowebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
    SpringRunner는 스프링에서 제공해주는 클래스이고 스프링 테스트를 진행할떼 좀더 효율적으로
    처리해준다. 내부적으로 ApplicationContext도 만들어 준다.
 */
@WebMvcTest // web관련 테스트만 진행할때 사용한다. 그러면 MockMvc 클래스를 사용할 수 있다.
@RunWith(SpringRunner.class)
public class SampleControllerTest {
    @Autowired
    MockMvc mockMvc;


    @Test
    public void hello() throws Exception {
        this.mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void helloNumber() throws Exception {
        this.mockMvc.perform(get("/events/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void helloPost() throws Exception {
        this.mockMvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void helloDelete() throws Exception {
        this.mockMvc.perform(delete("/events/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void helloPut() throws Exception {
        this.mockMvc.perform(put("/events/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
}