package com.example.task.controller;

import net.bytebuddy.build.Plugin;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class InputControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testIsComplete() throws Exception {
        //given
        var request = MockMvcRequestBuilders.post("/send")
                .contentType(MediaType.TEXT_HTML)
                .content("aabbccc");
        //when
        this.mockMvc.perform(request)
        //then
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(
                                    """
                                    {
                                    "c":3,
                                    "a":2,
                                    "b":2
                                    }
                                    """
                        )
                );
    }

    @Test
    void testIsFailure() throws Exception {

        //given
        var request = MockMvcRequestBuilders.post("/send")
                .contentType(MediaType.TEXT_HTML)
                .content("aabbccc123");

        //when
        this.mockMvc.perform(request)

        //then
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType("text/plain;charset=UTF-8"),
                        content().string("String make contains only alphabet symbols")
                );
    }


}