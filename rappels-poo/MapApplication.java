import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapApplication {
    public static void main(String[] args) {
        // Pas de doublons dans les clés, mais pas d'ordre garanti
        Map<String, String> dic1 = new HashMap<>();

        dic1.put("A", "A");
        dic1.put("Z", "Z");
        dic1.put("C", "C");
        dic1.put("D", "D");
        dic1.put("A", "A2");

        System.out.println(dic1);

        
        // Pas de doublons dans les clés, avec ordre garanti
        Map<String, String> dic2 = new LinkedHashMap<>();

        dic2.put("A", "A");
        dic2.put("Z", "Z");
        dic2.put("C", "C");
        dic2.put("D", "D");
        dic2.put("A", "A2");

        System.out.println(dic2);
    }
}
