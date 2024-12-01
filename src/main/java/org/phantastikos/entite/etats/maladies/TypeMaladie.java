package org.phantastikos.entite.etats.maladies;

public enum TypeMaladie {
    MDC("Maladie débilitante chronique", "MDC", 10),
    FOMO("Syndrome fear of missing out", "FOMO", 8),
    DRS("Dépendance aux réseaux sociaux", "DRS", 14),
    PEC("Porphyrie érythropoïétique congénitale", "PEC", 6),
    ZPL("Zoopathie paraphrénique lycanthropique", "ZPL", 8);

    private final String nomComplet;
    private final String nomAbrege;
    private final int niveauMax;

    TypeMaladie(String nomComplet, String nomAbrege, int niveauMax) {
        this.nomComplet = nomComplet;
        this.nomAbrege = nomAbrege;
        this.niveauMax = niveauMax;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public String getNomAbrege() {
        return nomAbrege;
    }

    public int getNiveauMax() {
        return niveauMax;
    }
}