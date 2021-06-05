package com.example.demo.io;

import com.example.demo.conf.rabitmq.routes.RabbitMqProducerRoute;
import com.example.demo.io.FileReader;
import com.example.demo.io.JsonFileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class ReaderConf {

    @Bean
    @Scope(scopeName = "singletone")
    public FileReader fileReader(RabbitMqProducerRoute rabbitMqProducerRoute, ObjectMapper objectMapper) {
        return new JsonFileReader(rabbitMqProducerRoute, objectMapper);
    }
}
