public class PrimitifApplication {
    public static void main(String[] args) {
        // Types primitifs

        // Entiers signés obligatoirement
        int a = 5;      // 4 octets
        long b = 5;     // 8 octets
        short c = 5;    // 2 octets
        byte d = 5;     // 1 octets

        // Caractères ==> aussi un entier MAIS pas signé
        char e = '5';   // 2 octets
        char f = 53;

        // Vrai / Faux
        boolean g = true; // 1 bit

        // A virgules flottantes
        float h = 5;    // 4 octets
        double i = 5;   // 8 octets

        // Les bases
        int j = 0x13;   // Hexa, base 16
        System.out.println("Hexa : " + j);
        
        j = 0b101;      // Binaire, base 2
        System.out.println("Binaire : " + j);
        
        j = 013;        // Octal, base 8
        System.out.println("Octal : " + j);

        // Troncature
        int k = 56;
        byte l = (byte)k;
    }
}
