package com.mich.webapp.storage;

import com.mich.webapp.exception.ExistStorageException;
import com.mich.webapp.exception.NotExistStorageException;
import com.mich.webapp.model.Resume;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract boolean isOutSide(Integer searchKey);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey) throws IOException;

    protected abstract List<Resume> doCopyAll();

    public void save(Resume r) {
        LOG.info("Save \"" + r + "\"");
        SK searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void update(Resume r) {
        LOG.info("Update \"" + r + "\"");
        SK searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete \"" + uuid + "\"");
        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public void delete(Integer searchKey) {
        LOG.info("Delete #" + searchKey);
        isNotOutSideSearchKey(searchKey);
        searchKey--;
        doDelete((SK) searchKey);
    }

    @Override
    public Resume get(String uuid) throws IOException {
        LOG.info("Get \"" + uuid + "\"");
        SK searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public Resume get(Integer searchKey) throws IOException {
        LOG.info("Get #" + searchKey);
        isNotOutSideSearchKey(searchKey);
        searchKey--;
        return doGet((SK) searchKey);
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume \"" + uuid + "\" not exist!");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume \"" + uuid + "\" already exist!");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private void isNotOutSideSearchKey(Integer searchKey) {
        if (isOutSide(searchKey)) {
            LOG.warning("Resume #" + searchKey + " not exist!");
            throw new NotExistStorageException("");
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}