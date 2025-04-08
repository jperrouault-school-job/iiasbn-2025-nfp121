package fr.formation;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpJsonClient {
    private HttpClient httpClient = HttpClient.newBuilder().build();
    private ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();

    public <T> T get(String url, Class<T> clz) {
        try {
            return mapper.readValue(this.get(url), clz);
        }

        catch (Exception e) {
            System.out.println("Impossible de désérialiser le flux ...");
        }

        return null;
    }

    public byte[] get(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build()
            ;

            return httpClient.send(request, BodyHandlers.ofByteArray()).body();
        }

        catch (URISyntaxException ex) {
            System.out.println("L'URI spécifiée est incorrecte.");
        }

        catch (Exception e) {
            System.out.println("Impossible de récupérer le flux ...");
        }

        return null;
    }
}
