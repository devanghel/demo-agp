package com.example.demo.entry;

import com.example.demo.conf.rabitmq.routes.RabbitMqProducerRoute;
import com.example.demo.io.JsonFileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class EntryPointController {

    @Autowired
    private RabbitMqProducerRoute rabbitMqProducerRoute;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("start")
    public void start() {
        new JsonFileReader(rabbitMqProducerRoute, objectMapper);
    }

}
