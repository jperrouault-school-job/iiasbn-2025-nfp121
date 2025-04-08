package introspectionreflexivite;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflexiviteApplication {
    public static void main(String[] args) throws Exception {
        // Constructor<Photo> ctor = Photo.class.getDeclaredConstructor(String.class);
        Constructor<Photo> ctor = Photo.class.getDeclaredConstructor(Integer.TYPE, String.class);

        // Si le ctor est privé, pas possible ... sauf à le rendre disponible
        ctor.setAccessible(true);

        // Photo p = ctor.newInstance("Donc voilà hein");
        Photo p = ctor.newInstance(50, "Donc voilà hein");

        System.out.println(p);

        // Field field = Photo.class.getDeclaredField("title");
        Field field = p.getClass().getDeclaredField("title");

        field.setAccessible(true);

        field.set(p, "Autre titre");
        String title = (String) field.get(p);

        System.out.println(title);

        for (var method : p.getClass().getDeclaredMethods()) {
            Async async = method.getDeclaredAnnotation(Async.class);

            if (async != null) {
                System.out.println(method.getName());

                method.setAccessible(true);

                method.invoke(p);
            }
        }
    }
}
