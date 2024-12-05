package org.phantastikos.structures.hopital.services;


import org.phantastikos.entite.creatures.Creature;

import java.util.*;

public class ServiceMedical {
    private String nom;
    private double superficie;
    private int capaciteMax;
    private int budget;
    private Budget catBudget;
    private List<Creature> creatures;

    public ServiceMedical(String nom, double superficie, int capaciteMax, Budget budget) {
        this.nom = nom;
        this.superficie = superficie;
        this.capaciteMax = capaciteMax;
        this.catBudget = budget;
        this.creatures = new ArrayList<>();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setCreatures(List<Creature> creatures) {
        this.creatures = creatures;
    }

    public String getNom() {
        return nom;
    }

    public Budget getCatBudget() {
        return catBudget;
    }

    public void setCatBudget(Budget catBudget) {
        this.catBudget = catBudget;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }
    public boolean accepterCreature(Creature creature) {
        if (creatures.isEmpty()) {
            return true;
        }

        Class<?> typePremiereCreature = creatures.getFirst().getClass();
        return typePremiereCreature.equals(creature.getClass());
    }

    public String ajouterCreature(Creature creature) {
        if (creatures.size() < capaciteMax) {
            creatures.add(creature);
            creature.setResidence(this);
            return "La creature" + creature.getNom() + " est maintenant dans le service " + creature.getResidence();
        } else {
            return "Impossible d'ajouter la créature"+ creature.getNom() +": capacité maximale atteinte !";
        }
    }

    public String enleverCreature(Creature creature) {
        creatures.remove(creature);
        creature.setResidence(null);
        return "la creature " + creature.getNom() + " n'est plus dans le service " + creature.getResidence();
    }

    public String reviserBudget() {
        if (creatures.size() >= capaciteMax-10) {
            budget -= 50;
            return "Le budget a été révisé";
        }
        return "Le budget n'a pas été révisé";
    }

    public String soignerCreatures(){
        Random aleatoire = new Random();
        StringBuilder log = new StringBuilder();
        for (Creature creature : creatures) {
             int nbrMaladies = creature.getMaladies().size();
             if (nbrMaladies > 0) {
                 log.append(creature.etreSoignee(creature.getMaladies().get(aleatoire.nextInt(nbrMaladies)))).append("\n");
             }
        }
        return log.toString();
    }

    public Map<String, String> recupererAttributs() {
        Map<String, String> attributs = new HashMap<>();
        attributs.put("nom", nom);
        attributs.put("superficie", ""+superficie);
        attributs.put("capaciteMax", ""+capaciteMax);
        attributs.put("budget", catBudget.toString());
        return attributs;
    }
}
