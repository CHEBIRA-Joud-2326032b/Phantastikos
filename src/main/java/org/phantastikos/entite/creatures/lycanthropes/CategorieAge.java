package org.phantastikos.entite.creatures.lycanthropes;

public enum CategorieAge {
    JEUNE, ADULTE, VIEUX;

    public static CategorieAge categoriser(int age) {
        if (age < 15) {
            return JEUNE;
        } else if (age < 45) {
            return ADULTE;
        } else {
            return VIEUX;
        }
    }
}
