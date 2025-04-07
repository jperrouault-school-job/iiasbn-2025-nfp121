package socket;

import java.net.Socket;

public class SocketClientApplication {
    public static void main(String[] args) {
        try (Socket client = new Socket("127.0.0.1", 1234)) {
            int input;

            while ((input = client.getInputStream().read()) != -1) {
                byte value = (byte)input;
                System.out.println("Valeur reçue : " + value);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
