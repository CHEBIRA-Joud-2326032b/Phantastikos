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

public class MenuPrincipal extends FXGLMenu {

    public MenuPrincipal() {
        super(MenuType.MAIN_MENU);

        ImageView background = new ImageView();
        background.setImage(new Image("assets/images/fond_accueil.jpeg"));
        background.setFitWidth(FXGL.getAppWidth());
        background.setFitHeight(FXGL.getAppHeight());


        VBox menu = new VBox(20);
        menu.setTranslateX(FXGL.getAppWidth() / 2 - 100);
        menu.setTranslateY(200);

        menu.getChildren().addAll(
                implBouton("Nouvelle Partie", this::fireNewGame),
                implBouton("Quitter", this::fireExit)
        );

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), menu);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        getContentRoot().getChildren().addAll(background,menu);
    }

    private Node implBouton(String nom, Runnable action) {
        var button = new boutonPhantastik(nom, action);
        button.setTranslateY(FXGL.getAppHeight() / 2 - 40 / 2);
        return button;
    }


    private static class boutonPhantastik extends StackPane {
        public boutonPhantastik(String name, Runnable action) {

            var bg = new Rectangle(200, 40);
            bg.setStroke(Color.WHITE);
            bg.setArcWidth(15);
            bg.setArcHeight(15);

            var text = FXGL.getUIFactoryService().newText(name, Color.WHITE, 18);

            bg.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.GOLD).otherwise(Color.BLACK)
            );

            text.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.BLACK).otherwise(Color.WHITE)
            );

            setOnMouseClicked(e -> action.run());

            getChildren().addAll(bg, text);

            setOnMouseEntered(e -> setEffect(new DropShadow(15, Color.GOLD)));
            setOnMouseExited(e -> setEffect(null));
        }
    }
}
