package org.phantastikos.entite.creatures.comportements;

import org.phantastikos.entite.creatures.Creature;

import java.util.List;

/**
 * Interface représentant le comportement de triage pour une créature.
 * Cette interface hérite des comportements de base définis dans {@link BaseComportements}.
 * La méthode {@code attendre} est surchargée pour prendre en compte
 * des interactions sociales entre les créatures d'une même classe.
 */
public interface Triage extends BaseComportements {

    /**
     * Diminue le moral de la créature lorsqu'elle attend.
     * Si son moral atteint 0 ou moins, elle hurle.
     * Le degré de diminution du moral dépend de la présence d'autres
     * créatures de la même classe dans la résidence :
     * - Si une solidarité est détectée (au moins une autre créature de la même classe),
     *   la diminution du moral est moins importante (-5).
     * - Sinon, la diminution est plus importante (-10).
     */
    @Override
    default void attendre() {
        Creature creature = (Creature) this;
        if (creature.getMoral() <= 0) {
            hurler();
        } else {
            boolean solidarite = false;

            // Récupère la liste des autres créatures dans la résidence.
            List<Creature> autresCreatures = creature.getResidence().getCreatures()
                    .stream()
                    .filter(c -> !c.equals(creature))
                    .toList();

            // Vérifie si au moins une autre créature est de la même classe.
            for (Creature c : autresCreatures) {
                if (c.getClass().equals(creature.getClass())) {
                    solidarite = true;
                    break;
                }
            }

            // Diminue le moral en fonction de la solidarité.
            if (solidarite) {
                creature.setMoral(creature.getMoral() - 5);
            } else {
                creature.setMoral(creature.getMoral() - 10);
            }
        }
    }
}
