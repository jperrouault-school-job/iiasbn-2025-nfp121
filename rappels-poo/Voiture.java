public class Voiture extends Vehicule {
    private String modele = "";

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Voiture() {
        System.out.println("CREATION D'UNE VOITURE !");
    }

    @Override
    public void demarrer() {
        System.out.println("La voiture démarre ...");
    }

    @Override
    public String toString() {
        return "[modele = " + this.modele + "]";
    }
}
