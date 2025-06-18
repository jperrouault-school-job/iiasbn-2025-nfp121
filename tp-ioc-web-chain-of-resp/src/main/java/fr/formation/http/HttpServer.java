package fr.formation.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import fr.formation.annotation.Component;
import fr.formation.annotation.Inject;
import fr.formation.chainofresp.HttpAcceptFilter;
import fr.formation.chainofresp.HttpAuthorizationFilter;
import fr.formation.core.WebApplicationContext;
import fr.formation.core.WebApplicationContext.WebMethod;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class HttpServer {
    @Inject
    private WebApplicationContext ctx;

    @Inject
    private HttpAuthorizationFilter authorizationFilter;

    @Inject
    private HttpAcceptFilter acceptFilter;

    public void listen() {
        log.debug("Démarrage du serveur sur le port 80 ...");

        log.debug("Configuration des filtres ...");

        this.authorizationFilter
            .chain(this.acceptFilter)
        ;

        try (ServerSocket server = new ServerSocket(80)) {
            log.debug("Serveur démarré et en écoute !");

            while (true) {
                try {
                    Socket client = server.accept();

                    new Thread(() -> this.acceptClient(client)).start();
                }

                catch (Exception e) {
                    log.error("Problème lors de la connexion avec le client : {}", e.getMessage());
                }
            }
        }

        catch (Exception e) {
            log.error("Impossible de créer le serveur : {}", e.getMessage());
        }
    }

    private void acceptClient(Socket client) {
        log.debug("Une nouvelle connexion est arrivée !");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String line = reader.readLine();
            HttpResponse response = HttpResponse.builder()
                .contentType(HttpContentType.TEXT_HTML)
                .status(HttpResponseStatus.OK)
                .build()
            ;

            if (line != null) {
                String path = line.split(" ")[1];
                HttpRequest request = HttpRequest.builder()
                    .path(path)
                    .build()
                ;

                while (!(line = reader.readLine()).isBlank()) {
                    String[] headerParts = line.split(": ");

                    if (headerParts.length != 2) {
                        continue;
                    }

                    request.getHeaders().put(headerParts[0], headerParts[1]);
                }

                this.authorizationFilter.doChain(request, response);

                WebMethod webMethod = this.ctx.getMethods().get(path);

                if (webMethod == null) {
                    response.setStatus(HttpResponseStatus.NOT_FOUND);
                    response.setContent("""
    <html>
    <body>
    <h1>Page not found</h1>
    </body>
    </html>
    """);
                }
            }

            StringBuilder responseBuilder = new StringBuilder("HTTP/1.1 ");
            byte[] contentBytes = response.getContent().getBytes(StandardCharsets.UTF_8);

            responseBuilder.append(response.getStatus().getCode());
            responseBuilder.append(" ");
            responseBuilder.append(response.getStatus().name());
            responseBuilder.append("\r\n");

            responseBuilder.append("Content-Type: " + response.getContentType().getValue() + "; charset=UTF-8\r\n");
            responseBuilder.append("Content-Length: " + contentBytes.length + "\r\n");
            responseBuilder.append("Connection: close\r\n");
            responseBuilder.append("\r\n");

            writer.write(responseBuilder.toString());
            writer.flush();
            writer.write(new String(contentBytes, StandardCharsets.UTF_8));
            writer.flush();
        }

        catch (Exception e) {
            log.error("Problème lors de la communication avec le client : {}", e.getMessage());
        }
    }
}
