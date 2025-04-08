package fr.formation.tcp;

import java.net.Socket;

import fr.formation.interpretor.Evaluation;
import fr.formation.interpretor.Interpretor;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;

@Builder
@Log4j2
public class ClientSocket {
    private Socket socket;

    public void receive() {
        int inputValue;
        StringBuilder received = new StringBuilder();
        
        try {
            while ((inputValue = this.socket.getInputStream().read()) != -1) {
                char character = (char)inputValue;

                if (inputValue == '\n' || character == '\r') {
                    if (received.length() == 0) {
                        continue;
                    }
                    
                    log.debug("Commande : {}", received.toString());

                    this.sendResponse(Interpretor.evaluate(received.toString()));
                    
                    received = new StringBuilder();
                    continue;
                }

                received.append(character);
            }
        }

        catch (Exception e) {
            log.error("Une erreur est survenue pendant la lecture du flux : {}", e.getMessage());
        }
    }

    public void sendResponse(Evaluation evaluation) {
        try {
            int offset = 0;
            int chunkSize = 1024;
            
            // En-tête : type (sur 1 octet), taille du flux (sur 4 octets)
            byte[] header = new byte[5];
            int dataSize = evaluation.getValue().length;

            header[0] = (byte)evaluation.getType();
            header[1] = (byte)(dataSize >> 24);
            header[2] = (byte)(dataSize >> 16);
            header[3] = (byte)(dataSize >> 8);
            header[4] = (byte)(dataSize);

            this.socket.getOutputStream().write(header);

            // Donnée coupée en blocs de 1024 octets
            while (offset < evaluation.getValue().length) {
                int bytesToWrite = Math.min(chunkSize, evaluation.getValue().length - offset);

                this.socket.getOutputStream().write(evaluation.getValue(), offset, bytesToWrite);
                
                offset += bytesToWrite;
            }

            this.socket.getOutputStream().flush();
        }

        catch (Exception e) {
            log.error("Une erreur est survenue pendant l'envoie du flux : {}", e.getMessage());
        }
    }
}
