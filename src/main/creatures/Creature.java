package main.creatures;

import main.maladies.Maladie;
import main.services.ServiceMedical;

import java.util.ArrayList;
import java.util.List;

public class Creature {
    private String nom;
    private char sexe;
    private double poids;
    private double taille;
    private int age;
    private int moral;
    private List<Maladie> maladies;
    private ServiceMedical residence;

    public ServiceMedical getResidence() {
        return residence;
    }

    public void setResidence(ServiceMedical residence) {
        this.residence = residence;
    }

    public Creature(String nom, char sexe, double poids, double taille, int age) {
        this.nom = nom;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.maladies = new ArrayList<>();
    }


    public String getNom() {
        return nom;
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
}