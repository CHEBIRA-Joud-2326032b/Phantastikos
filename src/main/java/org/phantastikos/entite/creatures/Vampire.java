package org.phantastikos.entite.creatures;


import org.phantastikos.entite.creatures.comportements.Contaminant;
import org.phantastikos.entite.creatures.comportements.Demoralisant;
import org.phantastikos.entite.creatures.comportements.Regenerant;
import org.phantastikos.entite.creatures.comportements.Vip;

public class Vampire extends Creature implements Vip, Demoralisant, Contaminant, Regenerant {

    public Vampire(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(100);
    }


}
