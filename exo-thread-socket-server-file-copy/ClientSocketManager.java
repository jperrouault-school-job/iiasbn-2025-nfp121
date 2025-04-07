import java.io.FileOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientSocketManager {
    private List<Socket> clients = new ArrayList<>();

    public void add(Socket client) {
        synchronized (this.clients) {
            this.clients.add(client);
        }

        Application.THREAD_POOL.submit(() -> this.copyFile(client));
    }

    public void copyFile(Socket client) {
        byte[] buffer = new byte[5];
        int len;

        try (FileOutputStream fos = new FileOutputStream("copie.txt")) {
            while ((len = client.getInputStream().read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            System.out.println("Copie faite !");
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
