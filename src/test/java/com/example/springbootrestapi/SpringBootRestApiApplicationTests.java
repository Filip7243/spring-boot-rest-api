package com.example.springbootrestapi;

import com.example.springbootrestapi.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest()
@AutoConfigureMockMvc
class SpringBootRestApiApplicationTests {

    @Autowired
    private StudentController studentController;

    @Test
    void contextLoads() {
        assertThat(studentController).isNotNull();
    }

}
