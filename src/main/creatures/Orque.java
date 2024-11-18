package main.creatures;

import main.creatures.categories.ActionsCreature;
import main.maladies.Maladie;

public class Orque extends Creature implements ActionsCreature {

    public Orque(String nom, char sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
        setMoral(60);
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
