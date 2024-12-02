package org.phantastikos.structures.hopital.services;


import org.phantastikos.entite.creatures.Creature;

import java.util.ArrayList;
import java.util.List;

public class ServiceMedical {
    private String nom;
    private double superficie;
    private int capaciteMax;
    private Budget budget;
    private List<Creature> creatures;

    public ServiceMedical(String nom, double superficie, int capaciteMax, Budget budget) {
        this.nom = nom;
        this.superficie = superficie;
        this.capaciteMax = capaciteMax;
        this.budget = budget;
        this.creatures = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void ajouterCreature(Creature creature) {
        if (creatures.size() == capaciteMax) {
            creatures.add(creature);
            creature.setResidence(this);
        }

    }

    public void enleverCreature(Creature creature) {
        creatures.remove(creature);
        creature.setResidence(null);
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
