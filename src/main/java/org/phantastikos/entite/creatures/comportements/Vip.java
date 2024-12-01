package org.phantastikos.entite.creatures.comportements;

import org.phantastikos.entite.creatures.Creature;

public interface Vip extends BaseComportements {

    @Override
    default void attendre() {
        Creature creature = (Creature) this;
        creature.setNbrAttente(creature.getNbrAttente() + 1);
        creature.setMoral(creature.getMoral() - (10 * creature.getNbrAttente()));
    }
}
