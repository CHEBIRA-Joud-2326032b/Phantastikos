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

public class Main {
    private String nomHopital;
    private List<Medecin> listeMedecins;
    private List<Creature> listePatients;
     private List<ServiceMedical> listeServices;

    private int compteurTour;

    // Constructeur
    public Main(String nomHopital) {
        this.nomHopital = nomHopital;
        this.listeMedecins = new ArrayList<>();
        this.listePatients = new ArrayList<>();
        this.listeServices = new ArrayList<>();
        this.compteurTour = 0;
    }

    // Méthodes pour ajouter et retirer des entités
    public void ajouterMedecin(Medecin medecin) {
        listeMedecins.add(medecin);
    }

    public void retirerMedecin(Medecin medecin) {
        listeMedecins.remove(medecin);
    }

    public void ajouterService(ServiceMedical Service) {
        listeServices.add(Service);
    }

    public void retirerService(ServiceMedical Service) {
        listeServices.remove(Service);
    }


    public void afficherActionsPossibles() {
        System.out.println("Actions disponibles :");
        System.out.println("1 - examiner un service médical");
        System.out.println("2 - Soigner un Service");
        System.out.println("3 - Réviser le budget");
        System.out.println("4 - Transferer une créature");
        System.out.println("5 - examiner les créatures d'un service");
    }


    // Méthode Main pour lancer le programme
    public static void main(String[] args) {
        Main hopital = new Main("Hôpital Général");

        // Création de médecins et de patients
        Medecin medecin1 = new Medecin("Dr. Prout", "M", 12);
        ServiceMedical endroitDesBG = new ServiceMedical("L'endroit des BG !", 4, 10, INEXISTANT);
        ServiceMedical ServiceSud = new ServiceMedical("Service Sud", 7, 13, MEDIOCRE);

        Creature loupGarou = new Creature("Loup-Garou Malade", 'M', 80.0, 1.90, 150);
        Creature vampire = new Creature("Vampire Fatigué", 'F', 65.0, 1.75, 200);

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

        //vues.afficherMessage("Bienvenue dans la simulation de l'hôpital !");
        //vues.afficherEtatHopital(hopital);

        while (jeuEnCours) {
            if (actionsRestantes == 0) {
                // Fin du tour
                System.out.println("Fin du tour. Mise à jour de l'état de l'hôpital...");
                for (ServiceMedical service : hopital.listeServices) {
                    for (Creature creature : service.getCreatures()) {
                        creature.attendre(); // La créature attend, son moral diminue
                    }

                    // Vérifiez les créatures qui s'emportent et gérez la contagion
                    for (Creature creature : service.getCreatures()) {
                        if (creature.estEmporte()) {
                            creature.contaminer(service.getCreatures());
                        }
                    }
                }
                actionsRestantes = 5;
                //vue.afficherEtatHopital(hopital);
            }

            // Afficher les actions possibles
            hopital.afficherActionsPossibles();

            String choix = scanner.nextLine();



            switch (choix) {
                case "1":
                    if (hopital.listeServices.isEmpty()) {
                        System.out.println("Aucun Service !");
                        break;
                    }

                    System.out.println("Services disponibles :");
                    for (int i = 0; i < hopital.listeServices.size(); i++) {
                        ServiceMedical allService = hopital.listeServices.get(i);
                        System.out.println((i + 1) + " - " + allService.getNom());
                    }

                    System.out.println("Entrez le numéro du Services à examiner :");
                    int choixServiceAffichage;
                    try {
                        choixServiceAffichage = Integer.parseInt(scanner.nextLine()) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                        break;
                    }
                    if (choixServiceAffichage < 0 || choixServiceAffichage >= hopital.listeServices.size()) {
                        System.out.println("Service introuvable !");
                        break;
                    }

                    ServiceMedical ServicesChoisiAff = hopital.listeServices.get(choixServiceAffichage);
                    System.out.println("Service choisi : " + ServicesChoisiAff.getNom());

                    // Afficher les detail, ca marche pas dans la console mais pas grave
                    ServicesChoisiAff.toString();
                    //Implementer si besoin "actionsRestantes--;" mais j'estime que c'est pas judicieux
                    //que afficher un service coute une action.
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

                    if (hopital.listeServices.isEmpty()) {
                        System.out.println("Aucun Service à soigner !");
                        break;
                    }

                    System.out.println("Services disponibles :");
                    for (int i = 0; i < hopital.listeServices.size(); i++) {
                        ServiceMedical allService = hopital.listeServices.get(i);
                        System.out.println((i + 1) + " - " + allService.getNom());
                    }

                    // Demander à l'utilisateur de choisir un patient
                    System.out.println("Entrez le numéro du Services à soigner :");
                    int choixService;
                    try {
                        choixService = Integer.parseInt(scanner.nextLine()) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                        break;
                    }

                    if (choixService < 0 || choixService >= hopital.listeServices.size()) {
                        System.out.println("Service introuvable !");
                        break;
                    }

                    ServiceMedical ServicesChoisi = hopital.listeServices.get(choixService);
                    System.out.println("Service choisi : " + ServicesChoisi.getNom());

                    // Soigner le patient
                    medecinChoisi.soigner(endroitDesBG);
                    System.out.println(ServicesChoisi.getNom() + " a été soigné par " + medecinChoisi.getNom() + " !");
                    actionsRestantes--;
                    break;
                case "3":
                    System.out.println("Choix 3");
                    actionsRestantes--;
                    System.out.println(actionsRestantes);

                    break;

                case "4": // Transférer une créature d'un service à un autre
                    if (hopital.listeServices.size() < 2) {
                        System.out.println("Impossible de transférer : il doit y avoir au moins deux services !");
                        break;
                    }

                    // Étape 1 : Sélectionner le service source
                    System.out.println("Services disponibles pour le transfert :");
                    for (int i = 0; i < hopital.listeServices.size(); i++) {
                        System.out.println((i + 1) + " - " + hopital.listeServices.get(i).getNom());
                    }
                    System.out.println("Entrez le numéro du service source :");
                    int choixServiceSource;
                    try {
                        choixServiceSource = Integer.parseInt(scanner.nextLine()) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                        break;
                    }

                    if (choixServiceSource < 0 || choixServiceSource >= hopital.listeServices.size()) {
                        System.out.println("Service introuvable !");
                        break;
                    }

                    ServiceMedical serviceSource = hopital.listeServices.get(choixServiceSource);

                    if (serviceSource.getCreatures().isEmpty()) {
                        System.out.println("Ce service ne contient aucune créature !");
                        break;
                    }

                    // Étape 2 : Sélectionner la créature à transférer
                    System.out.println("Créatures disponibles dans " + serviceSource.getNom() + " :");
                    List<Creature> creaturesSource = serviceSource.getCreatures();
                    for (int i = 0; i < creaturesSource.size(); i++) {
                        System.out.println((i + 1) + " - " + creaturesSource.get(i).getNom());
                    }
                    System.out.println("Entrez le numéro de la créature à transférer :");
                    int choixCreature;
                    try {
                        choixCreature = Integer.parseInt(scanner.nextLine()) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                        break;
                    }

                    if (choixCreature < 0 || choixCreature >= creaturesSource.size()) {
                        System.out.println("Créature introuvable !");
                        break;
                    }

                    Creature creatureADeplacer = creaturesSource.get(choixCreature);

                    // Étape 3 : Sélectionner le service cible
                    System.out.println("Services disponibles pour recevoir " + creatureADeplacer.getNom() + " :");
                    for (int i = 0; i < hopital.listeServices.size(); i++) {
                        if (i != choixServiceSource) {
                            System.out.println((i + 1) + " - " + hopital.listeServices.get(i).getNom());
                        }
                    }
                    System.out.println("Entrez le numéro du service cible :");
                    int choixServiceCible;
                    try {
                        choixServiceCible = Integer.parseInt(scanner.nextLine()) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                        break;
                    }

                    if (choixServiceCible < 0 || choixServiceCible >= hopital.listeServices.size() || choixServiceCible == choixServiceSource) {
                        System.out.println("Service cible invalide !");
                        break;
                    }

                    ServiceMedical serviceCible = hopital.listeServices.get(choixServiceCible);

                    // Étape 4 : Transférer la créature
                    serviceSource.enleverCreature(creatureADeplacer);
                    serviceCible.ajouterCreature(creatureADeplacer);
                    System.out.println(creatureADeplacer.getNom() + " a été transféré de " + serviceSource.getNom() + " à " + serviceCible.getNom() + " !");
                    actionsRestantes--;
                    break;
                case "5":
                    if (hopital.listeServices.isEmpty()) {
                        System.out.println("Aucun Service !");
                        break;
                    }

                    System.out.println("Services disponibles :");
                    for (int i = 0; i < hopital.listeServices.size(); i++) {
                        ServiceMedical allService = hopital.listeServices.get(i);
                        System.out.println((i + 1) + " - " + allService.getNom());
                    }

                    System.out.println("Entrez le numéro du Services pour y examiner les créatures :");
                    int choixCreaturesAffichage;
                    try {
                        choixCreaturesAffichage = Integer.parseInt(scanner.nextLine()) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                        break;
                    }
                    if (choixCreaturesAffichage < 0 || choixCreaturesAffichage >= hopital.listeServices.size()) {
                        System.out.println("Service introuvable !");
                        break;
                    }

                    ServiceMedical CreaturesChoisiAff = hopital.listeServices.get(choixCreaturesAffichage);
                    System.out.println("Service choisi : " + CreaturesChoisiAff.getNom());
                    List<Creature> creatures = CreaturesChoisiAff.getCreatures();

                    if (creatures.isEmpty()) {
                        System.out.println("Aucune créature dans ce service.");
                    } else {
                        System.out.println("Créatures et leurs maladies dans le service :");
                        for (Creature creature : creatures) {
                            System.out.println("- " + creature.getNom() + " :");
                            for (Maladie maladie : creature.getMaladies()) {
                                System.out.println("  * " + maladie);
                            }
                        }
                    }
                    break;


                default:
                    System.out.println("Choix invalide. Essayez encore.");
            }
        }
    }
}