// Chemin/Fichier : DeckFactory.java
package io.github.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckFactory {
    public static Deck createDeck(int numberOfDecks) {
        Deck deck = new Deck();
        for (int i = 0; i < numberOfDecks; i++) {
            for (String suit : Card.SUITS) {
                for (String rank : Card.RANKS) {
                    deck.addCard(new Card(rank, suit, false));
                }
            }
        }
        deck.shuffle();
        return deck;
    }
}
