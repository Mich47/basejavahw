package com.mich.webapp.storage;

import com.mich.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    public void getNum() {
        Assert.assertEquals(RESUME_3, storage.get(1));
        Assert.assertEquals(RESUME_2, storage.get(2));
        Assert.assertEquals(RESUME_1, storage.get(3));
    }
}
