package org.phantastikos.structures.hopital.services;

import org.phantastikos.entite.creatures.Creature;

public abstract class ServiceMedicalSpecifique extends ServiceMedical {
    public ServiceMedicalSpecifique(String nom, double superficie, int capaciteMax, Budget budget) {
        super(nom, superficie, capaciteMax, budget);
    }

    public abstract boolean accepterCreature(Creature creature);

    @Override
    public String ajouterCreature(Creature creature) {
        if (!accepterCreature(creature)) {
            return "La créature" + creature.getNom() + "ne correspond pas aux critères de ce service médical.";
        }
        return super.ajouterCreature(creature);

    }
}

