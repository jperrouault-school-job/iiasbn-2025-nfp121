import java.util.Collection;

public class Application {
    public static void main(String[] args) {
        // CollectionFactory<Application, Integer, Integer> cf = new CollectionFactory<>();
        // cf.createDemo(new Application());

        Collection<String> lst1 = CollectionFactory.createCollection(false, false);
    }
}
