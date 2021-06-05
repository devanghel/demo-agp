package com.example.demo.io;

import java.io.IOException;
import java.util.List;

// TODO: 03/06/2021 also add a webParser or something class for the abstract factory
public interface FileReader {

    <T> List<T> readMultipleEntries() throws IOException;

    <T> T readSingleEntry() throws IOException;

    default boolean hasMultipleEntries() throws IOException {
        return false;
    }
}
