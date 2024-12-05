package org.phantastikos.structures.hopital.services;

import org.phantastikos.entite.creatures.Creature;

import java.util.HashMap;
import java.util.Map;

public class CentreQuarantaine extends ServiceMedical {
    private boolean isolation;

    public CentreQuarantaine(String nom, double superficie, int capaciteMax, Budget budget,boolean isolation) {
        super(nom, superficie, capaciteMax, budget);
        this.isolation = isolation;
    }

    public boolean isIsolation() {
        return isolation;
    }

    public void setIsolation(boolean isolation) {
        this.isolation = isolation;
    }

    @Override
    public boolean accepterCreature(Creature creature) {
        return creature.isContagieuse();
    }


    @Override
    public String reviserBudget(){
        int cpt = 0;
        if (getCreatures().size() >= getCapaciteMax()-10) {
            setBudget(getBudget()-50);
            cpt++;
        }
        if (!isolation) {
            setBudget(getBudget()-50);
            cpt++;
        }
        return "Le budget a été révisé " + cpt + " fois";
    }
    @Override
    public Map<String, String> recupererAttributs() {
        Map<String, String> attributs = new HashMap<>();
        attributs.put("nom", getNom());
        attributs.put("superficie", "" + getSuperficie());
        attributs.put("capaciteMax", "" + getCapaciteMax());
        attributs.put("bduget", getCatBudget().toString());
        attributs.put("isolation", "" + isolation);
        return attributs;
    }
}