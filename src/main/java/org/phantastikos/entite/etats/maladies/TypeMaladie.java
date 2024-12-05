package org.phantastikos.entite.etats.maladies;

import java.util.Random;

/**
 * Enumération représentant les différents types de maladies possibles.
 * Chaque type de maladie est associé à un nom complet, un nom abrégé et un niveau maximum
 * que la maladie peut atteindre.
 * <p>
 * Ces maladies sont utilisées dans le contexte des créatures et leur évolution dans le jeu.
 * Chaque maladie a des caractéristiques spécifiques, comme son nom et la gravité de son impact.
 * </p>
 */
public enum TypeMaladie {

    MDC("Maladie débilitante chronique", "MDC", 10),  // Exemple : une maladie chronique avec un niveau max de 10
    FOMO("Syndrome fear of missing out", "FOMO", 8),  // Exemple : une maladie psychologique avec un niveau max de 8
    DRS("Dépendance aux réseaux sociaux", "DRS", 14),  // Exemple : une addiction aux réseaux sociaux avec un niveau max de 14
    PEC("Porphyrie érythropoïétique congénitale", "PEC", 6),  // Exemple : une maladie génétique rare avec un niveau max de 6
    ZPL("Zoopathie paraphrénique lycanthropique", "ZPL", 8);  // Exemple : une maladie paranormale avec un niveau max de 8

    private final String nomComplet;  // Nom complet de la maladie
    private final String nomAbrege;  // Nom abrégé de la maladie
    private final int niveauMax;     // Niveau maximum que la maladie peut atteindre

    /**
     * Constructeur de l'énumération {@link TypeMaladie}.
     * Chaque type de maladie est défini par un nom complet, un nom abrégé et un niveau maximum.
     *
     * @param nomComplet le nom complet de la maladie.
     * @param nomAbrege le nom abrégé de la maladie.
     * @param niveauMax le niveau maximum de la maladie (la gravité maximale qu'elle peut atteindre).
     */
    TypeMaladie(String nomComplet, String nomAbrege, int niveauMax) {
        this.nomComplet = nomComplet;
        this.nomAbrege = nomAbrege;
        this.niveauMax = niveauMax;
    }

    /**
     * Retourne le nom complet de la maladie.
     *
     * @return le nom complet de la maladie.
     */
    public String getNomComplet() {
        return nomComplet;
    }

    /**
     * Retourne le nom abrégé de la maladie.
     *
     * @return le nom abrégé de la maladie.
     */
    public String getNomAbrege() {
        return nomAbrege;
    }

    /**
     * Retourne le niveau maximum que la maladie peut atteindre.
     *
     * @return le niveau maximum de la maladie.
     */
    public int getNiveauMax() {
        return niveauMax;
    }

    public static TypeMaladie getAleatoire() {
        TypeMaladie[] maladies = values(); // Récupère tous les types de maladie
        Random random = new Random();
        return maladies[random.nextInt(maladies.length)]; // Sélectionne une maladie aléatoire
    }
}
