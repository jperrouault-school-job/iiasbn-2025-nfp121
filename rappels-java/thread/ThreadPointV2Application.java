package thread;

public class ThreadPointV2Application {
    public static void main(String[] args) throws InterruptedException {
        PointV2 p = new PointV2();

        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                p.translate(1, 1);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        // Démarrage des threads
        t1.start();
        t2.start();

        // Attendre la fin de chaque thread
        t1.join();
        t2.join();

        System.out.println(p);
    }
}
