package org.phantastikos.entite.creatures.comportements;

import org.phantastikos.entite.creatures.Creature;
import org.phantastikos.entite.etats.maladies.Maladie;

import java.util.List;
import java.util.Random;

/**
 * Interface définissant les comportements de base pour les créatures.
 * Les comportements incluent des actions telles que attendre, hurler,
 * tomber malade, ou vieillir.
 */
public interface BaseComportements {

    /**
     * Réduit le moral de la créature lorsqu'elle attend.
     * Si le moral atteint 0 ou moins, la créature hurle.
     */
    default void attendre() {
        Creature creature = (Creature) this;
        if (creature.getMoral() <= 0) {
            hurler();
        } else {
            creature.setMoral(creature.getMoral() - 10);
        }
    }

    /**
     * Permet à la créature de hurler.
     * Augmente son compteur de hurlements et,
     * si le seuil est atteint, déclenche une colère.
     */
    default void hurler() {
        Creature creature = (Creature) this;
        if (creature.getCptHurlements() >= 3) {
            sEmporter();
        }
        creature.setCptHurlements(creature.getCptHurlements() + 1);
    }

    /**
     * Déclenche un emportement de la créature.
     * Il y a une probabilité (30%) que la créature trépassera.
     */
    default void sEmporter() {
        Creature creature = (Creature) this;
        Random rand = new Random();
        if (rand.nextInt(101) < 30) {
            trepasser();
        }
    }

    /**
     * Rend la créature malade en lui assignant une maladie spécifique.
     *
     * @param maladie la maladie à ajouter à la créature.
     */
    default void tomberMalade(Maladie maladie) {
        Creature creature = (Creature) this;
        creature.ajouterMaladie(maladie);
    }

    /**
     * Soigne une maladie spécifique chez la créature.
     * Si le soin échoue, le niveau de la maladie est réduit.
     * Le moral de la créature augmente dans tous les cas.
     *
     * @param maladie la maladie à soigner.
     */
    default void etreSoignee(Maladie maladie) {
        Creature creature = (Creature) this;
        Random malchance = new Random();
        if (creature.getChance() > malchance.nextInt(101)) {
            creature.enleverMaladie(maladie);
            creature.setMoral(creature.getMoral() + 40);
        } else {
            maladie.diminuerNiveau(maladie.getNiveauActuel() / 2);
            creature.setMoral(creature.getMoral() + 20);
        }
    }

    /**
     * Détermine si la créature trépassera ou non en fonction de sa chance.
     *
     * @return {@code true} si la créature trépassera, {@code false} sinon.
     */
    default boolean trepasser() {
        Creature creature = (Creature) this;
        Random malchance = new Random();
        if (creature.getChance() > malchance.nextInt(101)) {
            return false;
        }
        return true;
    }

    /**
     * Permet à la créature de contaminer une autre créature
     * avec une maladie qu'elle porte.
     */
    default void contaminer() {
        Creature creature = (Creature) this;
        Random malchance = new Random();
        List<Creature> autresCreatures = creature.getResidence().getCreatures()
                .stream()
                .filter(c -> !c.equals(creature))
                .toList();
        Creature cible = autresCreatures.get(malchance.nextInt(autresCreatures.size()));
        Maladie maladieChoisie = creature.getMaladies()
                .get(malchance.nextInt(creature.getMaladies().size()));
        if (cible.getMaladies().stream().noneMatch(m -> m.getType().equals(maladieChoisie.getType()))) {
            cible.ajouterMaladie(new Maladie(maladieChoisie.getType()));
        }
    }

    /**
     * Fait vieillir la créature en augmentant son âge.
     * L'augmentation est aléatoire, avec une probabilité
     * d'ajouter entre 2 et 4 années.
     */
    default void vieillir() {
        Creature creature = (Creature) this;
        Random aleatoire = new Random();
        int malchance = aleatoire.nextInt(101);
        if (malchance > 85) {
            creature.setAge(creature.getAge() + 4);
        } else if (malchance > 65) {
            creature.setAge(creature.getAge() + 3);
        } else {
            creature.setAge(creature.getAge() + 2);
        }
    }
}
