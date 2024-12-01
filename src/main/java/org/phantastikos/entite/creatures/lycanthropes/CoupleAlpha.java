package org.phantastikos.entite.creatures.lycanthropes;

import org.phantastikos.utils.NameGenerator;

import java.util.Comparator;
import java.util.Random;

public class CoupleAlpha {
    private final Meute meute;
    private Lycanthrope maleAlpha;
    private Lycanthrope femelleAlpha;

    public CoupleAlpha(Meute meute) {
        this.meute = meute;
        this.maleAlpha = trouverMaleAlpha();
        this.femelleAlpha = trouverFemelleAlpha();
    }
    public CoupleAlpha(Meute meute, Lycanthrope maleAlpha) {
        this.meute = meute;
        this.maleAlpha = maleAlpha;
        this.femelleAlpha = trouverFemelleAlpha();
    }

    private Lycanthrope trouverMaleAlpha() {
        return meute.getMembres().stream()
                .filter(m -> m.getSexe() == 'M' && m.getCatAge() == CategorieAge.ADULTE)
                .max(Comparator.comparingInt(Lycanthrope::getForce))
                .orElse(null);
    }

    private Lycanthrope trouverFemelleAlpha() {
        return meute.getMembres().stream()
                .filter(m -> m.getSexe() == 'F' && m.getCatAge() == CategorieAge.ADULTE)
                .max(Comparator.comparingInt(Lycanthrope::getForce))
                .orElse(null);
    }

    public Lycanthrope getMaleAlpha() {
        return maleAlpha;
    }

    public Lycanthrope getFemelleAlpha() {
        return femelleAlpha;
    }

    public void setMaleAlpha(Lycanthrope maleAlpha) {
        this.maleAlpha = maleAlpha;
    }

    public void setFemelleAlpha(Lycanthrope femelleAlpha) {
        this.femelleAlpha = femelleAlpha;
    }

    public void donnerNaissance() {
        Random random = new Random();
        NameGenerator prenom = new NameGenerator(random.nextInt(10));
        int nombreEnfants = random.nextInt(7) + 1;

        for (int i = 0; i < nombreEnfants; i++) {
            String nom = prenom.getName() + (meute.getMembres().size() + 1);
            char sexe = random.nextBoolean() ? 'M' : 'F';
            double poids = 20 + random.nextDouble() * 30;
            double taille = 60 + random.nextDouble() * 2;
            int age = 0;
            int force = 50 + random.nextInt(51);
            int facteurImpet = random.nextInt(10) + 1;
            char rang = 'γ';
            meute.ajouterLycanthrope(new Lycanthrope(nom, sexe, poids, taille, age, force, facteurImpet, rang, meute));
        }
    }
}

