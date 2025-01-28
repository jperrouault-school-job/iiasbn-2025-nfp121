public class SwitchApplication {
    public static void main(String[] args) {
        int value = 0;

        switch (value) {
            default: System.out.println("DEF");
            case 2: System.out.println("2");
            case 4: System.out.println("4"); break;
            case 5:
            case 6:
                System.out.println("5 ou 6");
        }
    }
}
