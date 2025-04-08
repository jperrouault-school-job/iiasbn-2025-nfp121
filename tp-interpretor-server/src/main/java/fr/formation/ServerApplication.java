package fr.formation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.formation.tcp.Server;

public class ServerApplication {
    public static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(101);

    public static void main(String[] args) {
        THREAD_POOL.submit(() -> new Server().listen(1234));
    }
}
