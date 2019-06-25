package com.mich.webapp.storage;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test
    public void getNum() throws IOException {
        Assert.assertEquals(RESUME_1, storage.get(1));
        Assert.assertEquals(RESUME_2, storage.get(2));
        Assert.assertEquals(RESUME_3, storage.get(3));
    }
}

