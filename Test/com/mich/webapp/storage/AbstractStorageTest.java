package com.mich.webapp.storage;

import com.mich.webapp.exception.ExistStorageException;
import com.mich.webapp.exception.NotExistStorageException;
import com.mich.webapp.exception.StorageException;
import com.mich.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected final static File STORAGE_DIR = new File("C:\\storage");

    protected Storage storage;

    protected static final String UUID_1 = "uuid3";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid1";
    private static final String UUID_4 = "uuid4";

    protected static final Resume RESUME_1;
    protected static final Resume RESUME_2;
    protected static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Name3");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name1");
        RESUME_4 = new Resume(UUID_4, "Name4");

        RESUME_1.addContacts(ContactType.MAIL, "email@i.ua");
        RESUME_1.addContacts(ContactType.PHONE, "456454425");
        RESUME_1.addSections(SectionType.OBJECTIVE, new TextSection("OBJECTIVE_1"));
        RESUME_1.addSections(SectionType.PERSONAL, new TextSection("Personal Data"));
        RESUME_1.addSections(SectionType.ACHIEVEMENT, new ListSection("Achievement_1", "Achievement_2", "Achievement_3"));
        RESUME_1.addSections(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "C#"));
        RESUME_1.addSections(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization 1", "organization.km.ua",
                        new Organization.Position(2012, Month.FEBRUARY, "position 1", "content 1"),
                        new Organization.Position(2009, Month.JANUARY, 2012, Month.FEBRUARY, "position 2", "content 2"))));
        RESUME_1.addSections(SectionType.EDUCATION, new OrganizationSection(
                new Organization("Institute 1", null,
                        new Organization.Position(2011, Month.JULY, 2012, Month.MAY, "Specialist", "idle"),
                        new Organization.Position(2007, Month.SEPTEMBER, 2011, Month.JUNE, "Student", "Study")),
                new Organization("Organization 2", "organization_2.km.ua")));

        RESUME_2.addContacts(ContactType.SKYPE, "user123");
        RESUME_2.addContacts(ContactType.PHONE, "258464546");
        RESUME_2.addSections(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization 3", "organization_3.km.ua",
                        new Organization.Position(2015, Month.FEBRUARY, "position 3", "content 3"))));

    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() throws IOException {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }


    @Test
    public void update() throws IOException {
        Resume r = new Resume(UUID_2, "New Name");
        storage.update(r);
        Assert.assertEquals(r, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume r = new Resume("update", "New Name");
        storage.update(r);
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        assertSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("qwerty");
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() throws IOException {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }


    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(list, Arrays.asList(RESUME_3, RESUME_2, RESUME_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws IOException {
        storage.get("qweewew");
    }

    @Test(expected = StorageException.class)
    public void getEmpty() throws IOException {
        storage.clear();
        storage.get(UUID_2);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) throws IOException {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }
}