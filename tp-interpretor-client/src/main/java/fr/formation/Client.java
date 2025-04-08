package fr.formation;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;

    public void connect() {
        try {
            this.socket = new Socket("127.0.0.1", 1234);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Evaluation sendAndReceive(String command) {
        if (this.socket == null) {
            return Evaluation.builder().build();
        }

        try {
            this.socket.getOutputStream().write(command.getBytes());

            // En-tête et données
            byte[] header = new byte[5];
            byte[] buffer = new byte[1024];

            this.socket.getInputStream().read(header);

            int dataType = header[0];

            int dataSize = ((header[1] & 0xFF) << 24) |
                           ((header[2] & 0xFF) << 16) |
                           ((header[3] & 0xFF) << 8) |
                           (header[4] & 0xFF)
            ;

            System.out.println("Type de données : " + dataType);
            System.out.println("Taille totale des données : " + dataSize);

            int len;
            int totalBytesRead = 0;
            byte[] value = new byte[dataSize];

            while (totalBytesRead < dataSize && (len = this.socket.getInputStream().read(buffer)) != -1) {
                System.arraycopy(buffer, 0, value, totalBytesRead, len);
                totalBytesRead += len;
            }

            return Evaluation.builder()
                .type(dataType)
                .value(value)
                .build()
            ;
        }
        
        catch (IOException e) {
            e.printStackTrace();

            return Evaluation.builder().build();
        }
    }

    public void disconnect() {
        if (this.socket == null) {
            return;
        }

        try {
            this.socket.close();
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
