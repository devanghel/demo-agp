package com.example.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class Demo1ApplicationTest {


    @BeforeEach
    public void setUp() {

    }

    @Test
    void green() {
        Assert.assertEquals(10L, 10L);
    }

    @Test
    void red() {
        Assert.assertNotEquals(10L, 0L);
    }

    @AfterEach
    public void tearDown() {

    }
}