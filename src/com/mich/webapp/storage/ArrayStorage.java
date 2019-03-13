package com.mich.webapp.storage;

import com.mich.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage{
    private static final int STORAGE_LIMIT = 3;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (indexOfUuid(r.getUuid()) >= 0) {
            System.out.println("ERROR: Item is present!");
        } else {
            if (size < STORAGE_LIMIT) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("ERROR: The Array is full!");
            }
        }
    }

    public void update(String uuid) {
        int i = indexOfUuid(uuid);
        if (i >= 0) {
            storage[i].setUuid(storage[i].getUuid() + "_update");
        } else {
            System.out.println("ERROR: Item is NOT present!");
        }
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

    public void delete(String uuid) {
        if (size == 0) {
            System.out.println("ERROR: Array is empty!");
        } else {
            int i = indexOfUuid(uuid);
            if (i < 0) {
                System.out.println("ERROR: Item not found!");
            } else {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int indexOfUuid(String uuid) {
        try {
            int i = Integer.parseInt(uuid);
            if ((i <= size) && (i > 0)) {
                return (i - 1);
            }
        } catch (Exception e) {
        } finally {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    return i;
                }
            }
        }
        return -1;
    }
}
