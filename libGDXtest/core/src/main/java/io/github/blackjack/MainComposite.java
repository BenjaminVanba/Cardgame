package io.github.blackjack;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainComposite extends Actor {
    private List<Actor> renderComponents; // Liste des composants d'affichage

    public MainComposite() {
        renderComponents = new ArrayList<>();
    }

    // Ajouter un composant au composite
    public void addComponent(Actor component) {
        renderComponents.add(component);
    }

    // Gérer le rendu pour chaque composant ajouté
    public void render(Stage stage) {
        for (Actor component : renderComponents) {
            stage.addActor(component);
        }
    }
}
