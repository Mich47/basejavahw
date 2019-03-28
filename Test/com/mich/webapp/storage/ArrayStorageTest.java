package com.mich.webapp.storage;

import com.mich.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest{
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
    public void getAll() {
        Resume[] array = storage.getAll();
        Assert.assertEquals(3, array.length);
        Assert.assertEquals(RESUME_1, array[0]);
        Assert.assertEquals(RESUME_2, array[1]);
        Assert.assertEquals(RESUME_3, array[2]);
    }
}

