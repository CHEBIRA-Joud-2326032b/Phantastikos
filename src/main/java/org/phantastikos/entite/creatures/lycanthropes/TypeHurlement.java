package org.phantastikos.entite.creatures.lycanthropes;

public enum TypeHurlement {
    APPARTENANCE("Appartenance à une meute"),
    DOMINATION("Domination"),
    SOUMISSION("Soumission"),
    AGRESSIVITE("Agressivité");

    private final String description;

    TypeHurlement(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

