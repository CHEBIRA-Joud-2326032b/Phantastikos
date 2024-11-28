package org.example.creatures;


import org.example.creatures.categories.ActionsCreature;
import org.example.creatures.categories.Triage;
import org.example.maladies.Maladie;

public class Lycanthrope extends Creature implements Triage {

    public Lycanthrope(String nom, char sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(80);
    }


}
