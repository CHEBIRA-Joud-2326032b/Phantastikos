package org.example.creatures;


import org.example.creatures.categories.Contaminant;
import org.example.creatures.categories.Demoralisant;
import org.example.creatures.categories.Regenerant;
import org.example.creatures.categories.Vip;
import org.example.maladies.Maladie;

public class Vampire extends Creature implements Vip, Demoralisant, Contaminant, Regenerant {

    public Vampire(String nom, char sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(100);
    }


}