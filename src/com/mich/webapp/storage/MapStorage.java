package com.mich.webapp.storage;

import com.mich.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<Integer, Resume> map = new HashMap<>();
    private Integer index = 0;

    @Override
    protected Object getSearchKey(String uuid) {
        for (Map.Entry<Integer, Resume> entry : map.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put((Integer) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
        index--;
        //map = new HashMap<>(map);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected boolean isOutSide(Object searchKey) {
        return ((int) searchKey < 1) || ((int) searchKey > map.values().size());
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(index, r);
        index++;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        //Number key = (Integer) searchKey;
        return map.get(searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return map.values().size();
    }
}
