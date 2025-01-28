import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientApplication {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder().build();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("https://www.google.fr"))
                .build()
            ;

            HttpResponse<byte[]> response = httpClient.send(request, BodyHandlers.ofByteArray());

            byte[] google = response.body();

            System.out.println(google.length);
        }

        catch (Exception e) {
            System.out.println("OUPS");
        }
    }
}
