package com.mich.webapp.storage;

import com.mich.webapp.exception.NotExistStorageException;
import org.junit.Assert;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
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