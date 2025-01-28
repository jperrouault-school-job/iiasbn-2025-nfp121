public class Application {
    public static void main(String[] args) {
        // DataSource ds = new DataSource(true, "hostname", 3306, "username", "123456", "i1", "i2", "i3", "i4", "i5", "i6");

        DataSource ds = DataSource.builderV2()
            .host("host")
            .port(333)
            .password("secret")
            .username("username")
            .secure(true)
            .build()
        ;
    }
}
