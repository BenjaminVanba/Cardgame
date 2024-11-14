package io.github.blackjack;

import java.util.Scanner;

public class ExampleKeep {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("\nMise: ");
        String userChoice = in.nextLine();
        System.out.println("\nConfirmer: ");
        userChoice = in.nextLine();

        System.out.println("\n----------------------------");
        System.out.println("-------Giving 1st card------");
        System.out.println("----------------------------");
        Hand croupierHand = new Hand();
        Hand playerHand = new Hand();

        Card randomCard = new Card();
        System.out.println(
                "Giving this card to the croupier: " + "* " + randomCard.getRank() + " de " + randomCard.getSuit()
                        + " *");
        croupierHand.addCard(randomCard);
        randomCard = new Card();
        // randomCard = new Card("3", "Trèfles", true);
        System.out.println(
                "Giving this card to the player: " + "* " + randomCard.getValue() + " de " + randomCard.getSuit()
                        + " *");
        playerHand.addCard(randomCard);

        System.out.println("------Score des mains-------");
        System.out.println("Croupier: " + croupierHand.getValue());
        System.out.println("Joueur: " + playerHand.getValue());

        System.out.println("\n----------------------------");
        System.out.println("-------Giving 2nd card------");
        System.out.println("----------------------------");

        randomCard = new Card();
        System.out.println(
                "Giving this card to the croupier: " + "* " + randomCard.getRank() + " de " + randomCard.getSuit()
                        + " *");
        croupierHand.addCard(randomCard);
        randomCard = new Card();
        // randomCard = new Card("2", "Piques", true);
        System.out.println(
                "Giving this card to the player: " + "* " + randomCard.getRank() + " de " + randomCard.getSuit()
                        + " *");
        playerHand.addCard(randomCard);

        System.out.println("------Score des mains-------");
        System.out.println("Croupier: " + croupierHand.getValue());
        System.out.println("Joueur: " + playerHand.getValue());

        if (playerHand.isBlackJack()) {
            System.out.println("Oh le boss tia un blackjack bv");
        }

        System.out.println("\nRester ou Distribuer ?: ");
        userChoice = in.nextLine();

        if (userChoice.equals("D")) {
            System.out.println("Distruber");
            while (!playerHand.isBurnt() && userChoice.equals("D")) {
                // randomCard = new Card("2", "Pique", true);
                randomCard = new Card();
                System.out.println(
                        "Giving this card to the player: " + "* " + randomCard.getRank() + " de "
                                + randomCard.getSuit()
                                + " *");
                playerHand.addCard(randomCard);
                System.out.println("------Score des mains-------");
                System.out.println("Croupier: " + croupierHand.getValue());
                System.out.println("Joueur: " + playerHand.getValue());
                System.out.println("\nRester ou Distribuer ?: ");
                userChoice = in.nextLine();
            }
        } else if (userChoice.equals("R")) {
            System.out.println("Rester");
        }
        // randomCard.DisplayInfo(randomCard);
    }
}