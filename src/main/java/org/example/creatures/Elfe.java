package org.example.creatures;


import org.example.creatures.categories.Vip;
import org.example.maladies.Maladie;

public class Elfe extends Creature implements Vip {

    public Elfe(String nom, char sexe, double poids, double taille, int age) {
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
