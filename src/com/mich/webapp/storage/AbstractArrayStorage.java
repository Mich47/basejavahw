package com.mich.webapp.storage;

import com.mich.webapp.exception.ExistStorageException;
import com.mich.webapp.exception.NotExistStorageException;
import com.mich.webapp.exception.StorageException;
import com.mich.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 5;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR: The Storage is full!", r.getUuid());
        } else {
            int index = indexOfUuid(r.getUuid());
            if (index >= 0) {
                throw new ExistStorageException(r.getUuid());
            } else {
                insertResume(r, index);
                size++;
            }
        }
    }

    public void update(Resume r) {
        if (size == 0) {
            throw new StorageException("ERROR: Storage is empty!", "");
        } else {
            int i = indexOfUuid(r.getUuid());
            if (i >= 0) {
                storage[i] = r;
            } else {
                throw new NotExistStorageException(r.getUuid());
            }
        }
    }

    //Overload
    public void delete(String uuid) {
        if (size == 0) {
            throw new StorageException("ERROR: Storage is empty!", uuid);
        } else {
            int i = indexOfUuid(uuid);
            if (i < 0) {
                throw new NotExistStorageException(uuid);
            } else {
                deleteResume(i);
                storage[size - 1] = null;
                size--;
            }
        }
    }

    //Overload
    public void delete(int uuidNum) {
        if (size == 0) {
            throw new StorageException("ERROR: Storage is empty!", "");
        } else if ((uuidNum < 1) || (uuidNum > size)) {
            throw new NotExistStorageException("");
        } else {
            deleteResume(uuidNum - 1);
            storage[size - 1] = null;
            size--;
        }
    }

    public int size() {
        return size;
    }

    //Overload
    public Resume get(String uuid) {
        if (size == 0) {
            throw new StorageException("ERROR: Storage is empty!", uuid);
        }
        int i = indexOfUuid(uuid);
        if (i < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[i];
    }

    //Overload
    public Resume get(int uuidNum) {
        if (size == 0) {
            throw new StorageException("ERROR: Storage is empty!", "");
        } else if ((uuidNum < 1) || (uuidNum > size)) {
            throw new NotExistStorageException("");
        } else {
            return storage[uuidNum - 1];
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int indexOfUuid(String uuid);

    protected abstract void insertResume(Resume r, int index);

    protected abstract void deleteResume(int index);
}
