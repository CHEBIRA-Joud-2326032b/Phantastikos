package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.BaseComportements;
import org.phantastikos.entite.etats.maladies.Maladie;
import org.phantastikos.entite.etats.maladies.TypeMaladie;
import org.phantastikos.structures.hopital.services.ServiceMedical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Creature implements BaseComportements {
    private String nom;
    private char sexe;
    private int poids;
    private int taille;
    private int age;
    private int moral;
    private int chance;
    private List<Maladie> maladies;
    private ServiceMedical residence;
    private int cptHurlements;
    private int nbrAttente;



    public Creature(String nom, char sexe, int poids, int taille, int age) {
        this.nom = nom;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.maladies = new ArrayList<>();
        this.cptHurlements = 0;
        this.moral = 3;
        this.nbrAttente = 0;
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

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
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

    public boolean possedeMaladie(Maladie maladie) {
        return this.maladies.contains(maladie);
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
        maladie.changerNiveau(1);
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

    public Map<String, String> recupererAttributs(){
        Map<String, String> attributs = new HashMap<>();

        attributs.put("nom", nom);
        attributs.put("sexe", ""+sexe);
        attributs.put("poids", ""+poids);
        attributs.put("taille", ""+taille);
        attributs.put("age", ""+age);
        attributs.put("moral", ""+moral);
        attributs.put("chance", ""+chance);
        attributs.put("maladies", maladies.toString());
        attributs.put("residence", residence.getNom());
        attributs.put("cptHurlements", ""+cptHurlements);
        attributs.put("nbrAttente", ""+nbrAttente);
        return attributs;
    }
}