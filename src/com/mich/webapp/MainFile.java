package com.mich.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src");
        System.out.println(dir.isDirectory());
        showAllFiles(dir);

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showAllFiles(File file) throws IOException {
        if (file.isDirectory()) {
            System.out.println(file.getCanonicalPath());
            File[] list = file.listFiles();
            if (list != null) {
                for (int i = list.length; --i >= 0; ) {
                    showAllFiles(list[i]);
                }
            }
        } else {
            System.out.println(file.getCanonicalPath());
        }
    }
}
