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
    protected void setIndexSave(int index, Resume resume) {
        System.arraycopy(storage, (-1) * index - 1, storage, (-1) * index, size);
        storage[(-1) * index - 1] = resume;
    }

    @Override
    protected void setIndexDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size);
    }
}
