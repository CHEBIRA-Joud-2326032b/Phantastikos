package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.Vip;

public class Reptilien extends Creature implements Vip {

    public Reptilien(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(80);
    }


}
