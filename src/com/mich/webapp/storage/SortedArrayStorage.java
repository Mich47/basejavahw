package com.mich.webapp.storage;

import com.mich.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    /*
    private static final class ResumeComparator implements Comparator<Resume> {

        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }


    private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {

        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };
    */

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void insertResume(Resume r, int index) {
        //http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected void deleteResume(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }
}
