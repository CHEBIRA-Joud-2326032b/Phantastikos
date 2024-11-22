package org.example.creatures.categories;


import org.example.creatures.Creature;
import org.example.maladies.Maladie;

public interface ActionsCreature {
    default void attendre(){
        Creature creature = (Creature) this;
        creature.setMoral(creature.getMoral()-10);
    }

    default void hurler(){}

    default void sEmporter(){}

    default void tomberMalade(Maladie maladie){}

    default boolean etreSoignee(){
        return true;
    }

    default boolean trepasser(){
        return true;
    }
}
