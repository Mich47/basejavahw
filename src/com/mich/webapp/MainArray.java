package com.mich.webapp;

import com.mich.webapp.exception.NotExistStorageException;
import com.mich.webapp.exception.StorageException;
import com.mich.webapp.model.Resume;
import com.mich.webapp.storage.MapStorage;
import com.mich.webapp.storage.SortedArrayStorage;
import com.mich.webapp.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Interactive test for com.mich.webapp.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private final static Storage ARRAY_STORAGE = new MapStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | save uuid | update uuid | del uuid | del# uuid | get uuid | get# uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    r = new Resume(uuid);
                    ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "update":
                    r = new Resume(uuid);
                    ARRAY_STORAGE.update(r);
                    printAll();
                    break;
                case "del":
                    ARRAY_STORAGE.delete(uuid);
                    printAll();
                    break;
                case "del#":
                    try {
                        int i = Integer.parseInt(uuid);
                        ARRAY_STORAGE.delete(i);
                        printAll();
                    } catch (StorageException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "get":
                    System.out.println(ARRAY_STORAGE.get(uuid));
                    break;
                case "get#":
                    try {
                        int i = Integer.parseInt(uuid);
                        System.out.println(ARRAY_STORAGE.get(i));
                    } catch (StorageException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    private static void printAll() {
        Resume[] all = ARRAY_STORAGE.getAll();
        System.out.println("----------------------------");
        if ((all.length == 0)) {
            System.out.println("Empty");
        } else {
            //for (Resume r : all) {
            for (int i = 0; i < all.length; i++) {
                System.out.println((i + 1) + ". " + all[i].getUuid());
            }
        }
        System.out.println("----------------------------");
    }
}