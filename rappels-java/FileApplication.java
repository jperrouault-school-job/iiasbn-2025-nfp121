import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileApplication {
    public static void main(String[] args) {
        // Stream pour les octets
        // Ouput pour l'écriture
        // Input pour la lecture
        // File
        // Un fichier en écriture d'octets : FileOutputStream

        // FileOutputStream fos = null;

        // try {
        //     fos = new FileOutputStream("lefichier.txt");
        // }

        // catch (FileNotFoundException e) {
        //     System.out.println("Problème fichier");    
        // }

        // finally {
        //     try {
        //         fos.close();
        //     }

        //     catch (IOException e) {
        //         System.out.println("Impossible de fermer");
        //     }
        // }

        try (FileOutputStream fos = new FileOutputStream("lefichier.txt")) {
            // fos.write("Démonstration".getBytes());
            fos.write(97);
        }

        catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable");    
        }

        catch (IOException e) {
            System.out.println("Problème sur le fichier");    
        }
     }
}
