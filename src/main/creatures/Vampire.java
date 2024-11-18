package main.creatures;

import main.creatures.categories.Contaminant;
import main.creatures.categories.Demoralisant;
import main.creatures.categories.Regenerant;
import main.creatures.categories.Vip;
import main.maladies.Maladie;

public class Vampire extends Creature implements Vip, Demoralisant, Contaminant, Regenerant {

    public Vampire(String nom, char sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(100);
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
