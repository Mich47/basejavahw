package com.mich.webapp.storage;

import com.mich.webapp.exception.StorageException;
import com.mich.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest extends AbstractStorageTest{
    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test(expected = StorageException.class)
    public void overflow() {
        try {
            for (int i = storage.size(); i < ArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
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

