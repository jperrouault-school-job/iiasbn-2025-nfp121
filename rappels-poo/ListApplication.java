import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ListApplication {
    public static void main(String[] args) {
        List<String> lst1 = new ArrayList<>();
        List<String> lst2 = new LinkedList<>();
        List<String> lst3 = new Vector<>(); // ArrayList Thread-safe
        List<String> lst4 = Collections.synchronizedList(new ArrayList<>()); // ArrayList Thread-safe

        synchronized (lst1) { // Manipulation de la liste en mode thread-safe

        }

        lst1.add("A");
        lst1.add("Z");
        lst1.add("C");
        lst1.add("D");
        lst1.add("A");

        System.out.println(lst1);
    }
}
