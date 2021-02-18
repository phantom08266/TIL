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

    @Autowired
    PersonRepository personRepository;
    @Test
    public void hello() throws Exception {
        Person person = new Person();
        person.setName("june");
        personRepository.save(person);

        System.out.println(person.getName() + " " + person.getId());
        this.mockMvc.perform(get("/hello")
                    .param("id",person.getId().toString()))
                .andDo(print())
                .andExpect(content().string("hello june"));
    }
}