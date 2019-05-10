package com.mich.webapp.storage;

import com.mich.webapp.exception.StorageException;
import com.mich.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 5;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insertResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doSave(Resume r, Object index) {
        isFull();
        insertResume(r, (Integer) index);
        size++;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        isEmpty();
        storage[(Integer) index] = r;
    }

    @Override
    protected void doDelete(Object index) {
        isEmpty();
        deleteResume((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    @Override
    protected Resume doGet(Object index) {
        isEmpty();
        return storage[(Integer) index];
    }

    private void isEmpty() {
        if (size == 0) {
            throw new StorageException("ERROR: Storage is empty!", "");
        }
    }

    private void isFull() {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR: The Storage is full!", "");
        }
    }

    @Override
    protected boolean isOutSide(Object index) {
        return ((int) index < 1) || ((int) index > size);
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }
}
