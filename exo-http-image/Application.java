import java.io.FileOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Application {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder().build();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("https://previews.123rf.com/images/amunique/amunique2303/amunique230308604/200906406-joli-chat-tigr%C3%A9-dans-une-cape-bleue-avec-une-bouteille-de-whisky.jpg"))
                .build()
            ;

            HttpResponse<byte[]> response = httpClient.send(request, BodyHandlers.ofByteArray());

            byte[] img = response.body();

            try (FileOutputStream fos = new FileOutputStream("image.jpg")) {
                fos.write(img);
            }
        }

        catch (Exception e) {
            System.out.println("OUPS");
        }
    }
}
