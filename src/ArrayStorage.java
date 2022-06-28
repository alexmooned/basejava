import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Object get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        int sizeArray = size();
        for (int i = 0; i < sizeArray; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = null;
            }
        }
        for (int i = 0; i < sizeArray; i++) {
            if (storage[i] == null && i != sizeArray - 1) {
                storage[i] = storage[i + 1];
                storage[i + 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int cnt = 0;
        for (Resume resume : storage
        ) {
            if (resume != null)
                cnt++;
        }
        return cnt;
    }
}
