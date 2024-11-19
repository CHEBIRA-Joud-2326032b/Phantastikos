package main.creatures.categories;

import main.creatures.Creature;
import main.maladies.Maladie;

public interface ActionsCreature {
    default void attendre(){
        Creature creature = (Creature) this;
        creature.setMoral(creature.getMoral()-10);
    };
    void hurler();
    void sEmporter();
    void tomberMalade(Maladie maladie);
    boolean etreSoignee();
    boolean trepasser();
}
