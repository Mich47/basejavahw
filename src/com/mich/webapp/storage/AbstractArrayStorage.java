package com.mich.webapp.storage;

import com.mich.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 5;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = indexOfUuid(r.getUuid());
        if (index >= 0) {
            System.out.println("ERROR: Item is present!");
        } else {
            if (size < STORAGE_LIMIT) {
                insertItem(r, index);
                size++;
            } else {
                System.out.println("ERROR: The Array is full!");
            }
        }
    }

    public void update(String uuid) {
        int i = indexOfUuid(uuid);
        if (i >= 0) {
            storage[i].setUuid(uuid);
        } else {
            System.out.println("ERROR: Item is NOT present!");
        }
    }

    public void delete(String uuid) {
        if (size == 0) {
            System.out.println("ERROR: Array is empty!");
        } else {
            int i = indexOfUuid(uuid);
            if (i < 0) {
                System.out.println("ERROR: Item not found!");
            } else {
                deleteItem(i);
                size--;
            }
        }
    }

    public void delete(int uuidNum) {
        if (size == 0) {
            System.out.println("ERROR: Array is empty!");
        } else if ((uuidNum < 1) || (uuidNum > size)) {
            System.out.println("ERROR: Item not found!");
        } else {
            deleteItem(uuidNum - 1);
            size--;
        }
    }

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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int indexOfUuid(String uuid);

    protected abstract void insertItem(Resume r, int index);

    protected abstract void deleteItem(int index);
}
