package io.github.blackjack;

import java.util.Arrays;
import java.util.Scanner;

public class Example {
    public static void main(String[] args) {
        System.out.println("\n-----");

        // Création du casino
        Casino casino = new CasinoPremium();

        // Bienvenue dans le casino
        System.out.println(
                "Bienvenu dans le casino '" + casino.getName() + "'. Ça compte en " + casino.getDevice() + " ici.");

        // Instanciation du croupier
        AIPlayer croupier = new AIPlayer("JackLeCroupier");
        Hand croupierHand = croupier.hand;

        // Instanciation du joueur
        HumanPlayer joueur = new HumanPlayer("JoeLeJoueur", 100);
        Hand joueurHand = joueur.hand;

        // Invitation au joueur de miser
        System.out.println("\nBon tu mises combien ?: ");
        try (Scanner scanner = new Scanner(System.in)) {
            int mise = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne résiduel

            if (mise > 0) {
                System.out.println("Tu as misé " + mise + " " + casino.device + " !\n");
            } else {
                System.out.println("La mise doit être positive. Réessaie.\n");
                return; // Arrêter si la mise est invalide
            }

            // Distribution des cartes initiales
            croupierHand.addCard(new Card(false));
            croupierHand.addCard(new Card(true));
            joueurHand.addCard(new Card(false));
            joueurHand.addCard(new Card(false));

            System.out.println("\nCroupier: " + croupierHand.getCardsString() + " " + croupierHand.getScores());
            System.out.println("Joueur  : " + joueurHand.getCardsString() + " " + joueurHand.getScores());

            // Vérifier si le joueur a un blackjack
            if (joueurHand.isBlackJack()) {
                System.out.println("\nT'es un monstre !!! Blackjack !");
            }

            // Gestion des choix du joueur
            boolean isRoundStillGoing = true;
            while (isRoundStillGoing) {
                System.out.println("\nDistribuer ou Rester ?: ");
                String choice = scanner.nextLine();

                if (choice.equals("R")) {
                    isRoundStillGoing = false; // Le joueur choisit de rester
                } else {
                    joueurHand.addCard(new Card(false));
                    System.out.println("\nCroupier: " + croupierHand.getCardsString() + " " + croupierHand.getScores());
                    System.out.println("Joueur  : " + joueurHand.getCardsString() + " " + joueurHand.getScores());
                    if (joueurHand.isBurnt()) {
                        System.out.println("Ha grosse merde t'es burn!!!");
                        return;
                    }
                }
            }

            // Croupier joue après que le joueur reste
            croupierHand.unhideCard();
            System.out.println("\nCroupier: " + croupierHand.getCardsString() + " " + croupierHand.getScores());

            while ((croupierHand.getMinValue() < 17 || croupierHand.getMaxValue() < 17) &&
                    (croupierHand.getMinValue() <= joueurHand.getMaxValue()) &&
                    !croupierHand.isBurnt()) {
                croupierHand.addCard(new Card(false));
                System.out.println("\nCroupier: " + croupierHand.getCardsString() + " " + croupierHand.getScores());
                System.out.println("Joueur  : " + joueurHand.getCardsString() + " " + joueurHand.getScores());
            }

            // Déterminer le gagnant
            if (croupierHand.isBurnt() || joueurHand.getMaxValue() > croupierHand.getMaxValue()) {
                System.out.println("\nFélicitations, " + joueur.getName() + " a gagné !");
            } else if (joueurHand.getMaxValue() == croupierHand.getMaxValue()) {
                System.out.println("\nÉgalité !");
            } else {
                System.out.println("\nDésolé, " + joueur.getName() + ", le croupier a gagné.");
            }
        }
    }
}
