package com.stakater.kubernetes.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Web MVC test for {@link GreetingController}
 *
 * @author Nikhil Vibhav
 */
@WebMvcTest(GreetingController.class)
@TestPropertySource(properties = {"NAME=Nikhil Vibhav"})
public class GreetingControllerMvcTest {

    private static final String URI = "/api/v1/kubernetes-demo";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenRequest_WhenGreet_ThenReturn200_OK() throws Exception {
        mockMvc.perform(get(URI + "/greet")
                .accept(MediaType.TEXT_PLAIN_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Nikhil Vibhav"));
    }

}
