package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.Triage;

public class Orque extends Creature implements Triage {

    public Orque(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(60);
    }
    public boolean isContagieuse() {
        return true; 
    }
}
