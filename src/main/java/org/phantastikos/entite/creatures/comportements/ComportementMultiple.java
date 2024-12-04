package org.phantastikos.entite.creatures.comportements;

import java.util.List;

public interface ComportementMultiple extends BaseComportements{
    List<BaseComportements> getComportements();

    @Override
    default boolean trepasser() {
        boolean result = false;
        for (BaseComportements comportement : getComportements()) {
            result |= comportement.trepasser();
        }
        return result;
    }
}



