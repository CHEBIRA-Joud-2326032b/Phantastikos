package org.phantastikos.vues;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getSettings;

public class VueGenerale extends GameApplication {

    @Override
    protected void onPreInit() {
        getSettings().setMainMenuEnabled(true);
    }
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Phantastikos");
        settings.setVersion("1.0");
        settings.setWidth(1024);
        settings.setHeight(768);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new ScenePerso());
    }

    @Override
    protected void initUI() {
        var menuScene = FXGL.getGameScene();

        ImageView background = new ImageView(new Image("assets/images/fond_jeu.jpg"));
        background.setFitWidth(FXGL.getAppWidth());
        background.setFitHeight(FXGL.getAppHeight());
        menuScene.getRoot().getChildren().add(background);

        Text title = new Text("Jour 1");
        title.setStyle("-fx-font-size: 64; -fx-font-family: 'Cinzel'; -fx-fill: black;");
        title.setEffect(new DropShadow(10, Color.BLACK));
        title.setTranslateX(10);
        title.setTranslateY(80);

        HBox menu = new HBox(20);
        menu.setTranslateX(100);
        menu.setTranslateY(100);

        menu.setSpacing(250);

        Button btnServices = boutonStylise("\uD83C\uDFE5 Hôpital \uD83C\uDFE5");

        Button btnColonie = boutonStylise("🐺 Colonie 🐺");

        Button btnQuit = boutonStylise("🚪 Quitter");
        btnQuit.setOnAction(e -> FXGL.getGameController().exit());

        menu.getChildren().addAll(btnServices, btnColonie);

        StackPane container = new StackPane(menu);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), container);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        menuScene.getRoot().getChildren().addAll(title, container);
    }

    private Button boutonStylise(String text) {
        Button btn = new Button(text);

        btn.setStyle("-fx-font-family: 'Cinzel'; -fx-font-size: 20; "
                + "-fx-text-fill: gold; "
                + "-fx-background-image: url('assets/images/fond_bois.jpg'); "
                + "-fx-background-size: cover; "
                + "-fx-background-radius: 5; "
                + "-fx-padding: 10; -fx-pref-width: 300;");

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-font-family: 'Cinzel'; -fx-font-size: 20; "
                + "-fx-text-fill: black; "
                + "-fx-background-image: url('assets/images/fond_boisHover.jpg'); "
                + "-fx-background-size: cover; "
                + "-fx-background-radius: 5; "
                + "-fx-padding: 10; -fx-pref-width: 300;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-font-family: 'Cinzel'; -fx-font-size: 20; "
                + "-fx-text-fill: gold; "
                + "-fx-background-image: url('assets/images/fond_bois.jpg'); "
                + "-fx-background-size: cover; "
                + "-fx-background-radius: 5; "
                + "-fx-padding: 10; -fx-pref-width: 300;"));
        return btn;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
