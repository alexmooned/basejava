package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
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

    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow!", resume.getUuid());
        } else {
            saveResume((Integer) index, resume);
            size++;
        }
    }

    @Override
    protected void doDelete(Object index) {
        deleteResume((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract void saveResume(int index, Resume resume);

    protected abstract void deleteResume(int index);

    protected abstract Integer findSearchKey(String uuid);
}
