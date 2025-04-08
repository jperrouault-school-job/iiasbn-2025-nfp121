package fr.formation.tcp;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import fr.formation.ServerApplication;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClientSocketManager {
    private List<ClientSocket> clients = new ArrayList<>();

    public void add(Socket socket) {
        log.debug("Ajout d'un nouveau client ...");

        ClientSocket client = ClientSocket.builder()
            .socket(socket)
            .build()
        ;

        this.clients.add(client);

        ServerApplication.THREAD_POOL.submit(client::receive);
    }
}
