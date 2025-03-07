package socket;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerApplication {
    public static void main(String[] args) {
        // Serveur TCP : Avoir quelque chose qui écoute sur un port en particulier
        int port = 1234;

        try (ServerSocket server = new ServerSocket(port)) {
            Socket client = server.accept();

            System.out.println("Une nouvelle connexion est arrivée !!");
        }

        catch (Exception e) {
            System.out.println("Problème socket ...");
        }



    }
}
