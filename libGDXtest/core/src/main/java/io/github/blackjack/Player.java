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
        this.playerId = ++lastPlayerId;
        this.hand = new Hand();
        this.moneyAmount = moneyAmount;
        this.name = name;

        // Taille des cartes ajustée pour l'affichage
        setWidth(100); // Largeur réduite pour s'adapter à l'écran
        setHeight(100 * 930 / 655); // Hauteur proportionnelle à la largeur

        // Positionner le joueur en fonction de son ID
        if (this.playerId == 2) { // S'agit du dealer, mettre sa main en haut
            setX((1920 / 2) - (getWidth() * 3)); // Centré
            setY(1080 - 250);
        }

        if (this.playerId == 1) { // S'agit du joueur humain, mettre sa main en bas
            setX((1920 / 2) - (getWidth() * 3)); // Centré
            setY(50);
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
}
