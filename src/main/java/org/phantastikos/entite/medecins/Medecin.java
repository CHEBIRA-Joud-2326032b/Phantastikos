package org.phantastikos.entite.medecins;

import org.phantastikos.entite.creatures.Creature;
import org.phantastikos.structures.hopital.services.Budget;
import org.phantastikos.structures.hopital.services.ServiceMedical;

public class Medecin {
    private final String nom;
    private final String sexe;
    private final int age;

    /**
     * Constructeur de la classe Medecin.
     * Permet de créer un médecin avec un nom, un sexe et un âge spécifiés.
     *
     * @param nom  Le nom du médecin.
     * @param sexe Le sexe du médecin.
     * @param age  L'âge du médecin.
     */
    public Medecin(String nom, String sexe, int age) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
    }

    /**
     * Effectue un examen du service médical spécifié.
     * Affiche les détails du service à travers la méthode `afficherDetails()` de la classe ServiceMedical.
     *
     * @param service Le service médical à examiner.
     */
    public void examiner(ServiceMedical service) {
        System.out.println("Examen du service : " + service.getNom());
        service.afficherDetails();
    }

    /**
     * Soigne les créatures présentes dans le service médical spécifié.
     * Cette méthode permet de réaliser des soins pour les créatures (le traitement des maladies n'est pas précisé ici).
     *
     * @param service Le service médical dans lequel les créatures doivent être soignées.
     */
    public void soigner(ServiceMedical service) {
        System.out.println(nom + " soigne les créatures du service : " + service.getNom());
        // Implémentation du soin
    }

    /**
     * Révise le budget du service médical spécifié.
     * Cette méthode permet de mettre à jour le budget du service avec un nouveau montant.
     *
     * @param service      Le service médical dont le budget doit être révisé.
     * @param nouveauBudget Le nouveau budget à appliquer au service.
     */
    public void reviserBudget(ServiceMedical service, Budget nouveauBudget) {
        System.out.println(nom + " révise le budget du service : " + service.getNom());
        service.setBudget(nouveauBudget);
        System.out.println("Nouveau budget : " + service.getBudget());
    }

    /**
     * Transfère une créature d'un service médical à un autre.
     * Cette méthode enlève la créature du service d'origine et l'ajoute au service de destination.
     * La résidence de la créature est mise à jour pour refléter le nouveau service.
     *
     * @param ancien    Le service médical d'origine.
     * @param creature  La créature à transférer.
     * @param nouveau   Le service médical de destination.
     */
    public void transfererCreature(ServiceMedical ancien, Creature creature, ServiceMedical nouveau) {
        ancien.enleverCreature(creature);
        nouveau.ajouterCreature(creature);
        creature.setResidence(nouveau);
    }

    /**
     * Retourne le nom du médecin.
     *
     * @return Le nom du médecin.
     */
    public String getNom() {
        return nom;
    }
}
