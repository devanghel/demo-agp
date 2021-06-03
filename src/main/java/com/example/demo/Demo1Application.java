package com.example.demo;


import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NoArgsConstructor
public class Demo1Application {

    public static void main(String[] argsSpringApplication) {
        SpringApplication.run(Demo1Application.class, argsSpringApplication).close();
    }

}
