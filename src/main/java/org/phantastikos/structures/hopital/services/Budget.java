package org.phantastikos.structures.hopital.services;

public enum Budget {
    INEXISTANT,
    MEDIOCRE,
    INSUFFISANT,
    FAIBLE;

    public static Budget CategoriserBudget(int budget){
        if (budget < 200){
            return INEXISTANT;
        }
        else if (budget < 400){
            return MEDIOCRE;
        }
        else if (budget < 600){
            return INSUFFISANT;
        }
        else {
            return FAIBLE;
        }
    }
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
