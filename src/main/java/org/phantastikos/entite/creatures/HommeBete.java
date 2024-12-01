package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.Triage;

public class HommeBete extends Creature implements Triage {

    public HommeBete(String nom, char sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(80);
    }
    public boolean isContagieuse() {
        return true;
    }

}
