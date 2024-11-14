package io.github.blackjack;

import java.util.Scanner;

public class Example {
    public static void main(String[] args) {
        System.out.println("\n-----");

        // Création du casino
        // Casino casino = new CasinoDuCoin();
        Casino casino = new CasinoPremium();

        // Bienvennu du casino
        System.out.println(
                "Bienvenu dans le casino '" + casino.getName() + "'. Ça compte en " + casino.getDevice() + " ici.");

        // Instanciation du croupier
        AIPlayer croupier = new AIPlayer("JackLeCroupier");

        // Instanciation du croupier
        HumanPlayer joueur = new HumanPlayer("JoeLeJoueur", 100);

        // Invitation au joueur de bet
        System.out.println("\nBon tu mises combien ?: ");
        Scanner scanner = new Scanner(System.in);
        int mise = scanner.nextInt();

        // Vérification de la mise
        if (mise > 0) {
            System.out.println("Tu as misé " + mise + " !");
        } else {
            System.out.println("La mise doit être positive. Réessaie.");
            // Re-demander ou gérer l'erreur ici selon le besoin
        }

    }
}