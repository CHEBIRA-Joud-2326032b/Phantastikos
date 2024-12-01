package org.phantastikos.entite.creatures.lycanthropes;


import org.phantastikos.entite.creatures.Creature;
import org.phantastikos.entite.creatures.comportements.Triage;

import java.util.HashMap;
import java.util.Map;

public class Lycanthrope extends Creature implements Triage {

    private CategorieAge catAge;
    private int force;
    private int facteurDom;
    private char rang;
    private int niveau;
    private int facteurImpet;
    private Meute meute;

    public Lycanthrope(String nom, char sexe, double poids, double taille, int age, int force, int facteurImpet, char rang, Meute meute) {
        super(nom, sexe, poids, taille, age);
        this.force = force;
        this.facteurImpet = facteurImpet;
        this.rang = rang;
        this.meute = meute;
        facteurDom = 5;
        catAge = CategorieAge.categoriser(age);
        setMoral(80);
        calculNiveau();


    }
    public void calculNiveau() {
        int numAge = (catAge == CategorieAge.JEUNE) ? 10 :
                (catAge == CategorieAge.ADULTE) ? 20 :
                        (catAge == CategorieAge.VIEUX) ? 15 : 1000;

        int numRang = (rang == 'α') ? 30 :
                (rang == 'β') ? 20 :
                        (rang == 'γ') ? 10 : 5;

        niveau = numAge * Math.min(facteurDom, 20) + numRang * force;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getFacteurDom() {
        return facteurDom;
    }

    public void setFacteurDom(int facteurDom) {
        this.facteurDom = facteurDom;
    }

    public char getRang() {
        return rang;
    }

    public void setRang(char rang) {
        this.rang = rang;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getFacteurImpet() {
        return facteurImpet;
    }

    public void setFacteurImpet(int facteurImpet) {
        this.facteurImpet = facteurImpet;
    }

    public Meute getMeute() {
        return meute;
    }

    public void setMeute(Meute meute) {
        this.meute = meute;
    }

    public CategorieAge getCatAge() {
        return catAge;
    }

    public void setCatAge(CategorieAge catAge) {
        this.catAge = catAge;
    }

    public Map<String, Object> recupererAttributs() {
        Map<String, Object> attributs = new HashMap<>();

        if (getResidence() != null) {
            attributs.put("nom", getNom());
            attributs.put("sexe", getSexe());
            attributs.put("poids", getPoids());
            attributs.put("taille", getTaille());
            attributs.put("age", getAge());
            attributs.put("moral", getMoral());
            attributs.put("maladies", getMaladies());
            attributs.put("chance", getChance());
        } else {
            attributs.put("nom", getNom());
            attributs.put("sexe", getSexe());
            attributs.put("poids", getPoids());
            attributs.put("taille", getTaille());
            attributs.put("catAge", catAge);
            attributs.put("moral", getMoral());
            attributs.put("maladies", getMaladies());
            attributs.put("chance", getChance());
            attributs.put("force", force);
            attributs.put("facteurDom", facteurDom);
            attributs.put("rang", rang);
            attributs.put("niveau", niveau);
            attributs.put("facteurImpet", facteurImpet);
            attributs.put("meute", meute);
        }

        return attributs;
    }


    public void quitterMeute(){
        meute = null;
    }

    private boolean dominerPossible(Lycanthrope cible) {
        if (cible.rang == 'α' && cible.getSexe() == 'F') {
            return false;
        } else
            return this.force + this.facteurImpet >= cible.force;
    }

    public boolean dominer(Lycanthrope cible) {
        if (!dominerPossible(cible)) {
            return false;
        }
        if (this.niveau > cible.niveau || cible.rang == 'ω') {
            this.facteurDom++;
            cible.facteurDom--;
            char ancienRang = this.rang;
            this.rang = cible.rang;
            cible.rang = ancienRang;
            calculNiveau();
            cible.calculNiveau();
            if (cible.equals(meute.getCoupleAlpha().getMaleAlpha())){
                meute.setCoupleAlpha(new CoupleAlpha(meute, this));
            }
            return true;
        } else {
            cible.agresser(this);
            return false;
        }
    }


    private void agresser(Lycanthrope cible) {
        cible.facteurDom--;
    }


    public boolean dernierDeSonSexe() {
        long count = meute.getMembres().stream()
                .filter(l -> l.getSexe() == this.getSexe() && l.getRang() == this.getRang())
                .count();
        return count > 1;
    }

    public void baisserRang() {
        if (rang != 'ω' && dernierDeSonSexe()) {
            rang++;
        }
    }

    public void emettreHurlement(TypeHurlement typeHurlement) {
        new Hurlement(this, typeHurlement);
    }

}

