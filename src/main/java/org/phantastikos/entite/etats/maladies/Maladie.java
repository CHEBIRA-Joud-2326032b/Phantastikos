package org.phantastikos.entite.etats.maladies;

/**
 * Classe représentant une maladie dans le système de gestion des créatures.
 * <p>
 * Une maladie est associée à un type spécifique, défini par l'énumération {@link TypeMaladie}.
 * Elle possède un niveau qui évolue au fil du temps, et son niveau actuel détermine sa gravité. Une maladie
 * devient létale lorsque son niveau atteint ou dépasse le niveau maximum défini pour son type.
 * </p>
 */
public class Maladie {
    private final TypeMaladie type;  // Le type de la maladie (par exemple : Grippe, Fièvre, etc.)
    private int niveauActuel;  // Le niveau actuel de la maladie, représentant sa gravité

    /**
     * Constructeur de la classe `Maladie`.
     *
     * @param typeMaladie le type de la maladie, défini par l'énumération {@link TypeMaladie}.
     *                    Ce paramètre détermine les propriétés de la maladie (nom, niveau maximum, etc.).
     */
    public Maladie(TypeMaladie typeMaladie) {
        this.type = typeMaladie;
        this.niveauActuel = 0;  // Initialise le niveau de la maladie à 0
    }

    /**
     * Retourne le type de la maladie.
     *
     * @return le type de la maladie.
     */
    public TypeMaladie getType() {
        return type;
    }

    /**
     * Retourne le nom complet de la maladie en fonction de son type.
     *
     * @return le nom complet de la maladie.
     */
    public String getNomComplet() {
        return type.getNomComplet();
    }

    /**
     * Retourne le nom abrégé de la maladie en fonction de son type.
     *
     * @return le nom abrégé de la maladie.
     */
    public String getNomAbrege() {
        return type.getNomAbrege();
    }

    /**
     * Retourne le niveau actuel de la maladie.
     *
     * @return le niveau actuel de la maladie.
     */
    public int getNiveauActuel() {
        return niveauActuel;
    }

    /**
     * Retourne le niveau maximum que la maladie peut atteindre, défini par son type.
     *
     * @return le niveau maximum de la maladie.
     */
    public int getNiveauMaximum() {
        return type.getNiveauMax();
    }

    /**
     * Augmente le niveau actuel de la maladie.
     *
     * @param nombre la quantité de niveau à ajouter à la maladie.
     */
    public void augmenterNiveau(int nombre) {
        niveauActuel += nombre;
    }

    /**
     * Diminue le niveau actuel de la maladie.
     *
     * @param nombre la quantité de niveau à soustraire de la maladie.
     */
    public void diminuerNiveau(int nombre) {
        niveauActuel -= nombre;
    }

    /**
     * Modifie directement le niveau de la maladie.
     *
     * @param niveau le niveau à attribuer à la maladie.
     */
    public void changerNiveau(int niveau) {
        niveauActuel = niveau;
    }

    /**
     * Vérifie si la maladie est létale, c'est-à-dire si son niveau atteint ou dépasse le niveau maximum.
     *
     * @return {@code true} si la maladie est létale, sinon {@code false}.
     */
    public boolean estLetal() {
        return niveauActuel >= getNiveauMaximum();
    }

    /**
     * Retourne une représentation textuelle de la maladie, comprenant son nom complet, son nom abrégé,
     * ainsi que son niveau actuel et son niveau maximum.
     *
     * @return une chaîne de caractères représentant la maladie.
     */
    @Override
    public String toString() {
        return getNomComplet() + " (" + getNomAbrege() + "), Niveau : " + niveauActuel + "/" + getNiveauMaximum();
    }

    /**
     * Retourne le type de la maladie sous forme de chaîne de caractères.
     *
     * @return le type de la maladie.
     */
    public String getTypeMaladie() {
        return this.type.toString();
    }
}
