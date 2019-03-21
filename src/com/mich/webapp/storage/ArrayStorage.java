package com.mich.webapp.storage;

import com.mich.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    protected int indexOfUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertItem(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void deleteItem(int index) {
        storage[index] = storage[size - 1];
    }
}
