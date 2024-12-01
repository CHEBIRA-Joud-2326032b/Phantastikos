package org.example.creatures;


import org.example.creatures.categories.ActionsCreature;
import org.example.creatures.categories.Triage;
import org.example.maladies.Maladie;

public class Zombie extends Creature implements Triage {

    public Zombie(String nom, char sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(10);
    }
    public boolean isRegenerante() {
        return true;
    }

}
