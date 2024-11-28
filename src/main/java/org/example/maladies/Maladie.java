package org.example.maladies;

public class Maladie {
    private final TypeMaladie type;
    private int niveauActuel;

    public Maladie(TypeMaladie typeMaladie) {
        this.type = typeMaladie;
        this.niveauActuel = 0;
    }

    public TypeMaladie getType() {
        return type;
    }

    public String getNomComplet() {
        return type.getNomComplet();
    }

    public String getNomAbrege() {
        return type.getNomAbrege();
    }

    public int getNiveauActuel() {
        return niveauActuel;
    }

    public int getNiveauMaximum() {
        return type.getNiveauMax();
    }

    public void augmenterNiveau(int nombre) {
        niveauActuel += nombre;
    }

    public void diminuerNiveau(int nombre) {
        niveauActuel -= nombre;
    }

    public void changerNiveau(int niveau) {
        niveauActuel = niveau;
    }

    public boolean estLetal() {
        return niveauActuel >= getNiveauMaximum();
    }

    @Override
    public String toString() {
        return getNomComplet() + " (" + getNomAbrege() + "), Niveau : " + niveauActuel + "/" + getNiveauMaximum();
    }
}
