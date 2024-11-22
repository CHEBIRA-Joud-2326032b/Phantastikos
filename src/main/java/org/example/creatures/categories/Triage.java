package org.example.creatures.categories;


import org.example.creatures.Creature;

public interface Triage extends ActionsCreature{

    @Override
    default void attendre() {
        Creature creature = (Creature) this;
        boolean solidarite = false;
        for (Creature c : creature.getResidence().getCreatures()) {
            solidarite = c.getClass().equals(creature.getClass());
        }
        if (solidarite) {
            creature.setMoral(creature.getMoral()-5);
        }
        else {
            creature.setMoral(creature.getMoral()-10);
        }
    }
}
