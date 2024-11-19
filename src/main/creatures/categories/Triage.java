package main.creatures.categories;

import main.creatures.Creature;

public interface Triage extends ActionsCreature{

    @Override
    default void attendre() {
        Creature creature = (Creature) this;
        for (Creature c : creature.getResidence().getCreatures()){
            boolean solidarite = c.getClass().equals(creature.getClass());
        }
    }
}
