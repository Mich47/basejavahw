package com.mich.webapp.storage;

import com.mich.webapp.exception.ExistStorageException;
import com.mich.webapp.exception.NotExistStorageException;
import com.mich.webapp.exception.StorageException;
import com.mich.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {

    protected Storage storage;

    protected static final String UUID_1 = "uuid3";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid1";
    private static final String UUID_4 = "uuid4";

    protected static final Resume RESUME_1;
    protected static final Resume RESUME_2;
    protected static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_4 = new Resume(UUID_4);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }


    @Test
    public void update() {
        Resume r = new Resume(UUID_2);
        storage.update(r);
        Assert.assertSame(r, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume r = new Resume("update");
        storage.update(r);
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        assertSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("qwerty");
    }

    @Test
    public void deleteNum() {
        storage.delete(1);
        assertSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNumNotExist() {
        storage.delete(5);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("qweewew");
    }

    @Test(expected = StorageException.class)
    public void getEmpty() {
        storage.clear();
        storage.get(UUID_2);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }
}