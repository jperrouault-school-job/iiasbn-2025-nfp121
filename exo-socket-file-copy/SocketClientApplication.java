import java.io.FileInputStream;
import java.net.Socket;

public class SocketClientApplication {
    public static void main(String[] args) {
        System.out.println("Démrrage du client ...");

        try (Socket client = new Socket("127.0.0.1", 1234)) {
            System.out.println("Connexion OK !");
            byte[] buffer = new byte[5];

            try (FileInputStream fis = new FileInputStream("fichier.txt")) {
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    client.getOutputStream().write(buffer, 0, len);
                }
                
                // int value;

                // while ((value = fis.read()) != -1) {
                //     client.getOutputStream().write((byte)value);
                // }

                System.out.println("Fichier envoyé !");
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
