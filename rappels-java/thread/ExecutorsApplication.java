package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsApplication {
    public static void main(String[] args) {
        // Gestionnaire de pool de Threads
        ExecutorService executor = Executors.newFixedThreadPool(100);

        // Gestionnaire de schedulers
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(100);

        // Exécuter une tâche dans un des threads
        executor.submit(() -> {
            for (int i = 0; i < 100_000; i++) {
                System.out.println(i);
            }
        });

        scheduledExecutor.scheduleAtFixedRate(() -> {
            System.out.println("Message schédulé");
        }, 0, 1, TimeUnit.SECONDS);
    }
}
