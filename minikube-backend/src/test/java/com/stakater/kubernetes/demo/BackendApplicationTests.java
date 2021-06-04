package com.stakater.kubernetes.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for Minikube Backend application
 *
 * @author Nikhil Vibhav
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"NAME=Nikhil Vibhav"})
class BackendApplicationTests {

    private static final String URI = "/api/v1/kubernetes-demo";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greets() throws Exception {
        mockMvc.perform(get(URI + "/greet")
                .accept(MediaType.TEXT_PLAIN_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Nikhil Vibhav"));
    }

}
