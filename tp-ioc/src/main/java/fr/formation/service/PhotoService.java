package fr.formation.service;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.annotation.Component;
import fr.formation.annotation.Inject;
import fr.formation.model.Photo;
import fr.formation.proxy.HttpClientProxy;

@Component
public class PhotoService {
    @Inject
    private HttpClientProxy httpClient;
    
    @Inject
    private ObjectMapper mapper;

    public List<Photo> findAll() {
        try {
            HttpRequest request = HttpRequest
                .newBuilder(new URI("https://jsonplaceholder.typicode.com/photos"))
                .GET()
                .build()
            ;

            HttpResponse<byte[]> response = httpClient.send(request, BodyHandlers.ofByteArray());

            byte[] json = response.body();

            List<Photo> photos = mapper.readValue(json, new TypeReference<List<Photo>>() {});
            System.out.println(photos.size() + " photos");

            return photos;
        }

        catch (Exception e) {
            System.out.println("Ca marche po");
        }

        return new ArrayList<>();
    }
}
