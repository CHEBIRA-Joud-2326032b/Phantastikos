package org.phantastikos.entite.creatures.comportements;

import org.phantastikos.entite.creatures.Creature;

import java.util.Random;

public interface Contaminant extends BaseComportements {

    @Override
    default boolean trepasser(){
        Creature creature = (Creature) this;
        Random malchance = new Random();
        if (creature.getChance() > malchance.nextInt(101)){
            return false;
        }
        creature.getResidence().getCreatures().get(malchance.nextInt(creature.getResidence().getCreatures().size())).ajouterMaladie(creature.getMaladies().get(malchance.nextInt(creature.getMaladies().size())));
        return true;
    }
}
