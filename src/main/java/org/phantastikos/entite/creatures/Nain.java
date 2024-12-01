package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.Vip;

public class Nain extends Creature implements Vip {

    public Nain(String nom, char sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(95);
    }


}
