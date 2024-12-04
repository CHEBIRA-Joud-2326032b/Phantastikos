package org.phantastikos.entite.creatures.comportements;

import org.phantastikos.entite.creatures.Creature;

/**
 * Interface représentant un comportement spécifique pour une créature VIP.
 * Une créature VIP devient plus impatiente au fil du temps, avec un moral
 * qui diminue de manière exponentielle en fonction du nombre de fois
 * qu'elle attend.
 * <p>
 * Cette interface hérite des comportements de base définis dans {@link BaseComportements}.
 */
public interface Vip extends BaseComportements {

    /**
     * Diminue le moral de la créature en fonction du nombre d'attentes successives.
     * À chaque appel, le nombre d'attentes est incrémenté, et la diminution du moral
     * devient de plus en plus sévère (diminution proportionnelle à 10 fois le nombre d'attentes).
     */
    @Override
    default void attendre() {
        Creature creature = (Creature) this;

        // Incrémente le compteur d'attentes.
        creature.setNbrAttente(creature.getNbrAttente() + 1);

        // Diminue le moral en fonction du nombre d'attentes.
        creature.setMoral(creature.getMoral() - (10 * creature.getNbrAttente()));
    }
}
