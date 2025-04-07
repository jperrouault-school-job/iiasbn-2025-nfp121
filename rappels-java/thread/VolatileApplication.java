package thread;

public class VolatileApplication {
    // Avec "volatile", on empêche la mise en cache CPU de la variable
    private volatile static boolean finished = false;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            while (!finished) { }

            System.out.println("Terminé !");
        };

        Thread t1 = new Thread(task);

        t1.start();

        Thread.sleep(20);
        finished = true;        

        t1.join();
    }
}
