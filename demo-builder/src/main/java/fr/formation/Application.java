package fr.formation;

public class Application {
    public static void main(String[] args) {
        DataSource ds = DataSource.builder()
            .host("host")
            .port(333)
            .password("secret")
            .username("username")
            .secure(true)
            .build()
        ;
    }
}