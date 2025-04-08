package fr.formation;

import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Client client = new Client();
            String value = "";

            client.connect();

            value = sc.nextLine();

            while (!"quit".equals(value)) {
                Evaluation eval = client.sendAndReceive(value + "\n");

                if (eval.getType() == 0) {
                    System.out.println("JSON : " + new String(eval.getValue()));
                }

                else if (eval.getType() == 1) {
                    System.out.println("Propriété : " + new String(eval.getValue()));
                }

                else if (eval.getType() == 2) {
                    System.out.println("Image ...");
                }

                value = sc.nextLine();
            }

            client.disconnect();
        }
    }
}