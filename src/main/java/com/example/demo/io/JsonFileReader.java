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

    @Value("${file.folder.path}")
    private String fileFolder;

    @Override
    @SneakyThrows
    // TODO: 03/06/2021 push exception message trough mqqt
    // TODO: 03/06/2021 if you want to make it trully genereic think of a better naming
    public <T> List<T> readMultipleEntries() throws IOException {
        if (hasMultipleEntries()) {
            // TODO: 03/06/2021 implement login for .json type file parsing, and validation
            return objectMapper.readValue(Paths.get(fileFolder).toFile(), List.class);
        } else {
            throw new ObjectTypeDidNotMatchException(ObjectType.MULTIPLE_ENTRIES);
        }
    }

    @Override
    @SneakyThrows
    // TODO: 03/06/2021 think about using Object instead ot T
    public <T> T readSingleEntry() throws IOException {
        if (!hasMultipleEntries()) {
            return (T) objectMapper.readValue(Paths.get(fileFolder).toFile(), Object.class);
        } else {
            throw new ObjectTypeDidNotMatchException(ObjectType.SINGLE_ENTRY);
        }
    }

    @Override
    public boolean hasMultipleEntries() throws IOException {
        return objectMapper.readTree(new File(fileFolder)).isArray();
    }
}
