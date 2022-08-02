package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveResume(int index, Resume resume) {
        int idxSave = (-1) * index - 1;
        System.arraycopy(storage, idxSave, storage, idxSave + 1, size - idxSave);
        storage[idxSave] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        int IdxDelete = size - index - 1;
        if (IdxDelete > 0) {
            System.arraycopy(storage, index + 1, storage, index, IdxDelete);
        }
    }
}
