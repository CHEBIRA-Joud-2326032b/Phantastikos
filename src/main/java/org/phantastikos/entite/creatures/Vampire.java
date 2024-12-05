package org.phantastikos.entite.creatures;


import org.phantastikos.entite.creatures.comportements.*;

import java.util.List;
import java.util.Random;

public class Vampire extends Creature implements Vip, ComportementMultiple {
    private final List<BaseComportements> comportements;
    public Vampire(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);
        this.comportements = List.of(new Contaminant() {
        }, new Demoralisant() {
        }, new Regenerant() {
        });
        setMoral(100);
    }

    @Override
    public List<BaseComportements> getComportements() {
        return comportements;
    }

    @Override
    public boolean trepasser() {
        Random malchance = new Random();
        if (this.getChance() > malchance.nextInt(101)) {
            return false;
        }
        return ComportementMultiple.super.trepasser();
    }

    @Override
    public boolean isContagieuse() {
        return super.isContagieuse();
    }
    @Override
    public boolean isRegenerante(){
        return super.isRegenerante();
    }
}
