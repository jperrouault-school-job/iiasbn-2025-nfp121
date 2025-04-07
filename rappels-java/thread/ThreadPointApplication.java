package thread;

public class ThreadPointApplication {
    public static void main(String[] args) throws InterruptedException {
        Point p = new Point();

        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                synchronized (p) {
                    p.translate(1, 1);
                }
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
