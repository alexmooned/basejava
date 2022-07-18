package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;
    private int index;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume resume) {
        index = uuidInStorage(resume.getUuid(), "update");
        if (index > -1) {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        index = uuidInStorage(resume.getUuid(), "save");
        if (size == 10000) {
            System.out.println("storage переполнен, добавить резюме нельзя!");
            return;
        } else if (index == -1) {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        index = uuidInStorage(uuid, "get");
        if (index > -1) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        index = uuidInStorage(uuid, "delete");
        if (index > -1) {
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

    private int uuidInStorage(String uuid, String action) {
        index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
            }
        }
        if (index == -1 && (action == "get" || action == "update" || action == "delete")) {
            System.out.println("Ошибка " + action + ": резюме с uuid = " + uuid + " нет!");
        } else if
        (index > -1 && action == "save") {
            System.out.println("Ошибка " + action + ": резюме с uuid = " + uuid + " уже существует!");
        }
        return index;
    }
}
