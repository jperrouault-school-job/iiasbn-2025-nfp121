
public class Application {
    public static void main(String[] args) {
        Photo p1 = BeanFactory.createBean(Photo.class);
        Photo p2 = (Photo)BeanFactory.createBean("Photo");

        System.out.println(p1);
        System.out.println(p2);
    }
}