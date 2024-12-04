package org.phantastikos.structures.hopital.services;


import org.phantastikos.entite.creatures.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServiceMedical {
    private String nom;
    private double superficie;
    private int capaciteMax;
    private int budget;
    private Budget catBudget;
    private List<Creature> creatures;

    public ServiceMedical(String nom, double superficie, int capaciteMax, Budget budget) {
        this.nom = nom;
        this.superficie = superficie;
        this.capaciteMax = capaciteMax;
        this.catBudget = budget;
        this.creatures = new ArrayList<>();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setCreatures(List<Creature> creatures) {
        this.creatures = creatures;
    }

    public String getNom() {
        return nom;
    }

    public Budget getCatBudget() {
        return catBudget;
    }

    public void setCatBudget(Budget catBudget) {
        this.catBudget = catBudget;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void ajouterCreature(Creature creature) {
        if (creatures.size() < capaciteMax) {
            creatures.add(creature);
            creature.setResidence(this);
        } else {
            System.out.println("Impossible d'ajouter la créature : capacité maximale atteinte !");
        }
    }

    public void enleverCreature(Creature creature) {
        creatures.remove(creature);
        creature.setResidence(null);
    }

    public void reviserBudget() {
        if (creatures.size() >= capaciteMax-10) {
            budget -= 50;
        }
    }

    public void soignerCreatures(){
        Random aleatoire = new Random();
        for (Creature creature : creatures) {
            creature.etreSoignee(creature.getMaladies().get(aleatoire.nextInt(creature.getMaladies().size())));
        }
    }

    public String afficherDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Service Médical : ").append(nom).append("\n");
        details.append("Budget : ").append(budget).append("\n");
        details.append("Liste des créatures :\n");
        for (Creature creature : creatures) {
            details.append("- ").append(creature.getNom()).append("\n");
        }
        return details.toString();
    }
}
