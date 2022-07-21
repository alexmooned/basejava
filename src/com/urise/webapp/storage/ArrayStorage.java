package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT  = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT ];
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Ошибка update: резюме с uuid = " + resume.getUuid() + " нет!");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT ) {
            System.out.println("storage переполнен, добавить резюме нельзя!");
        } else if (findIndex(resume.getUuid()) > -1) {
            System.out.println("Ошибка save: резюме с uuid = " + resume.getUuid() + " уже существует!");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Ошибка get: резюме с uuid = " + uuid + " нет!");
            return null;
        }
        return storage[index];

    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Ошибка delete: резюме с uuid = " + uuid + " нет!");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
