package org.phantastikos.vues;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;
import org.phantastikos.structures.colonie.Colonie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.addUINode;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getSettings;

public class VueGenerale extends GameApplication {
    private Colonie colonie;
    private VBox sectionButtons;
    private VBox statsPane;
    private VBox popUp;
    private ScrollPane scrollPane;

    @Override
    protected void initGame() {

        if (colonie == null) {
            colonie = new Colonie();
            Lycanthrope lyc1 = new Lycanthrope("Fenrir", 'M', 80, 185, 25, 15, 10);
            Lycanthrope lyc2 = new Lycanthrope("Cerbere", 'F', 65, 170, 22, 5, 20);
            Meute meute1 = new Meute(colonie, "Meute 1", List.of(lyc1,lyc2),"Hurlement 1");
            lyc1.setMeute(meute1);
            lyc2.setMeute(meute1);
            colonie.ajouterMeute(meute1);
        }
    }
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

        double appWidth = FXGL.getAppWidth();
        double appHeight = FXGL.getAppHeight();

        sectionButtons = new VBox(10);
        sectionButtons.setLayoutX(0);
        sectionButtons.setLayoutY(200);
        sectionButtons.setPrefWidth(appWidth * 0.6);
        sectionButtons.setPrefHeight(appHeight-200);
        sectionButtons.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 10;");

        scrollPane = new ScrollPane();
        scrollPane.setLayoutX(appWidth * 0.6);
        scrollPane.setLayoutY(200);
        scrollPane.setPrefWidth(appWidth * 0.4);
        scrollPane.setPrefHeight(appHeight - 200);
        scrollPane.setStyle("-fx-background: transparent;-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 10;");

        String cssPath = getClass().getResource("/scrollpane.css").toExternalForm();
        scrollPane.getStylesheets().add(cssPath);

        statsPane = new VBox(10);
        statsPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setContent(statsPane);



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
        btnServices.setOnAction(e -> { afficherHopital();});
        Button btnColonie = boutonStylise("🐺 Colonie 🐺");
        btnColonie.setOnAction(e -> afficherColonie());
        Button btnQuit = boutonStylise("🚪 Quitter");
        btnQuit.setOnAction(e -> FXGL.getGameController().exit());
        menu.getChildren().addAll(btnServices, btnColonie, btnQuit);

        StackPane container = new StackPane(menu);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), container);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        menuScene.getRoot().getChildren().addAll(sectionButtons,scrollPane,title, container);
    }

    private void afficherRapportDansStatsPane(String rapport) {
        statsPane.getChildren().clear();
        Text texteRapport = new Text(rapport);
        texteRapport.setStyle("-fx-font-size: 14px; -fx-fill: white;");
        statsPane.getChildren().add(texteRapport);
    }
    private void ajouterBoutonsDansGrille(List<Button> boutons, GridPane grille, int nbColonnes, Button boutonRetour) {
        grille.getChildren().clear();

        int totalBoutons = boutons.size();
        int nbLignes = (int) Math.ceil((double) (totalBoutons + 1) / nbColonnes);

        int boutonIndex = 0;
        for (int ligne = 0; ligne < nbLignes; ligne++) {
            for (int col = 0; col < nbColonnes; col++) {
                if (ligne == 0 && col == nbColonnes - 1) {
                    grille.add(boutonRetour, col, ligne);
                } else if (boutonIndex < totalBoutons) {
                    grille.add(boutons.get(boutonIndex), col, ligne);
                    boutonIndex++;
                }
            }
        }
    }
    private void afficherColonie() {

        sectionButtons.getChildren().clear();

        Button btnAvancerTemps = new Button("Avancer le Temps");
        btnAvancerTemps.setOnAction(e -> {
            afficherRapportDansStatsPane(colonie.avancerTemps());
            afficherColonie();
        });

        sectionButtons.getChildren().add(btnAvancerTemps);

        GridPane grille = new GridPane();
        grille.setHgap(10);
        grille.setVgap(10);
        ArrayList<Button> boutons = new ArrayList<>();
        for (Meute meute : colonie.getMeutes()) {
            Button btnMeute = new Button(meute.getNom());
            btnMeute.setStyle("-fx-font-size: 16px; -fx-padding: 10;");
            btnMeute.setOnAction(e -> {
                FXGL.getNotificationService().pushNotification("Meute sélectionnée : " + meute.getNom());
                afficherPopUp(meute);
            });
            boutons.add(btnMeute);
        }
        Button btnRetour = new Button("Retour");
        btnRetour.setStyle("-fx-font-size: 16px; -fx-padding: 10;");
        btnRetour.setOnAction(e -> sectionButtons.getChildren().clear());
        ajouterBoutonsDansGrille(boutons, grille, 5, btnRetour);
        ScrollPane scroll = new ScrollPane(grille);
        scroll.setFitToWidth(true);
        scroll.setPrefHeight(sectionButtons.getPrefHeight());
        sectionButtons.getChildren().add(scroll);
    }


    private void afficherPopUp(Meute meute) {
        var menuScene = FXGL.getGameScene();
        Rectangle fond = new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight());
        fond.setFill(Color.color(0, 0, 0, 0.5));
        fond.setOnMouseClicked(event -> {
            event.consume();
        });

        popUp = new VBox(10);
        popUp.setStyle("-fx-background-color: #2e2e2e; -fx-padding: 20; -fx-border-color: white; -fx-border-width: 2;");
        popUp.setEffect(new DropShadow(15, Color.BLACK));
        popUp.setAlignment(javafx.geometry.Pos.CENTER);

        popUp.setLayoutX(FXGL.getAppWidth() / 2 - 150);
        popUp.setLayoutY(FXGL.getAppHeight() / 2 - 100);

        Text titreMenu = new Text("Choisissez une option");
        titreMenu.setStyle("-fx-font-size: 18; -fx-fill: white;");
        titreMenu.setEffect(new DropShadow(10, Color.BLACK));

        Button btnVoirCaracteristiques = new Button("Voir Caractéristiques");
        btnVoirCaracteristiques.setStyle("-fx-font-size: 16; -fx-background-color: #505050; -fx-text-fill: white;");
        btnVoirCaracteristiques.setOnAction(e -> {
            afficherStats(meute.recupererAttributs());
            enlever(List.of(fond, popUp));
        });

        Button btnVoirLycanthropes = new Button("Voir Lycanthropes");
        btnVoirLycanthropes.setStyle("-fx-font-size: 16; -fx-background-color: #505050; -fx-text-fill: white;");
        btnVoirLycanthropes.setOnAction(e -> {
            afficherLycanthropes(meute);
            enlever(List.of(fond, popUp));
        });

        Button btnRetour = new Button("Retour");
        btnRetour.setStyle("-fx-font-size: 16; -fx-background-color: #505050; -fx-text-fill: white;");
        btnRetour.setOnAction(e -> {
            enlever(List.of(fond, popUp));
        });

        popUp.getChildren().addAll(titreMenu, btnVoirCaracteristiques, btnVoirLycanthropes, btnRetour);

        menuScene.getRoot().getChildren().addAll(fond, popUp);
    }

    private void afficherLycanthropes(Meute meute) {
        statsPane.getChildren().clear();
        sectionButtons.getChildren().clear();

        Text titre = new Text("Lycanthropes de la Meute: " + meute.getNom());
        titre.setStyle("-fx-font-size: 20px; -fx-fill: white;");
        sectionButtons.getChildren().add(titre);

        GridPane grille = new GridPane();
        grille.setHgap(10);
        grille.setVgap(10);
        ArrayList<Button> boutons = new ArrayList<>();
        for (Lycanthrope lycanthrope : meute.getMembres()) {
            Button btnLycanthrope = new Button(lycanthrope.getNom());
            btnLycanthrope.setStyle("-fx-font-size: 16px; -fx-padding: 10;");

            btnLycanthrope.setOnAction(e -> afficherStats(lycanthrope.recupererAttributs()));

            sectionButtons.getChildren().add(btnLycanthrope);
            boutons.add(btnLycanthrope);
        }
        Button btnRetour = new Button("Retour");
        btnRetour.setStyle("-fx-font-size: 16px; -fx-padding: 10; -fx-background-color: red;-fx-text-fill: white");
        btnRetour.setOnAction(e -> afficherColonie());
        ajouterBoutonsDansGrille(boutons, grille, 5, btnRetour);
        ScrollPane scroll = new ScrollPane(grille);
        scroll.setFitToWidth(true);
        scroll.setPrefHeight(sectionButtons.getPrefHeight());
        sectionButtons.getChildren().add(scroll);
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

    private void afficherStats(Map<String, String> stats) {
        statsPane.getChildren().clear();

        for (Map.Entry<String, String> entry : stats.entrySet()) {
            Text statText = new Text(entry.getKey() + ": " + entry.getValue());

            statText.setStyle("-fx-font-size: 16px; -fx-fill: white;");

            statsPane.getChildren().add(statText);
        }

        statsPane.setVisible(true);
    }


    private void afficherHopital() {
        sectionButtons.getChildren().clear();

    }

    private void enlever(List ele) {
        FXGL.getGameScene().getRoot().getChildren().removeAll(ele);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
