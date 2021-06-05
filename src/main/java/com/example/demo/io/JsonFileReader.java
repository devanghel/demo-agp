package com.example.demo.io;

import com.example.demo.conf.rabitmq.routes.RabbitMqProducerRoute;
import com.example.demo.exception.ObjectType;
import com.example.demo.exception.ObjectTypeDidNotMatchException;
import com.example.demo.model.bid.Bid;
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

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(ioThreadNum);

    @Override
    @SneakyThrows
    public <T> List<T> readMultipleEntries() throws IOException {
        if (hasMultipleEntries()) {
            // TODO: 03/06/2021 implement login for .json type file parsing, and validation
            // TODO: 05/06/2021 push to broker
            objectMapper.readValue(Paths.get(fileFolder).toFile(), List.class).stream()
                    .forEach(it -> fixedThreadPool.submit(() -> {
                        rabbitMqProducerRoute
                                .setRouteId(((Bid)it).getTy());
                        rabbitMqProducerRoute.setRouteId("kekRouteId");
                        rabbitMqProducerRoute.setBody(it.toString());
                    }));
            return rabbitMqProducerRoute;
        } else {
            throw new ObjectTypeDidNotMatchException(ObjectType.MULTIPLE_ENTRIES);
        }
    }

    @Override
    @SneakyThrows
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
