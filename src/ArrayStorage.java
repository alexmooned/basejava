import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int sizeArray = 0;
    void clear() {
        for (int i = 0; i < sizeArray; i++) {
            storage[i] = null;
        }
        sizeArray = size();
    }

    void save(Resume r) {
        storage[sizeArray] = r;
        sizeArray = size();
    }

    Object get(String uuid) {
        for (int i = 0; i < sizeArray; i++) {
            if (storage[i].uuid.equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        int index = -1;
        for (int i = 0; i < sizeArray; i++) {
            if (storage[i].uuid.equals(uuid)) {
                index = i;
            }
        }
        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, sizeArray - index);
        }
        sizeArray = size();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, sizeArray);
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
