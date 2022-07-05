import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int count = 0;
     void clear() {
        for (int i = 0; i < count; i++) {
            storage[i] = null;
        }
        count = size();
    }

    void save(Resume r) {
        storage[count] = r;
        count = size();
    }

    Resume get(String uuid) {
        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                index = i;
            }
        }
        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, size() - index);
        }
        count = size();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    int size() {
        return calculatedSize();
    }

    private int calculatedSize() {
        int cnt = 0;
        for (Resume resume : storage
        ) {
            if (resume != null)
                cnt++;
        }
        return cnt;
    }
}
