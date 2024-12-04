package org.phantastikos.vues;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.addUINode;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getSettings;

/**
 * Représente l'interface principale du jeu, en gérant l'affichage et les interactions avec les utilisateurs.
 * Cette classe étend {@link GameApplication} et permet de configurer l'interface du jeu, la gestion des événements utilisateur,
 * ainsi que l'affichage des différents éléments du jeu comme la colonie, les meutes, et les lycanthropes.
 */
public class VueGenerale extends GameApplication {

    private Colonie colonie; // La colonie actuelle
    private VBox sectionButtons; // Contient les boutons du menu principal
    private VBox statsPane; // Contient les informations statistiques
    private VBox popUp; // Fenêtre pop-up pour afficher des détails

    /**
     * Initialisation du jeu, où la colonie et les meutes sont créées si elles n'existent pas.
     */
    @Override
    protected void initGame() {
        if (colonie == null) {
            colonie = new Colonie();
            Lycanthrope lyc1 = new Lycanthrope("Fenrir", 'M', 80, 185, 25, 15, 10);
            Lycanthrope lyc2 = new Lycanthrope("Cerbere", 'F', 65, 170, 22, 5, 20);
            Meute meute1 = new Meute(colonie, "La meute", List.of(lyc1, lyc2), "agrouuu");
            colonie.ajouterMeute(meute1);
        }
    }

    /**
     * Configuration préliminaire avant l'initialisation du jeu, ici pour activer le menu principal.
     */
    @Override
    protected void onPreInit() {
        getSettings().setMainMenuEnabled(true);
    }

    /**
     * Initialisation des paramètres de jeu comme le titre, la taille de la fenêtre et la scène principale.
     *
     * @param settings Paramètres de configuration du jeu.
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Phantastikos");
        settings.setVersion("1.0");
        settings.setWidth(1024);
        settings.setHeight(768);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new ScenePerso());
    }

    /**
     * Initialisation de l'interface utilisateur, création des éléments visuels comme les boutons et les sections.
     */
    @Override
    protected void initUI() {
        var menuScene = FXGL.getGameScene();

        // Fond d'écran
        ImageView background = new ImageView(new Image("assets/images/fond_jeu.jpg"));
        background.setFitWidth(FXGL.getAppWidth());
        background.setFitHeight(FXGL.getAppHeight());
        menuScene.getRoot().getChildren().add(background);

        // Configuration des sections pour les boutons et les statistiques
        double appWidth = FXGL.getAppWidth();
        double appHeight = FXGL.getAppHeight();

        sectionButtons = new VBox(10);
        sectionButtons.setLayoutX(0);
        sectionButtons.setLayoutY(200);
        sectionButtons.setPrefWidth(appWidth * 0.7);
        sectionButtons.setPrefHeight(appHeight - 200);
        sectionButtons.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 10;");
        addUINode(sectionButtons);

        statsPane = new VBox(10);
        statsPane.setLayoutX(appWidth * 0.7);
        statsPane.setLayoutY(200);
        statsPane.setPrefWidth(appWidth * 0.3);
        statsPane.setPrefHeight(appHeight - 200);
        statsPane.setStyle("-fx-background-color: rgba(50, 50, 50, 0.8); -fx-padding: 10;");
        addUINode(statsPane);

        // Titre du jeu
        Text title = new Text("Jour 1");
        title.setStyle("-fx-font-size: 64; -fx-font-family: 'Cinzel'; -fx-fill: black;");
        title.setEffect(new DropShadow(10, Color.BLACK));
        title.setTranslateX(10);
        title.setTranslateY(80);

        // Menu principal
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

        // Effet de transition de fade-in
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), container);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        menuScene.getRoot().getChildren().addAll(sectionButtons, statsPane, title, container);
    }

    /**
     * Affiche la section Colonie avec les boutons pour chaque meute et les options associées.
     */
    private void afficherColonie() {
        sectionButtons.getChildren().clear();

        if (colonie == null || colonie.getMeutes().isEmpty()) {
            Text message = new Text("Aucune meute n'est disponible.");
            message.setStyle("-fx-font-size: 18px; -fx-fill: white;");
            sectionButtons.getChildren().add(message);
            return;
        }

        Button btnAvancerTemps = new Button("Avancer le Temps");
        btnAvancerTemps.setOnAction(e -> colonie.avancerTemps());

        sectionButtons.getChildren().add(btnAvancerTemps);

        for (Meute meute : colonie.getMeutes()) {
            Button btnMeute = new Button(meute.getNom());
            btnMeute.setStyle("-fx-font-size: 16px; -fx-padding: 10;");

            btnMeute.setOnAction(e -> {
                FXGL.getNotificationService().pushNotification("Meute sélectionnée : " + meute.getNom());
                afficherPopUp(meute);
            });

            sectionButtons.getChildren().add(btnMeute);
        }
    }

    /**
     * Affiche un pop-up pour afficher les options associées à une meute spécifique.
     *
     * @param meute La meute pour laquelle le pop-up doit être affiché.
     */
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
            revenir(List.of(fond, popUp));
        });

        Button btnVoirLycanthropes = new Button("Voir Lycanthropes");
        btnVoirLycanthropes.setStyle("-fx-font-size: 16; -fx-background-color: #505050; -fx-text-fill: white;");
        btnVoirLycanthropes.setOnAction(e -> {
            afficherLycanthropes(meute);
            revenir(List.of(fond, popUp));
        });

        Button btnRetour = new Button("Retour");
        btnRetour.setStyle("-fx-font-size: 16; -fx-background-color: #505050; -fx-text-fill: white;");
        btnRetour.setOnAction(e -> {
            revenir(List.of(fond, popUp));
        });

        popUp.getChildren().addAll(titreMenu, btnVoirCaracteristiques, btnVoirLycanthropes, btnRetour);

        menuScene.getRoot().getChildren().addAll(fond, popUp);
    }

    /**
     * Affiche la liste des lycanthropes d'une meute donnée et leurs caractéristiques.
     *
     * @param meute La meute dont les lycanthropes doivent être affichés.
     */
    private void afficherLycanthropes(Meute meute) {
        statsPane.getChildren().clear();
        sectionButtons.getChildren().clear();

        Text titre = new Text("Lycanthropes de la Meute: " + meute.getNom());
        titre.setStyle("-fx-font-size: 20px; -fx-fill: white;");
        sectionButtons.getChildren().add(titre);

        for (Lycanthrope lycanthrope : meute.getMembres()) {
            Button btnLycanthrope = new Button(lycanthrope.getNom());
            btnLycanthrope.setStyle("-fx-font-size: 16px; -fx-padding: 10;");
            btnLycanthrope.setOnAction(e -> afficherStats(lycanthrope.recupererAttributs()));
            sectionButtons.getChildren().add(btnLycanthrope);
        }

        Button btnRetour = new Button("Retour");
        btnRetour.setStyle("-fx-font-size: 16px; -fx-padding: 10;");
        btnRetour.setOnAction(e -> afficherColonie());
        sectionButtons.getChildren().add(btnRetour);
    }

    /**
     * Crée un bouton stylisé avec un fond, une couleur de texte et un effet de survol.
     *
     * @param text Le texte à afficher sur le bouton.
     * @return Le bouton stylisé.
     */
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

    /**
     * Affiche les statistiques d'une entité sous forme de texte dans la section des statistiques.
     *
     * @param stats Le map contenant les caractéristiques à afficher.
     */
    private void afficherStats(Map<String, String> stats) {
        statsPane.getChildren().clear();

        for (Map.Entry<String, String> entry : stats.entrySet()) {
            Text statText = new Text(entry.getKey() + ": " + entry.getValue());
            statText.setStyle("-fx-font-size: 16px; -fx-fill: white;");
            statsPane.getChildren().add(statText);
        }

        statsPane.setVisible(true);
    }

    /**
     * Affiche l'hôpital (actuellement sans fonction définie).
     */
    private void afficherHopital() {
        sectionButtons.getChildren().clear();
    }

    /**
     * Permet de revenir à l'écran précédent en retirant les éléments spécifiés.
     *
     * @param ele La liste des éléments à retirer.
     */
    private void revenir(List ele) {
        FXGL.getGameScene().getRoot().getChildren().removeAll(ele);
    }

    /**
     * Lancement de l'application de jeu.
     *
     * @param args Arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
