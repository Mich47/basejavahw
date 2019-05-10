package com.mich.webapp;

import com.mich.webapp.model.Resume;
import com.mich.webapp.storage.MapUuidStorage;
import com.mich.webapp.storage.Storage;

/**
 * Test for your com.mich.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private final static Storage ARRAY_STORAGE = new MapUuidStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid2", "Name2");
        Resume r2 = new Resume("uuid1", "Name1");
        Resume r3 = new Resume("uuid3", "Name3");
        Resume r4 = new Resume("uuid0", "Name0");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r2.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        printAll();
        ARRAY_STORAGE.delete("1");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
