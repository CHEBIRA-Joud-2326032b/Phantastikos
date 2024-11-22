package org.example.creatures;

import org.example.creatures.categories.ActionsCreature;
import org.example.maladies.Maladie;

public class HommeBete extends Creature implements ActionsCreature {

    public HommeBete(String nom, char sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(80);
    }


    @Override
    public void hurler() {

    }

    @Override
    public void sEmporter() {

    }

    @Override
    public void tomberMalade(Maladie maladie) {

    }

    @Override
    public boolean etreSoignee() {
        return false;
    }

    @Override
    public boolean trepasser() {
        return false;
    }
}
