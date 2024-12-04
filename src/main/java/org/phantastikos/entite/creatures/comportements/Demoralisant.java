package org.phantastikos.entite.creatures.comportements;

import org.phantastikos.entite.creatures.Creature;

import java.util.Random;

public interface Demoralisant extends BaseComportements {


    @Override
    default boolean trepasser(){
        Creature creature = (Creature) this;
        Random malchance = new Random();
        if (creature.getChance() > malchance.nextInt(101)){
            return false;
        }
        for (Creature c : creature.getResidence().getCreatures()){
            if (50 > malchance.nextInt(101)){
                c.setMoral(c.getMoral() - 15);
            }
        }
        return true;
    }
}
