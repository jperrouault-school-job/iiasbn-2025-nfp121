import java.io.FileOutputStream;
import java.io.IOException;
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

            Application.THREAD_POOL.submit(this::sendFileOk);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFileOk() {
        synchronized (this.clients) {
            for (Socket client : this.clients) {
                if (client.isClosed()) {
                    continue; // Et il faudra la supprimer de la liste
                }

                byte[] output = "Fichier reçu".getBytes();
                
                try {
                    client.getOutputStream().write(output, 0, output.length);
                }
                
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
