package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void setIndexSave(int index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void setIndexDelete(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }
}
