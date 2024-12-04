package org.phantastikos.structures.hopital.services;

import org.phantastikos.entite.creatures.Creature;

public class CentreQuarantaine extends ServiceMedicalSpecifique {
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
    public String afficherDetails() {
        StringBuilder details = new StringBuilder(super.afficherDetails());
        details.append("Isolation : ").append(isolation ? "Activée" : "Désactivée").append("\n");
        return details.toString();
    }
}