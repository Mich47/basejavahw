package com.mich.webapp.exception;

public class ExistStorageException extends StorageException{
    public ExistStorageException(String uuid) {
        super("ERROR: Resume \"" + uuid + "\" already exist!");
    }
}
