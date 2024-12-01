package org.phantastikos.structures.hopital.services;

public enum Budget {
    INEXISTANT,
    MEDIOCRE,
    INSUFFISANT,
    FAIBLE;

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
