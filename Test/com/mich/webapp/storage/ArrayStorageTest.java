package com.mich.webapp.storage;

import com.mich.webapp.exception.NotExistStorageException;
import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test
    public void getNum() {
        Assert.assertEquals(RESUME_1, storage.get(1));
        Assert.assertEquals(RESUME_2, storage.get(2));
        Assert.assertEquals(RESUME_3, storage.get(3));
    }

    @Test
    public void deleteNum() {
        storage.delete(1);
        Assert.assertEquals(2, storage.size());
    }


    @Test(expected = NotExistStorageException.class)
    public void deleteNumNotExist() {
        storage.delete(5);
    }
}

