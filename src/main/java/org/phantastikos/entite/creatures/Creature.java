package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.BaseComportements;
import org.phantastikos.entite.etats.maladies.Maladie;
import org.phantastikos.structures.hopital.services.ServiceMedical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe représentant une créature avec divers attributs physiques et mentaux.
 * Une créature peut être affectée par des maladies, interagir avec son environnement
 * et effectuer des comportements définis dans {@link BaseComportements}.
 */
public class Creature implements BaseComportements {

    /** Nom de la créature. */
    private String nom;

    /** Sexe de la créature ('M' pour masculin, 'F' pour féminin, etc.). */
    private char sexe;

    /** Poids de la créature en kilogrammes. */
    private int poids;

    /** Taille de la créature en centimètres. */
    private int taille;

    /** Âge de la créature en années. */
    private int age;

    /** Niveau de moral de la créature (plus il est élevé, mieux elle se porte mentalement). */
    private int moral;

    /** Chance de la créature pour certains événements aléatoires. */
    private int chance;

    /** Liste des maladies dont la créature est atteinte. */
    private List<Maladie> maladies;

    /** Résidence actuelle de la créature (ex. : service médical). */
    private ServiceMedical residence;

    /** Compteur du nombre de hurlements émis par la créature. */
    private int cptHurlements;

    /** Nombre de fois où la créature a attendu sans agir. */
    private int nbrAttente;

    /**
     * Constructeur de la classe `Creature`.
     *
     * @param nom   le nom de la créature.
     * @param sexe  le sexe de la créature.
     * @param poids le poids de la créature (en kilogrammes).
     * @param taille la taille de la créature (en centimètres).
     * @param age   l'âge de la créature (en années).
     */
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

    // -------------------- Getters et Setters --------------------

    /**
     * Retourne le nom de la créature.
     *
     * @return le nom de la créature.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la créature.
     *
     * @param nom le nouveau nom de la créature.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le sexe de la créature.
     *
     * @return le sexe de la créature.
     */
    public char getSexe() {
        return sexe;
    }

    /**
     * Définit le sexe de la créature.
     *
     * @param sexe le nouveau sexe de la créature.
     */
    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    /**
     * Retourne le poids de la créature.
     *
     * @return le poids de la créature en kilogrammes.
     */
    public double getPoids() {
        return poids;
    }

    /**
     * Définit le poids de la créature.
     *
     * @param poids le nouveau poids de la créature en kilogrammes.
     */
    public void setPoids(int poids) {
        this.poids = poids;
    }

    /**
     * Retourne la taille de la créature.
     *
     * @return la taille de la créature en centimètres.
     */
    public double getTaille() {
        return taille;
    }

    /**
     * Définit la taille de la créature.
     *
     * @param taille la nouvelle taille de la créature en centimètres.
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }

    /**
     * Retourne l'âge de la créature.
     *
     * @return l'âge de la créature en années.
     */
    public int getAge() {
        return age;
    }

    /**
     * Définit l'âge de la créature.
     *
     * @param age le nouvel âge de la créature en années.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Retourne le moral de la créature.
     *
     * @return le moral de la créature.
     */
    public int getMoral() {
        return moral;
    }

    /**
     * Définit le moral de la créature.
     *
     * @param moral le nouveau moral de la créature.
     */
    public void setMoral(int moral) {
        this.moral = moral;
    }

    /**
     * Retourne la liste des maladies dont la créature est atteinte.
     *
     * @return une liste de maladies.
     */
    public List<Maladie> getMaladies() {
        return maladies;
    }

    /**
     * Définit la liste des maladies de la créature.
     *
     * @param maladies la nouvelle liste de maladies.
     */
    public void setMaladies(List<Maladie> maladies) {
        this.maladies = maladies;
    }

    /**
     * Vérifie si la créature possède une maladie donnée.
     *
     * @param maladie la maladie à vérifier.
     * @return {@code true} si la maladie est présente, {@code false} sinon.
     */
    public boolean possedeMaladie(Maladie maladie) {
        return this.maladies.contains(maladie);
    }

    /**
     * Retourne la résidence actuelle de la créature.
     *
     * @return la résidence de la créature.
     */
    public ServiceMedical getResidence() {
        return residence;
    }

    /**
     * Définit la résidence actuelle de la créature.
     *
     * @param residence la nouvelle résidence de la créature.
     */
    public void setResidence(ServiceMedical residence) {
        this.residence = residence;
    }

    /**
     * Retourne la chance de la créature.
     *
     * @return la chance de la créature.
     */
    public int getChance() {
        return chance;
    }

    /**
     * Définit la chance de la créature.
     *
     * @param chance la nouvelle chance de la créature.
     */
    public void setChance(int chance) {
        this.chance = chance;
    }

    /**
     * Ajoute une maladie à la liste des maladies de la créature.
     *
     * @param maladie la maladie à ajouter.
     */
    public void ajouterMaladie(Maladie maladie) {
        this.maladies.add(maladie);
    }

    /**
     * Retire une maladie de la liste des maladies de la créature.
     *
     * @param maladie la maladie à retirer.
     */
    public void enleverMaladie(Maladie maladie) {
        this.maladies.remove(maladie);
    }

    /**
     * Retourne le compteur de hurlements de la créature.
     *
     * @return le compteur de hurlements.
     */
    public int getCptHurlements() {
        return cptHurlements;
    }

    /**
     * Définit le compteur de hurlements de la créature.
     *
     * @param cptHurlements le nouveau compteur de hurlements.
     */
    public void setCptHurlements(int cptHurlements) {
        this.cptHurlements = cptHurlements;
    }

    /**
     * Retourne le nombre d'attentes de la créature.
     *
     * @return le nombre d'attentes.
     */
    public int getNbrAttente() {
        return nbrAttente;
    }

    /**
     * Définit le nombre d'attentes de la créature.
     *
     * @param nbrAttente le nouveau nombre d'attentes.
     */
    public void setNbrAttente(int nbrAttente) {
        this.nbrAttente = nbrAttente;
    }

    /**
     * Vérifie si la créature est régénérante.
     *
     * @return {@code true} si la créature est régénérante, sinon {@code false}.
     */
    public boolean isRegenerante() {
        return false;
    }

    /**
     * Vérifie si la créature est contagieuse.
     *
     * @return {@code true} si la créature est contagieuse, sinon {@code false}.
     */
    public boolean isContagieuse() {
        return false;
    }

    /**
     * Retourne un dictionnaire contenant les attributs de la créature sous forme de chaîne de caractères.
     *
     * @return un map des attributs de la créature.
     */
    public Map<String, String> recupererAttributs() {
        Map<String, String> attributs = new HashMap<>();

        attributs.put("nom", nom);
        attributs.put("sexe", "" + sexe);
        attributs.put("poids", "" + poids);
        attributs.put("taille", "" + taille);
        attributs.put("age", "" + age);
        attributs.put("moral", "" + moral);
        attributs.put("chance", "" + chance);
        attributs.put("maladies", maladies.toString());
        attributs.put("residence", residence != null ? residence.getNom() : "Aucune");
        attributs.put("cptHurlements", "" + cptHurlements);
        attributs.put("nbrAttente", "" + nbrAttente);
        return attributs;
    }
}
