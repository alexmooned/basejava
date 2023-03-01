package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected final Storage storage;
    private static final String UUID_NOT_EXIST = "dummy";

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;


    static {
//        RESUME_1 = new Resume(UUID_1, "Name1");
//        RESUME_2 = new Resume(UUID_2, "Name2");
//        RESUME_3 = new Resume(UUID_3, "Name3");
//        RESUME_4 = new Resume(UUID_4, "Name4");
        RESUME_1 = ResumeTestData.getTestData(UUID_1, "Name1");
        RESUME_2 = ResumeTestData.getTestData(UUID_2, "Name2");
        RESUME_3 = ResumeTestData.getTestData(UUID_3, "Name3");
        RESUME_4 = ResumeTestData.getTestData(UUID_4, "Name4");
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
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Resume updated = new Resume(UUID_3, "New Name");
        RESUME_3.addContact(ContactType.MAIL, "mail1@google.com");
        RESUME_3.addContact(ContactType.SKYPE, "NewSkype");
        RESUME_3.addContact(ContactType.MOBILE, "+7 921 222-22-22");
        storage.update(updated);
        assertEquals(updated, (storage.get(UUID_3)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_3);
        assertSize(2);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    public static class ResumeTestData extends Resume {
        public ResumeTestData(String fullName) {
            super(fullName);
        }

        public ResumeTestData(String uuid, String fullName) {
            super(uuid, fullName);
        }

        public static Resume getTestData(String uuid, String fullName) {
            Resume r = new Resume(uuid, fullName);

            r.addContact(ContactType.MAIL, fullName + "mail@mail.ru");
            r.addContact(ContactType.PHONE, "111");
            r.addSection(SectionType.OBJECTIVE, new TextSection("Objective" + uuid));
            r.addSection(SectionType.PERSONAL, new TextSection("Personal data" + uuid));
            r.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment1", "Achivment2"));
            r.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL"));
//            r.addSection(SectionType.EXPERIENCE,
//                    new OrganizationSection(
//                            new Organization("Organization", "http://www.organization.ru",
//                                    new Organization.Period(2020, Month.JANUARY, "position1", "content1"),
//                                    new Organization.Period(2019, Month.MARCH, 2020, Month.JANUARY, "position2", "content2"))));
//            r.addSection(SectionType.EDUCATION,
//                    new OrganizationSection(
//                            new Organization("Institute", "http://www.institute.ru",
//                                    new Organization.Period(2015, Month.JANUARY, 2019, Month.DECEMBER, "aspirant", null))));
            return r;
        }
    }
}