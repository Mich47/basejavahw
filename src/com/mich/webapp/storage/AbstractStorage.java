package com.mich.webapp.storage;

import com.mich.webapp.exception.ExistStorageException;
import com.mich.webapp.exception.NotExistStorageException;
import com.mich.webapp.exception.StorageException;
import com.mich.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract boolean isOutSide(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    //Overload
    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    //Overload
    public void delete(Integer searchKey) {
        isNotOutSideSearchKey(searchKey);
        searchKey--;
        doDelete(searchKey);
    }

    //Overload
    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    //Overload
    public Resume get(Integer searchKey) {
        isNotOutSideSearchKey(searchKey);
        searchKey--;
        return doGet(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private void isNotOutSideSearchKey(int searchKey) {
        if (isOutSide(searchKey)) {
            throw new NotExistStorageException("");
        }
    }


}