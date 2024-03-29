package com.urise.webapp;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.SectionType;

public class TestSingleton {
    private static TestSingleton instance;

    public static TestSingleton getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }

    private TestSingleton() {
    }

    public static void main(String[] args) {
//        TestSingleton.getInstance().toString();
        Singleton instance = Singleton.valueOf("INSTANCE1");
        System.out.println(instance.ordinal());

        for (ContactType type : ContactType.values()) {
            System.out.println(type.getTitle());
        }
        System.out.println();
        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
        }
    }

    public enum Singleton {
        INSTANCE, INSTANCE1
    }
}
