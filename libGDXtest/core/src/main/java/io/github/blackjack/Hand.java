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

    public List<Integer> getScores() {
        return this.scores;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public void unhideCard() {
        for (Card card : this.cards) {
            card.hidden = false; // Mettre `hidden` à `false` pour chaque carte
        }
    }

    public String getCardsString() {
        StringBuilder result = new StringBuilder();
        for (Card card : this.cards) {
            if (!card.hidden) {
                result.append(card.rank).append(" de ").append(card.suit).append(", ");
            } else {
                result.append("Carte cachée, ");
            }
        }
        // Retirer la dernière virgule et l'espace
        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }
        return result.toString();
    }

    public void addCard(Card card) {
        this.cards.add(card);

        // Si la carte ajoutée est un As
        if (card.isAce()) {
            scores.set(0, scores.get(0) + 1);
            scores.set(1, scores.get(1) + 11);
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
        return scores.get(0) > 21 && scores.get(1) > 21 && this.isBlackJack() == false;
    }

    // Retourne la valeur minimale dans la liste de scores
    public int getMinValue() {
        return scores.stream().min(Integer::compareTo).orElse(0);
    }

    // Retourne la valeur maximale dans la liste de scores
    public int getMaxValue() {
        return scores.stream().max(Integer::compareTo).orElse(0);
    }
}
