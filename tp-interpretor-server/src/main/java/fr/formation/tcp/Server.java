package fr.formation.tcp;

import java.net.ServerSocket;
import java.net.Socket;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Server {
    private final ClientSocketManager manager = new ClientSocketManager();

    public void listen(int port) {
        log.debug("Ouverture du serveur sur le port {} ...", port);

        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Socket client = server.accept();

                log.debug("Nouvelle connexion entrante !");

                this.manager.add(client);
            }
        }

        catch (Exception e) {
            log.error("Une erreur est survenue : {}", e.getMessage());
        }
    }
}
