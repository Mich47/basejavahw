package com.mich.webapp.storage;

import com.mich.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 3;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        if (size == 0) {
            System.out.println("ERROR: Array is empty!");
            return null;
        }
        int i = indexOfUuid(uuid);
        if (i < 0) {
            System.out.println("ERROR: Item not found!");
            return null;
        }
        return storage[i];
    }

    protected abstract int indexOfUuid(String uuid);
}
