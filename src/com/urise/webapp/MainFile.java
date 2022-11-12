package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/com/urise/webapp");
        //System.out.println(dir.isDirectory());

        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                //System.out.println(name);
            }
        }

        File[] list2 = dir.listFiles();
        if (list2 != null) {
            for (File name : list2) {
                System.out.println(name.getName());
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            //System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //printFileName(dir);
    }

    public static void printFileName(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                } else if (file.isDirectory()) {
                    printFileName(file);
                }
            }
        }

    }
}
