package io.github.blackjack;

import java.util.Scanner;

public class Example {
    public static void main(String[] args) {
        System.out.println("\n-----");

        // Création du casino
        Casino casino = new CasinoPremium();

        // Bienvenue dans le casino
        System.out.println(
                "Bienvenu dans le casino '" + casino.getName() + "'. Ça compte en " + casino.getDevice() + " ici.");

        // Création du deck avec 6 paquets
        Deck deck = DeckFactory.createDeck(1);
        System.out.println("Deck initialisé avec " + deck.getRemainingCards() + " cartes.\n");

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
            croupierHand.drawFromDeck(deck);
            croupierHand.drawFromDeck(deck);
            joueurHand.drawFromDeck(deck);
            joueurHand.drawFromDeck(deck);

            System.out.println("\nCroupier: " + croupierHand.getCardsString() + " " + croupierHand.getScores());
            System.out.println("Joueur  : " + joueurHand.getCardsString() + " " + joueurHand.getScores());

            boolean isRoundStillGoing = true;

            // Vérifier si le joueur a un blackjack
            if (joueurHand.isBlackJack()) {
                System.out.println("\nT'es un monstre !!! Blackjack !");
                isRoundStillGoing = false;
            }

            // Gestion des choix du joueur
            while (isRoundStillGoing) {
                System.out.println("\nDistribuer ou Rester ?: ");
                String choice = scanner.nextLine();

                if (choice.equals("R")) {
                    isRoundStillGoing = false; // Le joueur choisit de rester
                } else {
                    joueurHand.drawFromDeck(deck);
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

            while (true) {
                // Scores valides pour le joueur et le croupier
                int joueurValidScore = joueurHand.getMaxValue() <= 21 ? joueurHand.getMaxValue()
                        : joueurHand.getMinValue();
                int croupierValidScore = croupierHand.getMaxValue() <= 21 ? croupierHand.getMaxValue()
                        : croupierHand.getMinValue();

                // Le croupier s'arrête immédiatement s'il a 21
                if (croupierValidScore == 21) {
                    System.out.println("\nCroupier a atteint un score de 21.");
                    break;
                }

                // Vérification si le croupier doit continuer à tirer
                if (croupierValidScore >= 17 || croupierValidScore > joueurValidScore || croupierHand.isBurnt()) {
                    break; // Le croupier arrête de tirer
                }

                // Le croupier tire une carte
                croupierHand.drawFromDeck(deck);
                System.out.println("\nCroupier: " + croupierHand.getCardsString() + " " + croupierHand.getScores());
            }

            // Déterminer le gagnant
            int joueurValidScore = joueurHand.getMaxValue() <= 21 ? joueurHand.getMaxValue() : joueurHand.getMinValue();
            int croupierValidScore = croupierHand.getMaxValue() <= 21 ? croupierHand.getMaxValue()
                    : croupierHand.getMinValue();

            if (croupierHand.isBurnt() || (joueurValidScore > croupierValidScore && joueurValidScore <= 21)) {
                System.out.println("\nFélicitations, " + joueur.getName() + " a gagné !");
            } else if (joueurValidScore == croupierValidScore && joueurValidScore <= 21) {
                System.out.println("\nÉgalité !");
            } else {
                System.out.println("\nDésolé, " + joueur.getName() + ", le croupier a gagné.");
            }
        }
    }
}
