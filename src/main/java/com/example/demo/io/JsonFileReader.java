package com.example.demo.io;

import com.example.demo.exception.ObjectType;
import com.example.demo.exception.ObjectTypeDidNotMatchException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JsonFileReader implements FileReader {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${json.file.path}")
    private String filePath;

    @Override
    @SneakyThrows
    // TODO: 03/06/2021 push exception message trough mqqt
    public <T> List<T> readArray() throws IOException {
        if (isArray()) {
            return objectMapper.readValue(Paths.get(filePath).toFile(), List.class);
        } else {
            throw new ObjectTypeDidNotMatchException(ObjectType.OBJECT);
        }
    }

    @Override
    @SneakyThrows
    // TODO: 03/06/2021 think about using Object instead ot T
    public <T> T readObject() throws IOException {
        if (!isArray()) {
            return (T) objectMapper.readValue(Paths.get(filePath).toFile(), Object.class);
        } else {
            throw new ObjectTypeDidNotMatchException(ObjectType.OBJECT);
        }
    }

    @Override
    public boolean isArray() throws IOException {
        return objectMapper.readTree(new File(filePath)).isArray();
    }
}
