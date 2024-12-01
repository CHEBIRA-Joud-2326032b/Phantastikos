package org.example.creatures;

import org.example.creatures.categories.ActionsCreature;
import org.example.maladies.Maladie;
import org.example.services.ServiceMedical;

import java.util.ArrayList;
import java.util.List;

public class Creature implements ActionsCreature {
    private String nom;
    private char sexe;
    private double poids;
    private double taille;
    private int age;
    private int moral;
    private int chance;
    private List<Maladie> maladies;
    private ServiceMedical residence;
    private int cptHurlements;
    private int nbrAttente;



    public Creature(String nom, char sexe, double poids, double taille, int age) {
        this.nom = nom;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.maladies = new ArrayList<>();
        this.cptHurlements = 0;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }

    public List<Maladie> getMaladies() {
        return maladies;
    }

    public void setMaladies(List<Maladie> maladies) {
        this.maladies = maladies;
    }

    public ServiceMedical getResidence() {
        return residence;
    }

    public void setResidence(ServiceMedical residence) {
        this.residence = residence;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }
    public int getChance() {
        return chance;
    }

    public void ajouterMaladie(Maladie maladie) {
        this.maladies.add(maladie);
    }

    public void enleverMaladie(Maladie maladie) {
        this.maladies.remove(maladie);
    }

    public int getCptHurlements() {
        return cptHurlements;
    }

    public void setCptHurlements(int cptHurlements) {
        this.cptHurlements = cptHurlements;
    }

    public int getNbrAttente() {
        return nbrAttente;
    }

    public void setNbrAttente(int nbrAttente) {
        this.nbrAttente = nbrAttente;
    }

    public boolean isRegenerante() {
        return false;
    }

    public boolean isContagieuse() {
        return false;
    }
}