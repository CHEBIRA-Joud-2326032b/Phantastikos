package org.phantastikos.entite.creatures.comportements;

import org.phantastikos.entite.creatures.Creature;

import java.util.Random;

public interface Regenerant extends BaseComportements {

    @Override
    default boolean trepasser(){
        return false;
    }
}
