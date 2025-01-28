public class VehiculeService {
    public Vehicule create(String modele) {
        if (modele == null || modele.isEmpty()) {
            throw new VehiculeModeleNotEmptyException();
        }

        Vehicule v = new Vehicule();

        v.setModele(modele);

        return v;
    }
}
