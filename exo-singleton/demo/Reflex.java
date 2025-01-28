package demo;

import java.lang.reflect.InvocationTargetException;

public class Reflex {
    public static Object demo(String className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
        return Class.forName(className).getConstructor().newInstance();
    }
}
