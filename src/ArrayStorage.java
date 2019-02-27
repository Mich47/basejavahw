import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];


    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i]== null)
                break;
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i]==null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        int index;
        try {
            index = Integer.parseInt(uuid);
            if (index < storage.length && index > 0)
            {
                return storage[index - 1];
            }
            else
                return getUuid(uuid);
        }
        catch (Exception e) {
                return getUuid(uuid);
        }
    }

    void delete(String uuid) {
        int indexOfUuid = -1;

        try {
            indexOfUuid = Integer.parseInt(uuid) - 1;
            delUuid(indexOfUuid);

        }
        catch (Exception e) {
            indexOfUuid = indexOfUuid(uuid);
            delUuid(indexOfUuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] storageNew = Arrays.copyOf(storage, Arrays.asList(storage).indexOf(null));
        return storageNew;
    }

    int size() {
        int sizeArray = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null)
                break;
            else
                sizeArray++;
        }
        return sizeArray;
    }

    private int indexOfUuid(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid == uuid)
                return i;
            if (storage[i] == null)
                return -1;
        }
        return -1;
    }

    private void delUuid(int indexOfUuid){
        for (int i = indexOfUuid; i < storage.length; i++) {
            if (i < 0)
                break;
            if (storage[i] != null)
                storage[i] = storage[i + 1];
            else
            {
                break;
            }
        }
    }

    private Resume getUuid(String uuid){
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null)
                return null;
            if (storage[i].uuid == uuid)
                return storage[i];
        }
        return null;
    }
}
