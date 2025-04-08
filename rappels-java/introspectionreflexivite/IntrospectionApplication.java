package introspectionreflexivite;

import java.lang.reflect.Field;

public class IntrospectionApplication {
    public static void main(String[] args) throws NoSuchFieldException, SecurityException {
        for (var ctor : Photo.class.getDeclaredConstructors()) {
            System.out.println(ctor.getName());
            System.out.println(ctor.getParameterCount());
        }

        for (var method : Photo.class.getDeclaredMethods()) {
            System.out.println(method.getName());
            System.out.println(method.getParameterCount());
        }

        for (var field : Photo.class.getDeclaredFields()) {
            System.out.println(field.getName());
            System.out.println(field.getType());
        }

        Field f = Photo.class.getDeclaredField("id");

        System.out.println(f.getName());
    }
}
