// Chemin/Fichier : Deck.java
package io.github.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();

        // Ajouter manuellement toutes les cartes (4 couleurs * 13 rangs)
        String[] suits = { "Piques", "Coeurs", "Carreaux", "Trefles" };
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

        for (String suit : suits) {
            for (String rank : ranks) {
                this.cards.add(new Card(rank, suit, true)); // `false` signifie que la carte n'est pas cachée
            }
        }

        // Mélanger le deck
        Collections.shuffle(this.cards);

        // TODO @RH: Create shuffled 52 cards deck

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

    // Retourne la prochaine carte sans la retirer
    public Card peekNextCard() {
        if (!cards.isEmpty()) {
            return cards.get(0); // Retourne la première carte du deck
        } else {
            throw new IllegalStateException("Le deck est vide !");
        }
    }

    public int getRemainingCards() {
        return this.cards.size();
    }
}
