import java.lang.reflect.Constructor;

import demo.Reflex;
import demo.Vehicule;

public class Application {
    public static void main(String[] args) throws Exception {
        // new Singleton();
        Singleton s1 = Singleton.getInstance();
        // Singleton s2 = Singleton.getInstance();
        // Singleton s2 = new Singleton();

        Vehicule v = (Vehicule) Reflex.demo("demo.Vehicule");

        System.out.println(v);

        Constructor<Singleton> ctor = Singleton.class.getDeclaredConstructor();

        ctor.setAccessible(true);

        Singleton s2 = ctor.newInstance();

        if (s1 == s2) {
            System.out.println("C PAREIL");
        }

        else {
            System.out.println("C PAS PAREIL");
        }
    }
}
