import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerApplication {
    public static void main(String[] args) {
        System.out.println("Démarrage du serveur ...");


        try (ServerSocket server = new ServerSocket(1234)) {
            Socket client = server.accept();
            byte[] buffer = new byte[5];

            System.out.println("Connexion entrante !");

            int len;

            try (FileOutputStream fos = new FileOutputStream("copie.txt")) {
                while ((len = client.getInputStream().read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            
                // while ((input = client.getInputStream().read()) != -1) {
                //     fos.write((byte)input);
                // }



                System.out.println("Copie faite !");
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
