package org.phantastikos.structures.hopital.main;

import org.phantastikos.entite.creatures.Creature;
import org.phantastikos.entite.etats.maladies.Maladie;
import org.phantastikos.entite.etats.maladies.TypeMaladie;
import org.phantastikos.structures.hopital.services.ServiceMedical;
import org.phantastikos.vues.VueGenerale;
import org.phantastikos.entite.medecins.Medecin;
import org.phantastikos.entite.medecins.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.phantastikos.entite.etats.maladies.TypeMaladie.MDC;
import static org.phantastikos.structures.hopital.services.Budget.*;

/**
 * La classe principale qui gère la simulation de l'hôpital.
 * Elle permet de gérer les médecins, les services médicaux, les créatures (patients), et d'effectuer des actions sur ces entités.
 *
 * Cette classe contient un jeu basé sur des tours où l'utilisateur peut interagir avec les services, soigner des créatures, transférer des créatures entre services, etc.
 */
public class Main {

    private String nomHopital;
    private List<Medecin> listeMedecins;
    private List<Creature> listePatients;
    private List<ServiceMedical> listeServices;
    private int compteurTour;

    /**
     * Constructeur de la classe Main.
     * Initialise l'hôpital avec un nom, des listes vides pour les médecins, les créatures et les services, et un compteur de tours à zéro.
     *
     * @param nomHopital Le nom de l'hôpital.
     */
    public Main(String nomHopital) {
        this.nomHopital = nomHopital;
        this.listeMedecins = new ArrayList<>();
        this.listePatients = new ArrayList<>();
        this.listeServices = new ArrayList<>();
        this.compteurTour = 0;
    }

    /**
     * Méthode pour ajouter un médecin à l'hôpital.
     *
     * @param medecin Le médecin à ajouter.
     */
    public void ajouterMedecin(Medecin medecin) {
        listeMedecins.add(medecin);
    }

    /**
     * Méthode pour retirer un médecin de l'hôpital.
     *
     * @param medecin Le médecin à retirer.
     */
    public void retirerMedecin(Medecin medecin) {
        listeMedecins.remove(medecin);
    }

    /**
     * Méthode pour ajouter un service médical à l'hôpital.
     *
     * @param Service Le service médical à ajouter.
     */
    public void ajouterService(ServiceMedical Service) {
        listeServices.add(Service);
    }

    /**
     * Méthode pour retirer un service médical de l'hôpital.
     *
     * @param Service Le service médical à retirer.
     */
    public void retirerService(ServiceMedical Service) {
        listeServices.remove(Service);
    }

    /**
     * Affiche les actions possibles que l'utilisateur peut choisir.
     */
    public void afficherActionsPossibles() {
        System.out.println("Actions disponibles :");
        System.out.println("1 - Examiner un service médical");
        System.out.println("2 - Soigner un Service");
        System.out.println("3 - Réviser le budget");
        System.out.println("4 - Transférer une créature");
        System.out.println("5 - Examiner les créatures d'un service");
    }

    /**
     * Méthode principale qui lance le programme et gère la boucle du jeu.
     * Permet à l'utilisateur d'interagir avec l'hôpital en choisissant des actions parmi les options proposées.
     *
     * @param args Les arguments de ligne de commande.
     */
    public static void main(String[] args) {
        Main hopital = new Main("Hôpital Général");

        // Création de médecins et de patients
        Medecin medecin1 = new Medecin("Dr. Prout", "M", 12);
        ServiceMedical endroitDesBG = new ServiceMedical("L'endroit des BG !", 4, 10, INEXISTANT);
        ServiceMedical ServiceSud = new ServiceMedical("Service Sud", 7, 13, MEDIOCRE);

        Creature loupGarou = new Creature("Loup-Garou Malade", 'M', 80, 190, 150);
        Creature vampire = new Creature("Vampire Fatigué", 'F', 65, 175, 200);

        Maladie maladieZPL = new Maladie(TypeMaladie.ZPL);
        loupGarou.ajouterMaladie(maladieZPL);

        // Ajout des entités à l'hôpital
        hopital.ajouterMedecin(medecin1);
        hopital.ajouterService(endroitDesBG);
        hopital.ajouterService(ServiceSud);
        endroitDesBG.ajouterCreature(loupGarou);
        endroitDesBG.ajouterCreature(vampire);

        boolean jeuEnCours = true;
        int actionsRestantes = 5;
        Scanner scanner = new Scanner(System.in);

        while (jeuEnCours) {
            if (actionsRestantes == 0) {
                // Fin du tour
                System.out.println("Fin du tour. Mise à jour de l'état de l'hôpital...");
                for (ServiceMedical service : hopital.listeServices) {
                    for (Creature creature : service.getCreatures()) {
                        creature.attendre(); // La créature attend, son moral diminue
                    }
                }
                actionsRestantes = 5;
            }

            // Afficher les actions possibles
            hopital.afficherActionsPossibles();

            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    // Logique pour examiner un service médical
                    break;
                case "2":
                    // Logique pour soigner un patient
                    break;
                case "3":
                    // Logique pour réviser le budget
                    break;
                case "4":
                    // Logique pour transférer une créature
                    break;
                case "5":
                    // Logique pour examiner les créatures d'un service
                    break;
                default:
                    System.out.println("Choix invalide. Essayez encore.");
            }
        }
    }
}
