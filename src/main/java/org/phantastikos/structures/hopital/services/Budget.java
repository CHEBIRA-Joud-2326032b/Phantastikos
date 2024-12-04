package org.phantastikos.structures.hopital.services;

/**
 * Enumération représentant différents niveaux de budget disponibles pour un service médical dans l'hôpital.
 * Chaque niveau de budget indique la qualité des ressources disponibles dans un service médical.
 */
public enum Budget {

    /**
     * Le niveau de budget "Inexistant" indique qu'il n'y a pas de budget alloué au service médical.
     */
    INEXISTANT,

    /**
     * Le niveau de budget "Médiocre" indique que le service médical dispose d'un budget limité, mais insuffisant pour répondre à tous les besoins.
     */
    MEDIOCRE,

    /**
     * Le niveau de budget "Insuffisant" indique que le service médical dispose de ressources, mais celles-ci sont insuffisantes pour une prise en charge optimale des patients.
     */
    INSUFFISANT,

    /**
     * Le niveau de budget "Faible" indique que le service médical dispose de ressources limitées, mais avec certaines restrictions.
     */
    FAIBLE;

    /**
     * Redéfinition de la méthode `toString` pour afficher une représentation textuelle du niveau de budget.
     *
     * @return Une chaîne de caractères représentant le niveau de budget.
     */
    @Override
    public String toString() {
        switch (this) {
            case INEXISTANT: return "Inexistant";
            case MEDIOCRE: return "Médiocre";
            case INSUFFISANT: return "Insuffisant";
            case FAIBLE: return "Faible";
            default: return "Inconnu";
        }
    }
}
