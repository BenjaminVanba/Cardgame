// Chemin/Fichier : Deck.java
package io.github.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    // Ajouter une carte au deck
    public void addCard(Card card) {
        this.cards.add(card);
    }

    // Mélanger le deck
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    // Distribuer la prochaine carte
    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0); // Retire et retourne la première carte
        } else {
            throw new IllegalStateException("Le deck est vide !");
        }
    }

    public int getRemainingCards() {
        return this.cards.size();
    }
}
