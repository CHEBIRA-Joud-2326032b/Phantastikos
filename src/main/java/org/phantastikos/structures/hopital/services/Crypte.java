package org.phantastikos.structures.hopital.services;

import org.phantastikos.entite.creatures.Creature;

import java.util.HashMap;
import java.util.Map;

public class Crypte extends ServiceMedical {
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
    public String reviserBudget() {
        int cpt = 0;
        if (getCreatures().size() >= getCapaciteMax()-10) {
            setBudget(getBudget()-50);
            cpt++;
        }
        if(niveauVentilation <= 2) {
            setBudget(getBudget()-25);
            cpt ++;
        }
        if (temperature > 20.0) {
            setBudget(getBudget()-25);
            cpt ++;
        }
        setCatBudget(Budget.CategoriserBudget(getBudget()));
        return "Le budget a été révisé " + cpt + " fois";
    }

    @Override
    public Map<String, String> recupererAttributs(){
        Map<String, String> attributs = new HashMap<>();
        attributs.put("nom", getNom());
        attributs.put("superficie", ""+getSuperficie());
        attributs.put("capaciteMax", ""+getCapaciteMax());
        attributs.put("budget", getCatBudget().toString());
        attributs.put("niveauVentilation", ""+getNiveauVentilation());
        attributs.put("temperature", ""+getTemperature());
        return attributs;
    }
}