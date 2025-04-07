import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ClientSocketManager manager = new ClientSocketManager();

    public void listen() {
        System.out.println("Démarrage du serveur ...");
        
        try (ServerSocket server = new ServerSocket(1234)) {
            while (true) {
                Socket client = server.accept();

                System.out.println("Connexion entrante !");
                
                this.manager.add(client);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
