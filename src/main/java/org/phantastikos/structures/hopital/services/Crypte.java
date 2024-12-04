package org.phantastikos.structures.hopital.services;

import org.phantastikos.entite.creatures.Creature;

public class Crypte extends ServiceMedicalSpecifique {
    private int niveauVentilation;
    private double temperature;

    public Crypte(String nom, double superficie, int capaciteMax, Budget budget, int niveauVentilation, double temperature) {
        super(nom, superficie, capaciteMax, budget);
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
    public boolean accepterCreature(Creature creature) {
        return creature.isRegenerante();
    }

    @Override
    public void reviserBudget() {
        if (getCreatures().size() >= getCapaciteMax()-10) {
            setBudget(getBudget()-50);
        }
        if(niveauVentilation <= 2) {
            setBudget(getBudget()-25);
        }
        if (temperature > 20.0) {
            setBudget(getBudget()-25);
        }
        setCatBudget(Budget.CategoriserBudget(getBudget()));
    }
    @Override
    public String afficherDetails() {
        StringBuilder details = new StringBuilder(super.afficherDetails());
        details.append("Niveau de ventilation : ").append(niveauVentilation).append("\n");
        details.append("Température : ").append(temperature).append("°C\n");
        return details.toString();
    }
}