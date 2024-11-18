package main.vues;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VueGenerale extends Application {

    @Override
    public void start(Stage primaryStage) {
        // --- Fenêtre principale ---
        primaryStage.setTitle("Phantastikos Management System");

        // --- Menu principal ---
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Fichier");
        fileMenu.getItems().addAll(
                new MenuItem("Nouveau jeu"),
                new MenuItem("Charger"),
                new MenuItem("Sauvegarder"),
                new SeparatorMenuItem(),
                new MenuItem("Quitter")
        );

        Menu helpMenu = new Menu("Aide");
        helpMenu.getItems().addAll(
                new MenuItem("Règles"),
                new MenuItem("À propos")
        );

        menuBar.getMenus().addAll(fileMenu, helpMenu);

        // --- Tableau de bord ---
        VBox dashboard = createDashboard();

        // --- Section centrale : Liste des services médicaux ---
        VBox serviceList = createServiceList();

        // --- Actions principales ---
        HBox actionsBox = createActionsBox();

        // --- Mise en page principale ---
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(serviceList);
        root.setBottom(actionsBox);
        root.setLeft(dashboard);

        // --- Scène ---
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Création du tableau de bord
     */
    private VBox createDashboard() {
        VBox dashboard = new VBox();
        dashboard.setPadding(new Insets(10));
        dashboard.setSpacing(10);
        dashboard.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Label hospitalName = new Label("Phantastikos");
        hospitalName.setStyle("-fx-font-size: 18px; -fx-text-fill: black;");

        Label totalCreatures = new Label("Nombre total de créatures : 0");
        Label moralAverage = new Label("Moral moyen : N/A");
        Label budget = new Label("Budget total : Faible");

        dashboard.getChildren().addAll(hospitalName, totalCreatures, moralAverage, budget);

        return dashboard;
    }

    /**
     * Création de la liste des services médicaux
     */
    private VBox createServiceList() {
        VBox serviceList = new VBox();
        serviceList.setPadding(new Insets(10));
        serviceList.setSpacing(10);
        serviceList.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Label title = new Label("Services médicaux");
        title.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

        ListView<String> serviceListView = new ListView<>();
        serviceListView.getItems().addAll(
                "Triage (Orques, Zombies, etc.)",
                "Centre de quarantaine",
                "Crypte"
        );

        serviceList.getChildren().addAll(title, serviceListView);

        return serviceList;
    }

    /**
     * Création de la barre d'actions principales
     */
    private HBox createActionsBox() {
        HBox actionsBox = new HBox();
        actionsBox.setPadding(new Insets(10));
        actionsBox.setSpacing(20);
        actionsBox.setStyle("-fx-background-color: white;");

        Button addServiceButton = new Button("Ajouter un service");
        Button manageDoctorsButton = new Button("Gérer les médecins");
        Button simulateButton = new Button("Simuler une journée");

        // Changer la couleur du texte des boutons en noir
        addServiceButton.setStyle("-fx-text-fill: black;");
        manageDoctorsButton.setStyle("-fx-text-fill: black;");
        simulateButton.setStyle("-fx-text-fill: black;");

        actionsBox.getChildren().addAll(addServiceButton, manageDoctorsButton, simulateButton);

        return actionsBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
