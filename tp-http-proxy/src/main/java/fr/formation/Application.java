package fr.formation;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.model.Album;
import fr.formation.model.Photo;
import fr.formation.proxy.HttpClientProxy;

public class Application {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClientProxy();
        ObjectMapper mapper = new ObjectMapper();


        try {
            HttpRequest request = HttpRequest
                .newBuilder(new URI("https://jsonplaceholder.typicode.com/photos"))
                .GET()
                .build()
            ;

            HttpResponse<byte[]> response = httpClient.send(request, BodyHandlers.ofByteArray());

            byte[] json = response.body();

            // Photo[] photos = mapper.readValue(json, Photo[].class);
            // System.out.println(photos.length + " photos");

            // List<Photo> photos = Arrays.asList(mapper.readValue(json, Photo[].class));
            // System.out.println(photos.size() + " photos");

            List<Photo> photos = mapper.readValue(json, new TypeReference<List<Photo>>() {});
            System.out.println(photos.size() + " photos");

            for (Photo photo : photos) {
                HttpRequest requestAlbum = HttpRequest
                    .newBuilder(new URI("https://jsonplaceholder.typicode.com/albums/" + photo.getAlbumId()))
                    .GET()
                    .build()
                ;

                HttpResponse<byte[]> reponseAlbum = httpClient.send(requestAlbum, BodyHandlers.ofByteArray());

                Album album = mapper.readValue(reponseAlbum.body(), Album.class);

                photo.setAlbumTitle(album.getTitle());
            }

            Random rand = new Random();

            rand.ints(50, 0, photos.size())
                .mapToObj(i -> photos.get(i))
                .forEach(System.out::println);
        }

        catch (Exception e) {
            System.out.println("Ca marche po");
        }
    }
}