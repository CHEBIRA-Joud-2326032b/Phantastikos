package org.phantastikos.entite.creatures;


import org.phantastikos.entite.creatures.comportements.Triage;

public class Zombie extends Creature implements Triage {

    public Zombie(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(10);
    }
    public boolean isRegenerante() {
        return true;
    }

}
