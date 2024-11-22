package org.example.services;


import org.example.creatures.Creature;

import java.util.ArrayList;
import java.util.List;

public class ServiceMedical {
    private final String nom;
    private double budget;
    private final List<Creature> creatures;

    public ServiceMedical(String nom, double budget) {
        this.nom = nom;
        this.budget = budget;
        this.creatures = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }

    public void afficherDetails() {
        System.out.println("Service Médical : " + nom);
        System.out.println("Budget : " + budget);
        System.out.println("Liste des créatures : ");
        for (Creature creature : creatures) {
            System.out.println("- " + creature.getNom());
        }
    }
}
