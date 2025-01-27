import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetApplication {
    public static void main(String[] args) {
        // Sans doublons, mais l'ordre n'est pas garanti
        Set<String> lst1 = new HashSet<>();
        
        lst1.add("A");
        lst1.add("Z");
        lst1.add("C");
        lst1.add("D");
        lst1.add("A");

        System.out.println(lst1);

        // Pas de doublons, et ordre garanti
        Set<String> lst2 = new LinkedHashSet<>();
        
        lst2.add("A");
        lst2.add("Z");
        lst2.add("C");
        lst2.add("D");
        lst2.add("A");

        System.out.println(lst2);
    }
}
