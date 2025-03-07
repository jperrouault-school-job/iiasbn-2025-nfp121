@FunctionalInterface
public interface MonInterfaceFonctionnelle {
    public int additionner();
    // public int multiplier();

    public default int soustraire() {
        return 5 - 4;
    }
}
