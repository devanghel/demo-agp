package com.example.demo.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoConf {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
