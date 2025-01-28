import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BufferCopyApplication {
    public static void main(String[] args) {
        try (
            FileInputStream fis = new FileInputStream("test.pdf");
            FileOutputStream fos = new FileOutputStream("test.copy.pdf")
        ) {
            byte[] buffer = new byte[1024];
            int len = 0;

            System.out.println("Copie en cours ....");

            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            System.out.println("Copie terminée !");
        }

        catch (Exception e) {
            System.out.println("Oups, ça marche pas");
        }
    }
}
