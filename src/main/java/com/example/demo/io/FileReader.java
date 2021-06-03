package com.example.demo.io;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface FileReader {

    // TODO: 03/06/2021 think or research if it is posible to generify List and Object to make a single method read that will return both
    <T> List<T> readMultipleEntries() throws IOException;

    <T> T readSingleEntry() throws IOException;

    default boolean hasMultipleEntries() throws IOException {
        return false;
    }
}
