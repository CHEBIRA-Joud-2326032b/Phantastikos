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
        maleAlpha.setRang('α');
        this.femelleAlpha = trouverFemelleAlpha();
        femelleAlpha.setRang('α');
        meute.actualiserNiveau();
    }
    public CoupleAlpha(Meute meute, Lycanthrope maleAlpha) {
        this.meute = meute;
        this.maleAlpha = maleAlpha;
        this.femelleAlpha = trouverFemelleAlpha();
    }

    public CoupleAlpha(Lycanthrope maleAlpha, Lycanthrope femelleAlpha, Meute meute) {
        this.maleAlpha = maleAlpha;
        this.femelleAlpha = femelleAlpha;
        this.meute = meute;
    }
    public Lycanthrope trouverMaleAlpha() {
        return meute.getMembres().stream()
                .filter(m -> m.getSexe() == 'M' && m.getCatAge() == CategorieAge.ADULTE)
                .max(Comparator.comparingInt(Lycanthrope::getForce))
                .orElse(null);
    }

    public Lycanthrope trouverFemelleAlpha() {
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
        this.maleAlpha.setRang('α');
    }

    public void setFemelleAlpha(Lycanthrope femelleAlpha) {
        this.femelleAlpha = femelleAlpha;
        this.femelleAlpha.setRang('α');
    }

    public void donnerNaissance() {
        Random random = new Random();
        NameGenerator prenom = new NameGenerator(random.nextInt(10));
        int nombreEnfants = random.nextInt(7) + 1;

        for (int i = 0; i < nombreEnfants; i++) {
            String nom = prenom.getName() + (meute.getMembres().size() + 1);
            char sexe = random.nextBoolean() ? 'M' : 'F';
            int poids = 20 + random.nextInt(2) * 15;
            int taille = 60 + random.nextInt(2) * 2;
            int age = 0;
            int force = 50 + random.nextInt(51);
            int facteurImpet = random.nextInt(10) + 1;
            char rang = 'γ';
            meute.ajouterLycanthrope(new Lycanthrope(nom, sexe, poids, taille, age, force, facteurImpet, rang, meute));
        }
    }
}

