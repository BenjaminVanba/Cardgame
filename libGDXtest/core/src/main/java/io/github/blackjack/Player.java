package io.github.blackjack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Player extends Actor {
    protected Hand hand;
    protected double moneyAmount;
    protected String name;
    protected int playerId = 0;
    private static int lastPlayerId = 0; // Statique pour garder une trace des ID attribués

    // Cache des textures pour éviter de recréer les textures à chaque frame
    private static final Map<String, Texture> textureCache = new HashMap<>();

    public Player(String name, double moneyAmount) {
        this.playerId = ++lastPlayerId; // Attribution d'un ID unique à chaque joueur
        this.hand = new Hand(); // Initialisation de la main
        this.moneyAmount = moneyAmount;
        this.name = name;

        // Taille des cartes ajustée pour l'affichage
        setWidth(200); // Largeur des cartes
        setHeight(200 * 930 / 655); // Hauteur proportionnelle à la largeur

        // Positionner le joueur en fonction de son ID
        if (this.playerId == 2) { // S'agit du dealer, positionner sa main en haut au milieu
            setX((1920 - (getWidth() * 3)) / 2); // Centré horizontalement
            setY(1080 - 300); // Près du haut de l'écran
        }

        if (this.playerId == 1) { // S'agit du joueur humain, positionner sa main en bas au milieu
            setX((1920 - (getWidth() * 3)) / 2); // Centré horizontalement
            setY(50); // Près du bas de l'écran
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        List<Card> cards = hand.getCards();

        for (int i = 0; i < cards.size(); ++i) {
            // Récupérer le chemin de la texture de la carte

            if (!cards.get(i).isHidden()) {
                String texturePath = cards.get(i).getCardTexturePath();
                // Charger ou récupérer la texture depuis le cache
                Texture cardTexture = textureCache.computeIfAbsent(texturePath,
                        path -> new Texture(Gdx.files.internal(path)));

                // Dessiner la texture
                float cardX = getX() + (getWidth() + 10) * i; // Décalage horizontal avec un espace de 10px
                float cardY = getY();
                batch.draw(cardTexture, cardX, cardY, getWidth(), getHeight());
            } else {
                String texturePath = cards.get(i).gethiddenCardTexturePath();
                // Charger ou récupérer la texture depuis le cache
                Texture cardTexture = textureCache.computeIfAbsent(texturePath,
                        path -> new Texture(Gdx.files.internal(path)));

                // Dessiner la texture
                float cardX = getX() + (getWidth() + 10) * i; // Décalage horizontal avec un espace de 10px
                float cardY = getY();
                batch.draw(cardTexture, cardX, cardY, getWidth(), getHeight());
            }
        }
    }

    public double getMoney() {
        return this.moneyAmount;
    }

    public String getName() {
        return this.name;
    }

    public void setPositionBasedOnType(boolean isDealer) {
        if (isDealer) {
            setX((1920 - (getWidth() * 3)) / 2); // Centré horizontalement
            setY(1080 - 300); // En haut
        } else {
            setX((1920 - (getWidth() * 3)) / 2); // Centré horizontalement
            setY(50); // En bas
        }
    }

}
