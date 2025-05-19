import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerApplication {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(80);

        while (true) {
            Socket client = server.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            System.out.println("Nouvelle connexion HTTP héhé");

            String line = reader.readLine();

            if (line != null) {
                System.out.println(line);

                String path = line.split(" ")[1];
                System.out.println("Le chemin demandé est " + path);
            }

            String responseBody = """
<html>
<body>
<h1>Ma première page web des internets</h1>
</body>
</html>
""";

            StringBuilder responseBuilder = new StringBuilder("HTTP/1.1 200 OK\n\r");

            responseBuilder.append("Content-Type: text/html; charset=UTF-8\n\r");
            responseBuilder.append("Content-Length: " + responseBody.length() + "\n\r");
            responseBuilder.append("\n\r");
            responseBuilder.append(responseBody);

            writer.write(responseBuilder.toString());
            writer.flush();
        }
    }
}
