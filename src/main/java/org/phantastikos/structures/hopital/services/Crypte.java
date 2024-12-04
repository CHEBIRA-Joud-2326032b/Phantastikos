package org.phantastikos.structures.hopital.services;

import org.phantastikos.entite.creatures.Creature;

/**
 * Représente une crypte dans un hôpital, un type de service médical destiné à accueillir les créatures régénérantes.
 * Ce service médical possède des caractéristiques spécifiques telles que le niveau de ventilation et la température.
 *
 * Les créatures ajoutées à ce service doivent être régénérantes pour en bénéficier.
 */
public class Crypte extends ServiceMedical {

    /**
     * Niveau de ventilation dans la crypte, qui peut influencer le confort et la récupération des créatures.
     */
    private int niveauVentilation;

    /**
     * Température actuelle dans la crypte, exprimée en degrés Celsius.
     */
    private double temperature;

    /**
     * Constructeur pour créer une crypte avec un nom, une superficie, une capacité maximale, un budget, un niveau de ventilation et une température.
     *
     * @param nom Le nom de la crypte.
     * @param superficie La superficie de la crypte.
     * @param capaciteMax La capacité maximale d'accueil de la crypte.
     * @param budget Le budget alloué à la crypte.
     * @param niveauVentilation Le niveau de ventilation dans la crypte (sur une échelle donnée).
     * @param temperature La température dans la crypte en degrés Celsius.
     */
    public Crypte(String nom, double superficie, int capaciteMax, Budget budget, int niveauVentilation, double temperature) {
        super(nom, superficie, capaciteMax, budget);
        this.niveauVentilation = niveauVentilation;
        this.temperature = temperature;
    }

    /**
     * Retourne le niveau de ventilation de la crypte.
     *
     * @return Le niveau de ventilation dans la crypte.
     */
    public int getNiveauVentilation() {
        return niveauVentilation;
    }

    /**
     * Modifie le niveau de ventilation de la crypte.
     *
     * @param niveauVentilation Le nouveau niveau de ventilation.
     */
    public void setNiveauVentilation(int niveauVentilation) {
        this.niveauVentilation = niveauVentilation;
    }

    /**
     * Retourne la température actuelle de la crypte.
     *
     * @return La température de la crypte en degrés Celsius.
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Modifie la température de la crypte.
     *
     * @param temperature La nouvelle température à définir pour la crypte.
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Ajoute une créature à la crypte. Seules les créatures régénérantes peuvent être ajoutées.
     *
     * @param creature La créature à ajouter.
     * @throws IllegalArgumentException Si la créature n'est pas régénérante, une exception est lancée.
     */
    @Override
    public void ajouterCreature(Creature creature) {
        if (!creature.isRegenerante()) {
            throw new IllegalArgumentException("Seules les créatures régénérantes peuvent être ajoutées à une crypte.");
        }
        super.ajouterCreature(creature);
    }

    /**
     * Affiche les détails de la crypte, y compris le niveau de ventilation et la température.
     *
     * @return Une chaîne de caractères contenant les détails de la crypte.
     */
    @Override
    public String afficherDetails() {
        StringBuilder details = new StringBuilder(super.afficherDetails());
        details.append("Niveau de ventilation : ").append(niveauVentilation).append("\n");
        details.append("Température : ").append(temperature).append("°C\n");
        return details.toString();
    }
}
