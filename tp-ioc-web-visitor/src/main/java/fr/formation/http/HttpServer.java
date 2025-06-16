package fr.formation.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import fr.formation.annotation.Component;
import fr.formation.annotation.Inject;
import fr.formation.core.WebApplicationContext;
import fr.formation.core.WebApplicationContext.WebMethod;
import fr.formation.visitor.HtmlVisitor;
import fr.formation.visitor.JsonVisitor;
import fr.formation.visitor.Visitable;
import fr.formation.visitor.XmlVisitor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class HttpServer {
    @Inject
    private WebApplicationContext ctx;

    @Inject
    private JsonVisitor jsonVisitor;

    @Inject
    private XmlVisitor xmlVisitor;

    @Inject
    private HtmlVisitor htmlVisitor;

    public void listen() {
        log.debug("Démarrage du serveur sur le port 80 ...");

        try (ServerSocket server = new ServerSocket(80)) {
            log.debug("Serveur démarré et en écoute !");

            while (true) {
                try {
                    Socket client = server.accept();

                    log.debug("Une nouvelle connexion est arrivée !");
    
                    BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            
                    String line = reader.readLine();
                    HttpResponse response = HttpResponse.builder()
                        .contentType(HttpContentType.TEXT_HTML)
                        .status(HttpResponseStatus.OK)
                        .build()
                    ;
            
                    System.out.println(line);
        
                    if (line != null) {
                        String path = line.split(" ")[1];
                        System.out.println("Le chemin demandé est : " + path);

                        WebMethod webMethod = this.ctx.getMethods().get(path);

                        if (webMethod != null) {
                            StringBuilder sb = new StringBuilder();
                            Object result = webMethod.invoke();

                            if (result instanceof List<?> resultList) {
                                resultList.stream()
                                    .filter(e -> e instanceof Visitable)
                                    .map(e -> (Visitable)e)
                                    .forEach((v) -> {
                                        sb.append(v.accept(this.jsonVisitor));
                                        sb.append("\n\r");
                                        sb.append(v.accept(this.xmlVisitor));
                                        sb.append("\n\r");
                                        sb.append(v.accept(this.htmlVisitor));
                                        sb.append("\n\r");
                                    })
                                ;
                            }
                            
                            response.setContent(sb.toString());
                        }

                        else {
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

                    responseBuilder.append(response.getStatus().getCode());
                    responseBuilder.append(" ");
                    responseBuilder.append(response.getStatus().name());
                    responseBuilder.append("\r\n");
        
                    responseBuilder.append("Content-Type: " + response.getContentType().getValue() + "; charset=UTF-8\r\n");
                    responseBuilder.append("Content-Length: " + response.getContent().length() + "\r\n");
                    responseBuilder.append("\r\n");
                    responseBuilder.append(response.getContent());
        
                    writer.write(responseBuilder.toString());
                    writer.flush();
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
}
