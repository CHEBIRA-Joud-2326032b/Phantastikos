package org.example.creatures;

import org.example.creatures.categories.ActionsCreature;
import org.example.creatures.categories.Vip;
import org.example.maladies.Maladie;

public class Nain extends Creature implements Vip {

    public Nain(String nom, char sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(95);
    }


}
