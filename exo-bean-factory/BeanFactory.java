public class BeanFactory {
    private BeanFactory() { }

    public static <T> T createBean(Class<T> clz) {
        try {
            return clz.getConstructor().newInstance();
        }

        catch (Exception e) {
            return null;
        }
    }

    public static Object createBean(String clzName) {
        try {
            return Class.forName(clzName).getConstructor().newInstance();
        }

        catch (Exception e) {
            return null;
        }
    }
}
