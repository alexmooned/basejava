package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Ошибка update: резюме с uuid = " + resume.getUuid() + " нет!");
        } else {
            storage[index] = resume;
        }
    }

    public final void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("storage переполнен, добавить резюме нельзя!");
        } else if (index > -1) {
            System.out.println("Ошибка save: резюме с uuid = " + resume.getUuid() + " уже существует!");
        } else {
            saveResume(index, resume);
            size++;
        }
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Ошибка delete: резюме с uuid = " + uuid + " нет!");
        } else {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Ошибка get: резюме с uuid = " + uuid + " нет!");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int findIndex(String uuid);

    protected abstract void saveResume(int index, Resume resume);

    protected abstract void deleteResume(int index);
}
