package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class AppConf {

    /*
    The project will evolve with time, and we will need to customize in one place and use everywhere
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }


}
