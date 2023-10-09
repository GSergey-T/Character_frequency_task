package com.example.task.controller;

import com.example.task.service.LogicService;
import org.hibernate.TypeMismatchException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.attribute.standard.Media;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InputControllerTest {

    @Mock
    LogicService logicService;

    @InjectMocks
    InputController inputController;

    @Test
    @DisplayName("Method-1")
    void method1() {

        //given
        String input = "aabbccc";
        Map<Character,Integer> hashMap = new HashMap<>();
        hashMap.put('a',2);
        hashMap.put('b',2);
        hashMap.put('c',3);

        Mockito.when(this.logicService.method(input)).thenReturn(hashMap);

        //when
        var responseEntity = this.inputController.method(input);

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(hashMap,responseEntity.getBody());

    }

    @Test
    @DisplayName("Method-2")
    void method2() {

        //given

        //when
        var responce = this.inputController.handleException(new TypeMismatchException("String make contains only alphabet symbols"));

        //then
        assertNotNull(responce);
        assertTrue(Objects.requireNonNull(responce.getBody()).contains("String make contains only alphabet symbols"));
        assertEquals(HttpStatus.BAD_REQUEST,responce.getStatusCode());
    }


}