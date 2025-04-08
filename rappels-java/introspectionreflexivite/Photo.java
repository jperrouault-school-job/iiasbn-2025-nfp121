package introspectionreflexivite;

public class Photo {
    private int id;
    private String title;

    public Photo() {

    }

    private Photo(int id, String title) {
        this.id = id;
        this.title = title;
    }

    private Photo(String title) {
        this.title = title;
    }

    @Async
    public void demo() {
        System.out.println("Méthode demo");
    }
    
    private void demo2(String arg) {
        System.out.println("Méthode démo 2");
    }
}
