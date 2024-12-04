package org.phantastikos.entite.medecins;


import org.phantastikos.entite.creatures.Creature;
import org.phantastikos.structures.hopital.services.Budget;
import org.phantastikos.structures.hopital.services.ServiceMedical;

public class Medecin {
    private final String nom;
    private final String sexe;
    private final int age;

    public Medecin(String nom, String sexe, int age) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
    }

    public void examiner(ServiceMedical service) {
        System.out.println("Examen du service : " + service.getNom());
        service.afficherDetails();
    }

    public void soigner(ServiceMedical service) {
        System.out.println(nom + " soigne les créatures du service : " + service.getNom());
    }

    public void reviserBudget(ServiceMedical service) {
        service.reviserBudget();
    }

    public void transfererCreature(ServiceMedical ancien, Creature creature, ServiceMedical nouveau) {
        ancien.enleverCreature(creature);
        nouveau.ajouterCreature(creature);
        creature.setResidence(nouveau);
    }

    public String getNom() {
        return nom;
    }
}
