package org.phantastikos.vues;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Représente le menu principal du jeu, affiché au lancement de l'application.
 * Ce menu propose des options comme "Nouvelle Partie" et "Quitter", avec des animations et des effets visuels.
 * Il hérite de la classe {@link FXGLMenu} et utilise des éléments graphiques de la bibliothèque FXGL pour créer une interface utilisateur attrayante.
 */
public class MenuPrincipal extends FXGLMenu {

    /**
     * Constructeur du menu principal.
     * Initialise le fond d'écran, le menu de boutons et applique une transition de fondu pour l'affichage du menu.
     */
    public MenuPrincipal() {
        super(MenuType.MAIN_MENU);

        // Création de l'image de fond
        ImageView background = new ImageView();
        background.setImage(new Image("assets/images/fond_accueil.jpeg"));
        background.setFitWidth(FXGL.getAppWidth());
        background.setFitHeight(FXGL.getAppHeight());

        // Création du conteneur du menu avec des boutons
        VBox menu = new VBox(20);
        menu.setTranslateX(FXGL.getAppWidth() / 2 - 100);
        menu.setTranslateY(200);

        menu.getChildren().addAll(
                implBouton("Nouvelle Partie", this::fireNewGame),  // Bouton "Nouvelle Partie"
                implBouton("Quitter", this::fireExit)  // Bouton "Quitter"
        );

        // Animation de fondu pour l'affichage du menu
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), menu);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        // Ajout de l'image de fond et du menu à la scène
        getContentRoot().getChildren().addAll(background, menu);
    }

    /**
     * Crée un bouton personnalisé avec un nom et une action associée.
     *
     * @param nom Le texte du bouton à afficher.
     * @param action L'action à exécuter lors du clic sur le bouton.
     * @return Le bouton créé.
     */
    private Node implBouton(String nom, Runnable action) {
        var button = new boutonPhantastik(nom, action);
        button.setTranslateY(FXGL.getAppHeight() / 2 - 40 / 2);  // Position verticale du bouton
        return button;
    }

    /**
     * Classe interne représentant un bouton personnalisé avec des effets visuels.
     * Le bouton change de couleur au survol et applique une ombre portée lors du survol de la souris.
     */
    private static class boutonPhantastik extends StackPane {

        /**
         * Constructeur du bouton personnalisé.
         * Crée un fond rectangulaire et un texte, applique des effets au survol et définit l'action à exécuter.
         *
         * @param name Le texte à afficher sur le bouton.
         * @param action L'action à exécuter lors du clic sur le bouton.
         */
        public boutonPhantastik(String name, Runnable action) {

            // Création de l'arrière-plan du bouton (rectangle)
            var bg = new Rectangle(200, 40);
            bg.setStroke(Color.WHITE);
            bg.setArcWidth(15);
            bg.setArcHeight(15);

            // Création du texte du bouton
            var text = FXGL.getUIFactoryService().newText(name, Color.WHITE, 18);

            // Effet de changement de couleur du fond au survol
            bg.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.GOLD).otherwise(Color.BLACK)
            );

            // Effet de changement de couleur du texte au survol
            text.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.BLACK).otherwise(Color.WHITE)
            );

            // Définition de l'action à exécuter lors du clic
            setOnMouseClicked(e -> action.run());

            // Ajout de l'arrière-plan et du texte au bouton
            getChildren().addAll(bg, text);

            // Effet d'ombre portée au survol
            setOnMouseEntered(e -> setEffect(new DropShadow(15, Color.GOLD)));
            setOnMouseExited(e -> setEffect(null));
        }
    }
}
