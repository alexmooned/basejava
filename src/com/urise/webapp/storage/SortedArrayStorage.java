package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("storage переполнен, добавить резюме нельзя!");
        } else if (index > -1) {
            System.out.println("Ошибка save: резюме с uuid = " + resume.getUuid() + " уже существует!");
        } else {
            System.arraycopy(storage, (-1) * index - 1, storage, (-1) * index, size);
            storage[(-1) * index - 1] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Ошибка delete: резюме с uuid = " + uuid + " нет!");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size);
            size--;
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
