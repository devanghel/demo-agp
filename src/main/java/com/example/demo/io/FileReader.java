package com.example.demo.io;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

// TODO: 03/06/2021 also add a webParser class for the abstract factory
public interface FileReader {

    <T> Optional<List<Optional<T>>> readMultipleEntries() throws IOException;

    <T> Optional<Object> readSingleEntry() throws IOException;

    default boolean hasMultipleEntries() throws IOException {
        return false;
    }
}
