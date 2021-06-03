package com.example.demo.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class JsonFileReader implements FileReader {

    @Value("${json.file.path}")
    private String filePath;

    public void read(boolean isArray) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // TODO: 03/06/2021 think of a more generic way to parse any valid json structure be it array or obj
            Map<?, ?> map = mapper.readValue(Paths.get(filePath).toFile(), Map.class);

            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        }catch (IOException ioException) {
            // TODO: 03/06/2021 if profile dev, print stacktrace
            log.error(ioException.getMessage());
        }
    }

    @Override
    public <T> List<T> readArray() {
        return read();
    }

    @Override
    public <T> T readObject() {
        return read(isArray());
    }

    @Override
    public boolean isArray() {
        return FileReader.super.isArray();
    }
}
