import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

public class CollectionFactory<I extends Application, Autant, String> {
    public static <T> Collection<T> createCollection(boolean linked, boolean noDuplicates) {
        if (linked) {
            return (noDuplicates) ? new LinkedHashSet<>() : new LinkedList<>();
        }

        return (noDuplicates) ? new HashSet<>() : new ArrayList<>();
    }

    public void createDemo(I demo) {
        if (demo instanceof Application app) {
            app.main(null);
        }
    }

    private CollectionFactory() {
        
    }
}
