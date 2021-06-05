package com.example.demo;

import com.example.demo.io.FileReader;
import com.example.demo.io.JsonFileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class ReaderConf {

    @Bean
    @Scope(scopeName = "singletone")
    public FileReader fileReader(ObjectMapper objectMapper) {
        return new JsonFileReader(objectMapper);
    }
}
