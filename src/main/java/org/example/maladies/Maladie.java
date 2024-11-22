package org.example.maladies;

public class Maladie {
    // Attributs de la maladie
    private String nomComplet;
    private String nomAbrege;
    private int niveauActuel;
    private int niveauMaximum;

    // Constructeur
    public Maladie(String nomComplet, String nomAbrege, int niveauActuel, int niveauMaximum) {
        this.nomComplet = nomComplet;
        this.nomAbrege = nomAbrege;
        this.niveauActuel = Math.min(niveauActuel, niveauMaximum);
        this.niveauMaximum = niveauMaximum;
    }

    // Getters et setters
    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getNomAbrege() {
        return nomAbrege;
    }

    public void setNomAbrege(String nomAbrege) {
        this.nomAbrege = nomAbrege;
    }

    public int getNiveauActuel() {
        return niveauActuel;
    }

    public void setNiveauActuel(int niveauActuel) {
        this.niveauActuel = Math.min(niveauActuel, niveauMaximum); // Evite de dépasser le max
    }

    public int getNiveauMaximum() {
        return niveauMaximum;
    }

    public void setNiveauMaximum(int niveauMaximum) {
        this.niveauMaximum = niveauMaximum;
    }

    // Méthodes pour gérer le niveau de la maladie
    public void augmenterNiveau(int increment) {
        niveauActuel = Math.min(niveauActuel + increment, niveauMaximum);
    }

    public void diminuerNiveau(int decrement) {
        niveauActuel = Math.max(niveauActuel - decrement, 0);
    }

    public void changerNiveau(int nouveauNiveau) {
        niveauActuel = Math.min(Math.max(nouveauNiveau, 0), niveauMaximum);
    }

    // Détermine si le niveau est létal
    public boolean estLetal() {
        return niveauActuel >= niveauMaximum;
    }

    @Override
    public String toString() {
        return "Maladie{" +
                "nomComplet='" + nomComplet + '\'' +
                ", nomAbrege='" + nomAbrege + '\'' +
                ", niveauActuel=" + niveauActuel +
                ", niveauMaximum=" + niveauMaximum +
                '}';
    }
}

