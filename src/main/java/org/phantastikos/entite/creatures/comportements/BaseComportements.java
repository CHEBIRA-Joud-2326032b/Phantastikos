package org.phantastikos.entite.creatures.comportements;


import org.phantastikos.entite.creatures.Creature;
import org.phantastikos.entite.etats.maladies.Maladie;

import java.util.List;
import java.util.Random;

public interface BaseComportements {

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

    default String etreSoignee(Maladie maladie) {
        Creature creature = (Creature) this;
        Random malchance = new Random();
        if (creature.getChance() >malchance.nextInt(101)) {
            creature.enleverMaladie(maladie);
            creature.setMoral(creature.getMoral() + 40);
            return creature.getNom() + " n'a plus la maladie" + maladie.getNomAbrege() + " et a gagné 40 de moral : " + creature.getMoral();
        }
        else {
            maladie.diminuerNiveau(maladie.getNiveauActuel()/2);
            if (maladie.getNiveauActuel() <= 0){
                creature.enleverMaladie(maladie);
            }
            creature.setMoral(creature.getMoral() + 20);
            return "le niveau de la maladie " + maladie.getNomAbrege() + " de " + creature.getNom() + "a baissé => " + maladie.getNiveauActuel() + " et son moral a augmenté de 20 : " + creature.getMoral();
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

    default void vieillir(){
        Creature creature = (Creature) this;
        Random aleatoire = new Random();
        int malchance = aleatoire.nextInt(101);
        if (malchance > 85){
            creature.setAge(creature.getAge() + 4);
            creature.setTaille(creature.getTaille()+8);
            creature.setPoids(creature.getPoids()+8);
        } else if (malchance > 65) {
            creature.setAge(creature.getAge() + 3);
            creature.setTaille(creature.getTaille()+6);
            creature.setPoids(creature.getPoids()+5);
        }
        else {
            creature.setAge(creature.getAge() + 2);
            creature.setTaille(creature.getTaille()+5);
            creature.setPoids(creature.getPoids()+4);
        }
    }
}
