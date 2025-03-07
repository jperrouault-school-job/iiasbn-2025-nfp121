import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FonctionnelleApplication {
    public static void main(String[] args) {
        List<String> prenoms = List.of("Jérémy", "Gabriel", "Basile", "Jules", "Tristan");

        prenoms.stream()
            // Filtrer les prénoms qui commencent par "J"


            // Mettre tout en MAJUSCULE les prénoms

            // Afficher chaque prénom dans la console
            ;

        System.exit(0);


        MonInterfaceFonctionnelle mif = new MonInterfaceFonctionnelle() {

            @Override
            public int additionner() {
                return 5 + 5;
            }
        };

        MonInterfaceFonctionnelle mif2 = () -> {
            return 5 + 7;
        };

        System.out.println(mif.additionner());
        System.out.println(mif2.additionner());

        BiFunction<Integer, Integer, Integer> calc = (a, b) -> a + b;

        System.out.println(calc.apply(5, 6));

        demo((a, b) -> a + b);
        demo((a, b) -> a * b);

    }

    public static void demo(BiFunction<Integer, Integer, Integer> calc) {
        System.out.println(calc.apply(5, 8));
    }
}
