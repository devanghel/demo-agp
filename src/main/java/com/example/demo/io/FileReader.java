package com.example.demo.io;

import java.util.Collection;
import java.util.List;

public interface FileReader {

    <T> List<T> readArray();

    <T> T readObject();

    default boolean isArray() {
        return false;
    }
}
