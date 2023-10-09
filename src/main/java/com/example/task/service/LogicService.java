package com.example.task.service;

import org.hibernate.TypeMismatchException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LogicService {
    public Map<Character,Integer> method(String str) {
        if (!str.matches("^[a-zA-Z]*$")) {
            throw new TypeMismatchException("String make contains only alphabet symbols");
        }

        Map<Character,Integer> hashMap = new HashMap<>();
        char [] chars = str.toCharArray();
        for (char c: chars) {
            if (hashMap.containsKey(c)) {
                hashMap.put(c,hashMap.get(c) + 1);
            } else
                hashMap.put(c,1);
        }
        hashMap = hashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1,e2) -> e1,
                        LinkedHashMap::new
                ));
        return hashMap;
    }

}
