package io.github.blackjack;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainComposite extends Actor {
    private List<Actor> renderComponents;

    public MainComposite() {
        renderComponents = new ArrayList<>();
    }

    // Ajouter un composant au composite
    public void addComponent(Actor component) {
        renderComponents.add(component);
    }

    // Rendre les composants sur le stage
    public void render(Stage stage) {
        for (Actor component : renderComponents) {
            stage.addActor(component);
        }
    }

    // Récupérer un composant spécifique (ici, le menu par exemple)
    public Actor getComponent(int index) {
        return renderComponents.get(index);
    }

    // Effacer tous les composants
    public void clearComponents() {
        renderComponents.clear();
    }
}
