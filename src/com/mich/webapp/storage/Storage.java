package com.mich.webapp.storage;

import com.mich.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    void clear();

    void save(Resume r);

    void update(Resume r);

    //Overload
    Resume get(String uuid);

    //Overload
    Resume get(Integer searchKey);

    //Overload
    void delete(String uuid);

    //Overload
    void delete(Integer searchKey);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    int size();
}
