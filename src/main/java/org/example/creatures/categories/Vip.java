package org.example.creatures.categories;

import org.example.creatures.Creature;

public interface Vip extends ActionsCreature {

    @Override
    default void attendre() {
        Creature creature = (Creature) this;
        creature.setNbrAttente(creature.getNbrAttente() + 1);
        creature.setMoral(creature.getMoral() - (10 * creature.getNbrAttente()));
    }
}
