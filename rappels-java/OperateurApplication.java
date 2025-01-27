public class OperateurApplication {
    public static void main(String[] args) {
        // Opérateurs :
        // + - / * % & | ^ ~ >> >>> <<

        // IMPORTANT : Une affectation est une expression qui retourne le résultat affecté
        // int a = 5; // Affectera 5 dans a, et retournera 5

        // andOr();
        // andOrXorNot();
        decalage();
    }

    public static void decalage() {
        byte a = 5;     // 101

        // Décalage vers la gauche
        // <<
        // On introduit des 0 sur la droite, autant de 0 que le décalage
        System.out.println(a << 2);

        // Décalage vers la droite
        // >>
        // On introduit des 0 (ou des 1, selon le bit de signe) à gauche
        System.out.println(a >> 2);

        // Décalage vers la droite (sans signe)
        // >>>
        // On introduit des 0 à gauche
        System.out.println(a >>> 2);
    }

    public static void andOrXorNot() {
        //      101         5
        //      011         3

        int a = 5;
        int b = 3;

        // AND  &
        // Il faut avoir 1 partout pour avoir 1, sinon c'est 0
        System.out.println(a & b);

        // OR |
        // 0 et 0 font 0, sinon 1
        System.out.println(a | b);

        // XOR ^
        // 1 et 0 font 1, 0 et 0 font 0, 1 et 1 font 0
        System.out.println(a ^ b);

        // NOT ~
        // On inverse tous les bits
        System.out.println(~a);
    }

    public static void andOr() {
        // System.out.println((alwaysTrue() || alwaysFalse()));
        // System.out.println((alwaysTrue() | alwaysFalse()));
        
        // System.out.println((alwaysFalse() && alwaysTrue()));
        System.out.println((alwaysFalse() & alwaysTrue()));
    }

    public static boolean alwaysTrue() {
        System.out.println("True");
        return true;
    }

    public static boolean alwaysFalse() {
        System.out.println("False");
        return false;
    }
}
