package org.phantastikos.structures.hopital.services;

import org.phantastikos.entite.creatures.Creature;

public class CentreQuarantaine extends ServiceMedical {
    private boolean isolation;

    public CentreQuarantaine(String nom, double budget, boolean isolation) {
        super(nom, budget);
        this.isolation = isolation;
    }

    public boolean isIsolation() {
        return isolation;
    }

    public void setIsolation(boolean isolation) {
        this.isolation = isolation;
    }

    @Override
    public void ajouterCreature(Creature creature) {
        if (!creature.isContagieuse()) {
            throw new IllegalArgumentException("Seules les créatures contagieuses peuvent être ajoutées à un centre de quarantaine.");
        }
        super.ajouterCreature(creature);
    }

    public void reviserBudget(double budget) {
        // Révision du budget en tenant compte de l'isolation
        double budgetRevise = budget;
        if (isolation) {
            budgetRevise += 1000; // Exemple : coût fixe supplémentaire pour l'isolation
        }
        super.setBudget(budgetRevise);
    }

    @Override
    public String afficherDetails() {
        StringBuilder details = new StringBuilder(super.afficherDetails());
        details.append("Isolation : ").append(isolation ? "Activée" : "Désactivée").append("\n");
        return details.toString();
    }
}