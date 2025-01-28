import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Vehicule v1 = new Voiture();
        Vehicule v2 = new Moto();
        // Vehicule[] vehicules = new Vehicule[2];
        List<Vehicule> vehicules = new ArrayList<>();

        // v1.demarrer();
        // v2.demarrer();

        // vehicules[0] = v1;
        // vehicules[1] = v2;
        vehicules.add(v1);
        vehicules.add(v2);
        // cChelou(vehicules); // Compile mais devient incohérent !

        // for
        // for (int i = 0; i < vehicules.size(); i++) {
        //     Vehicule v = vehicules.get(i);
        // }

        // foreach
        for (Vehicule v : vehicules) {
            v.demarrer();
            vehicules.remove(v);
            System.out.println("SUPPRIME ?");
        }

        System.out.println("--??");

        // Pattern Iterator
        Iterator<Vehicule> it = vehicules.iterator();
        while (it.hasNext()) {
            Vehicule v = it.next();
            v.demarrer();
            it.remove();
        }

        System.out.println("--------");
        System.exit(0);


        // vehicules.add("test");
        // cChelou(vehicules);

        // vehicules[0].demarrer();
        // vehicules[1].demarrer();

        // vehicules.get(0).demarrer();
        // vehicules.get(1).demarrer();
        // vehicules.get(2).demarrer();
    }

    public static void cChelou(List list) {
        list.add("Chelou");
    }


    public static void main2(String[] args) {
        Voiture voiture = new Voiture();

        voiture.setModele("Peugeot");
        voiture = reference(voiture);

        System.out.println(voiture.getModele());

        // voiture.demarrer();
        // ((Vehicule)voiture).demarrer();

        int[] tab = new int[5];

        tab[1] = 5;
        reference(tab);
        System.out.println(tab[1]);

        int a = 5;
        Integer b  = 5;

        reference(a);
        System.out.println(a);
        
        reference(b);
        System.out.println(b);

        System.out.println(voiture);
    }

    public static void reference(Integer b) {
        b = 49;
    }

    public static void reference(int a) {
        a = 49;
    }

    public static void reference(int[] tab) {
        tab[1] = 42;
    }

    public static Voiture reference(Voiture voiture) {
        voiture = new Voiture();

        voiture.setModele("Renault");
        voiture.setModele("Citroën");

        return voiture;
    }
}
