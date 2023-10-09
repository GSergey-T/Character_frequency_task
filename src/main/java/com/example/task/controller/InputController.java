package com.example.task.controller;

import com.example.task.service.LogicService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InputController {

    LogicService logicService;

    @Autowired
    public InputController(LogicService logicService) {
        this.logicService = logicService;
    }

    @PostMapping("/send")
    public ResponseEntity<Map<Character,Integer>> method(@RequestBody String str) {
        return ResponseEntity.ok(logicService.method(str));
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<String> handleException(TypeMismatchException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
