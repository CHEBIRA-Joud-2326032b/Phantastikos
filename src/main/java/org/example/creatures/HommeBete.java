package org.example.creatures;

import org.example.creatures.categories.ActionsCreature;
import org.example.creatures.categories.Triage;
import org.example.maladies.Maladie;

public class HommeBete extends Creature implements Triage {

    public HommeBete(String nom, char sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(80);
    }
    public boolean isContagieuse() {
        return true;
    }

}
