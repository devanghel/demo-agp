package com.example.demo.io;

import com.example.demo.conf.rabitmq.routes.RabbitMqProducerRoute;
import com.example.demo.exception.ObjectType;
import com.example.demo.exception.ObjectTypeDidNotMatchException;
import com.example.demo.model.bid.Bid;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RequiredArgsConstructor
public class JsonFileReader implements FileReader {

    private final RabbitMqProducerRoute rabbitMqProducerRoute;

    private final ObjectMapper objectMapper;

    @Value("${file.folder.path}")
    private String fileFolder;

//    https://stackoverflow.com/a/42015922
    @Value("${io.thread.num}")
    private int ioThreadNum;

    private final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(ioThreadNum);
    @Override
    @SneakyThrows
    public <T> Optional<List<Optional<T>>> readMultipleEntries() {
        if (hasMultipleEntries()) {
            // TODO: 03/06/2021 implement login for .json type file parsing, and validation
            objectMapper.readValue(Paths.get(fileFolder).toFile(), List.class)
                    .forEach(this::sendMessageInANewThread);
            return Optional.empty();
        } else {
            throw new ObjectTypeDidNotMatchException(ObjectType.MULTIPLE_ENTRIES);
        }
    }

    @Override
    @SneakyThrows
    public <T> Optional<Object> readSingleEntry() {
        if (!hasMultipleEntries()) {
            sendMessageInANewThread(objectMapper.readValue(Paths.get(fileFolder).toFile(), Object.class));

            return Optional.empty();
        } else {
            throw new ObjectTypeDidNotMatchException(ObjectType.SINGLE_ENTRY);
        }
    }

    private <T> void sendMessageInANewThread(T it) {
        fixedThreadPool.submit(() -> {
            rabbitMqProducerRoute.setBody(((Bid) it).getPl());
        });
    }

    @Override
    public boolean hasMultipleEntries() throws IOException {
        return objectMapper.readTree(new File(fileFolder)).isArray();
    }
}
