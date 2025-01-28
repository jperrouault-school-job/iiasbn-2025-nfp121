import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopyApplication {
    public static void main(String[] args) {
        try (
            FileInputStream fis = new FileInputStream("test.pdf");
            FileOutputStream fos = new FileOutputStream("test.copy.pdf")
        ) {
            int value = 0;

            System.out.println("Copie en cours ....");

            while ((value = fis.read()) != -1) {
                fos.write(value);
            }

            System.out.println("Copie terminée !");
        }

        catch (Exception e) {
            System.out.println("Oups, ça marche pas");
        }
   }
}
