package com.example.task.service;

import org.hibernate.TypeMismatchException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LogicServiceTest {

    @InjectMocks
    LogicService logicService;

    @Test
    @DisplayName("Method One")
    void method1() {

        //Given
        String str = "aabbccc";
        Map<Character,Integer> hashMap = new HashMap<>();
        hashMap.put('a',2);
        hashMap.put('b',2);
        hashMap.put('c',3);

        //When
        var result = logicService.method(str);

        //Then
        assertEquals(hashMap, result);
    }

    @Test
    @DisplayName("Method Two")
    void method2() {

        //Given
        String str = "aabbccc123";
        String expectedMessage = "String make contains only alphabet symbols";

        //When
        Exception exception = assertThrows(TypeMismatchException.class, () -> {
            logicService.method(str);
        });

        //Then
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains(expectedMessage));

    }
}