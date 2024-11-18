package main.creatures.categories;

import main.maladies.Maladie;

public interface ActionsCreature {
    default void attendre(){
        int prout;
    };
    void hurler();
    void sEmporter();
    void tomberMalade(Maladie maladie);
    boolean etreSoignee();
    boolean trepasser();
}
