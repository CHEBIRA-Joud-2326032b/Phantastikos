package org.phantastikos.structures.hopital.main;

import org.phantastikos.entite.creatures.Creature;
import org.phantastikos.structures.hopital.services.ServiceMedical;
import org.phantastikos.vues.VueGenerale;
import org.phantastikos.entite.medecins.Medecin;
import org.phantastikos.entite.medecins.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.phantastikos.structures.hopital.services.Budget.*;

public class Main {
    private String nomHopital;
    private List<Medecin> listeMedecins;
    private List<Creature> listePatients;
    private int compteurTour;

    // Constructeur
    public Main(String nomHopital) {
        this.nomHopital = nomHopital;
        this.listeMedecins = new ArrayList<>();
        this.listePatients = new ArrayList<>();
        this.compteurTour = 0;
    }

    // Méthodes pour ajouter et retirer des entités
    public void ajouterMedecin(Medecin medecin) {
        listeMedecins.add(medecin);
    }

    public void retirerMedecin(Medecin medecin) {
        listeMedecins.remove(medecin);
    }

    public void ajouterPatient(Creature patient) {
        listePatients.add(patient);
    }

    public void retirerPatient(Creature patient) {
        listePatients.remove(patient);
    }


    public void afficherActionsPossibles() {
        System.out.println("Actions disponibles :");
        System.out.println("1 - Ajouter un patient");
        System.out.println("2 - Soigner un patient");
        System.out.println("3 - Quitter le jeu");
    }


    // Méthode Main pour lancer le programme
    public static void main(String[] args) {
        Main hopital = new Main("Hôpital Général");

        // Création de médecins et de patients
        Medecin medecin1 = new Medecin("Dr. Prout", "M", 12);
        ServiceMedical endroitDesBG = new ServiceMedical("L'endroit des BG !", 4, 10, INEXISTANT);
        Creature loupGarou = new Creature("Loup-Garou Malade", 'M', 80.0, 1.90, 150);
        Creature vampire = new Creature("Vampire Fatigué", 'F', 65.0, 1.75, 200);

        // Ajout des entités à l'hôpital
        hopital.ajouterMedecin(medecin1);
        endroitDesBG.ajouterCreature(loupGarou);
        endroitDesBG.ajouterCreature(vampire);

        boolean jeuEnCours = true;
        int actionsRestantes = 5;
        Scanner scanner = new Scanner(System.in);

        //vues.afficherMessage("Bienvenue dans la simulation de l'hôpital !");
        //vues.afficherEtatHopital(hopital);

        while (jeuEnCours) {
            if (actionsRestantes == 0) {
                // Fin du tour
                System.out.println("Fin du tour. Mise à jour de l'état de l'hôpital...");
                //hopital.mettreAJour();
                actionsRestantes = 5;
                //vue.afficherEtatHopital(hopital);
            }

            // Afficher les actions possibles
            hopital.afficherActionsPossibles();
            String choix = scanner.nextLine();

            switch (choix) {
                case "1": // Exemple : Ajouter un patient
                    System.out.println("Nom du nouveau patient :");
                    String nomPatient = scanner.nextLine();
                    Creature patient = new Creature(nomPatient, 'F', 60.0, 1.7, 25);
                    hopital.ajouterPatient(patient);
                    actionsRestantes--;
                    break;

                case "2": // Soigner un patient
                    // Afficher les médecins disponibles
                    if (hopital.listeMedecins.isEmpty()) {
                        System.out.println("Aucun médecin disponible !");
                        break;
                    }

                    System.out.println("Médecins disponibles :");
                    for (int i = 0; i < hopital.listeMedecins.size(); i++) {
                        Medecin medecin = hopital.listeMedecins.get(i);
                        System.out.println((i + 1) + " - " + medecin.getNom());
                    }

                    // Demander à l'utilisateur de choisir un médecin
                    System.out.println("Entrez le numéro du médecin choisi :");
                    int choixMedecin;
                    try {
                        choixMedecin = Integer.parseInt(scanner.nextLine()) - 1; // Convertit l'entrée utilisateur
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                        break;
                    }

                    if (choixMedecin < 0 || choixMedecin >= hopital.listeMedecins.size()) {
                        System.out.println("Médecin introuvable !");
                        break;
                    }

                    Medecin medecinChoisi = hopital.listeMedecins.get(choixMedecin);
                    System.out.println("Médecin choisi : " + medecinChoisi.getNom());

                    // Afficher les patients disponibles
                    if (hopital.listePatients.isEmpty()) {
                        System.out.println("Aucun patient à soigner !");
                        break;
                    }

                    System.out.println("Patients disponibles :");
                    for (int i = 0; i < hopital.listePatients.size(); i++) {
                        Creature allPatient = hopital.listePatients.get(i);
                        System.out.println((i + 1) + " - " + allPatient.getNom());
                    }

                    // Demander à l'utilisateur de choisir un patient
                    System.out.println("Entrez le numéro du patient à soigner :");
                    int choixPatient;
                    try {
                        choixPatient = Integer.parseInt(scanner.nextLine()) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                        break;
                    }

                    if (choixPatient < 0 || choixPatient >= hopital.listePatients.size()) {
                        System.out.println("Patient introuvable !");
                        break;
                    }

                    Creature patientChoisi = hopital.listePatients.get(choixPatient);
                    System.out.println("Patient choisi : " + patientChoisi.getNom());

                    // Soigner le patient
                    medecinChoisi.soigner(endroitDesBG);
                    System.out.println(patientChoisi.getNom() + " a été soigné par " + medecinChoisi.getNom() + " !");
                    actionsRestantes--;
                    break;


                case "3": // Fin du jeu
                    System.out.println("Merci d'avoir joué !");
                    jeuEnCours = false;
                    break;

                default:
                    System.out.println("Choix invalide. Essayez encore.");
            }
        }
    }
}