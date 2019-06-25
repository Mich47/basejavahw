package com.mich.webapp.storage;

import com.mich.webapp.model.Resume;

import java.io.IOException;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    void clear();

    void save(Resume r);

    void update(Resume r);

    Resume get(String uuid) throws IOException;

    Resume get(Integer searchKey) throws IOException;

    void delete(String uuid);

    void delete(Integer searchKey);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    List<Resume> getAllSorted();

    int size();
}
