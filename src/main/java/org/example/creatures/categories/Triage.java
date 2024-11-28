package org.example.creatures.categories;


import org.example.creatures.Creature;

import java.util.List;

public interface Triage extends ActionsCreature{

    @Override
    default void attendre() {
        Creature creature = (Creature) this;
        if (creature.getMoral() <= 0) {
            hurler();
        }
        else {
            boolean solidarite = false;
            List<Creature> autresCreatures = creature.getResidence().getCreatures().stream().filter(c -> !c.equals(creature)).toList();
            for (Creature c : autresCreatures) {
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
}