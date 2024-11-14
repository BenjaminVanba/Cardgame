package io.github.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
    protected ArrayList<Card> cards = new ArrayList<>();

    private List<Integer> scores = new ArrayList<>(Arrays.asList(0, 0)); // Liste modifiable pour stocker deux valeurs
                                                                         // de score

    public void DisplayInfo() {
        // TODO Auto-generated method stub
        // doit loop dans les cartes et écrire leur info
    }

    public List<Integer> getValue() {
        return this.scores;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);

        // Si la carte ajoutée est un As
        if (card.isAce()) {
            if (cards.size() == 1) { // Vérifie si c'est la première carte de la main
                scores.set(0, 1); // Compte l'As comme 11 pour le premier score
                scores.set(1, 11); // Compte également l'As comme 11 pour le deuxième score
            } else {
                scores.set(0, scores.get(0) + 1);
                scores.set(1, scores.get(1) + 1);
            }
        } else {
            int cardValue = card.getValue(); // Récupère la valeur de la carte
            scores.set(0, scores.get(0) + cardValue); // Ajoute la valeur au premier score
            scores.set(1, scores.get(1) + cardValue); // Ajoute la valeur au deuxième score
        }
    }

    public boolean isBlackJack() {
        if (cards.size() == 2) { // Vérifie qu'il y a exactement deux cartes
            if (scores.get(0) == 21 | scores.get(1) == 21) {
                return true;
            }
        }
        return false;
    }

    public boolean isBurnt() {
        return scores.get(0) > 21 && this.isBlackJack() == false;
    }
}
