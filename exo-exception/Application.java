public class Application {
    public static void main(String[] args) {
        VehiculeService service = new VehiculeService();

        service.create("Test");
        
        try {
            service.create("");
            return;
        }
        
        catch (VehiculeModeleNotEmptyException e) {
            System.out.println("Le modèle est obligatoire !");
            return;
        }
        
        catch (Exception e) {
            System.out.println("Erreur inconnue ..." + e.getMessage());
            return;
        }

        finally {
            System.out.println("Sera exécuté quoi qu'il arrive !");
        }
    }
}
