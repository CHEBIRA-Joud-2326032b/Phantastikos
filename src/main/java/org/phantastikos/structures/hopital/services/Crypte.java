package org.phantastikos.structures.hopital.services;

import org.phantastikos.entite.creatures.Creature;

public class Crypte extends ServiceMedical {
    private int niveauVentilation;
    private double temperature;

    public Crypte(String nom, double budget, int niveauVentilation, double temperature) {
        super(nom, budget);
        this.niveauVentilation = niveauVentilation;
        this.temperature = temperature;
    }

    public int getNiveauVentilation() {
        return niveauVentilation;
    }

    public void setNiveauVentilation(int niveauVentilation) {
        this.niveauVentilation = niveauVentilation;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public void ajouterCreature(Creature creature) {
        if (!creature.isRegenerante()) {
            throw new IllegalArgumentException("Seules les créatures régénérantes peuvent être ajoutées à une crypte.");
        }
        super.ajouterCreature(creature);
    }


    public void reviserBudget(double budget) {
        // Révision du budget en fonction de la ventilation et de la température
        double budgetRevise = budget;
        if (niveauVentilation < 5) {
            budgetRevise -= 500; // Exemple : pénalité pour faible ventilation
        }
        if (temperature < 0 || temperature > 10) {
            budgetRevise += 1000; // Exemple : coût supplémentaire pour température inadéquate
        }
        setBudget(budgetRevise);
    }

    @Override
    public String afficherDetails() {
        StringBuilder details = new StringBuilder(super.afficherDetails());
        details.append("Niveau de ventilation : ").append(niveauVentilation).append("\n");
        details.append("Température : ").append(temperature).append("°C\n");
        return details.toString();
    }
}