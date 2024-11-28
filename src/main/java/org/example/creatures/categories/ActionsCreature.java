package org.example.creatures.categories;


import org.example.creatures.Creature;
import org.example.maladies.Maladie;

import java.util.List;
import java.util.Random;

public interface ActionsCreature {

    default void attendre() {
        Creature creature = (Creature) this;
        if (creature.getMoral() <= 0) {
            hurler();
        }
        else {
            creature.setMoral(creature.getMoral() - 10);
        }

    }

    default void hurler() {
        Creature creature = (Creature) this;
        if (creature.getCptHurlements() >=3){
            sEmporter();
        }
        creature.setCptHurlements(creature.getCptHurlements() + 1);

    }

    default void sEmporter() {
        Creature creature = (Creature) this;
        Random rand = new Random();
        if (rand.nextInt(101) < 30){
            trepasser();
        }
    }

    default void tomberMalade(Maladie maladie) {
        Creature creature = (Creature) this;
        creature.ajouterMaladie(maladie);
    }

    default void etreSoignee(Maladie maladie) {
        Creature creature = (Creature) this;
        Random malchance = new Random();
        if (creature.getChance() >malchance.nextInt(101)) {
            creature.enleverMaladie(maladie);
            creature.setMoral(creature.getMoral() + 40);
        }
        else {
            maladie.diminuerNiveau(maladie.getNiveauActuel()/2);
            creature.setMoral(creature.getMoral() + 20);
        }

    }

    default boolean trepasser() {
        Creature creature = (Creature) this;
        Random malchance = new Random();
        if (creature.getChance() > malchance.nextInt(101)){
            return false;
        }
        return true;
    }

    default void contaminer() {
        Creature creature = (Creature) this;
        Random malchance = new Random();
        List<Creature> autresCreatures = creature.getResidence().getCreatures().stream().filter(c -> !c.equals(creature)).toList();
        Creature cible = autresCreatures.get(malchance.nextInt(autresCreatures.size()));
        Maladie maladieChoisie = creature.getMaladies().get(malchance.nextInt(creature.getMaladies().size()));
        if (cible.getMaladies().stream().noneMatch((m -> m.getType().equals(maladieChoisie.getType())))){
            cible.ajouterMaladie(new Maladie(maladieChoisie.getType()));
        }
    }
}