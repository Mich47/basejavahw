package com.mich.webapp;

import com.mich.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("Name");
        Class<? extends Resume> rClass = r.getClass();
        Field field = rClass.getDeclaredFields()[0];
        Method m = rClass.getMethod("toString");
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "qwerty");
        System.out.println(m.invoke(r));
    }
}
