package thread;

public class TheadApplication {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread " + Thread.currentThread().getName() + " qui affiche " + i);
            }
        };


        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        System.out.println("Fin dans thread " + Thread.currentThread().getName());
    }
}
