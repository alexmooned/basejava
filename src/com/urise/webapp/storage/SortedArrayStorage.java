package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    protected Integer findSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void saveResume(int index, Resume resume) {
        int indexSave = (-1) * index - 1;
        System.arraycopy(storage, indexSave, storage, indexSave + 1, size - indexSave);
        storage[indexSave] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        int indexDelete = size - index - 1;
        if (indexDelete > 0) {
            System.arraycopy(storage, index + 1, storage, index, indexDelete);
        }
    }
}
