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

    public void afficherDetails() {
        System.out.println("Service Médical: " + nom);
        System.out.println("Superficie: " + superficie + " m²");
        System.out.println("Capacité maximale: " + capaciteMax);
        System.out.println("Budget: " + budget);
        System.out.println("Nombre de créatures présentes: " + creatures.size());
        System.out.println("Liste des créatures:");
        for (Creature creature : creatures) {
            System.out.println(creature);
        }
    }
}
