package org.phantastikos.structures.colonie;

import org.phantastikos.entite.creatures.lycanthropes.CategorieAge;
import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;
import org.phantastikos.entite.creatures.lycanthropes.hurlements.Hurlement;
import org.phantastikos.entite.creatures.lycanthropes.hurlements.HurlementAppartenance;
import org.phantastikos.entite.creatures.lycanthropes.hurlements.HurlementDomination;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Colonie {
    private List<Meute> meutes;
    private int temps;

    public Colonie() {
        this.meutes = new ArrayList<>();
        temps = 0;
    }

    public List<Meute> getMeutes() {
        return meutes;
    }

    public void setMeutes(List<Meute> meutes) {
        this.meutes = meutes;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public void ajouterMeute(Meute meute) {
        meutes.add(meute);
        meute.setColonie(this);
    }

    public void detecterNouvelleMeute(){

        for (Meute meute : meutes) {
            Lycanthrope male = meute.getSolitaires().stream()
                    .filter(l -> l.getSexe() == 'M')
                    .findFirst()
                    .orElse(null);
            Lycanthrope femelle = meute.getSolitaires().stream()
                    .filter(l -> l.getSexe() == 'F')
                    .findFirst()
                    .orElse(null);
            if (male != null && femelle != null) {
                ajouterMeute(new Meute(this, "Meute"+meutes.size()+1, List.of(male,femelle),"badaddan"));
                meute.enleverLycanthrope(femelle);
                meute.enleverLycanthrope(male);
                meute.enleverSolitaire(femelle);
                meute.enleverSolitaire(male);
            }
        }
    }

    public void vieillissement(){
        for (Meute meute : meutes) {
            ArrayList<Lycanthrope> tous = new ArrayList<>();
            tous.addAll(meute.getSolitaires());
            tous.addAll(meute.getMembres());
            for (Lycanthrope l : tous){
                l.vieillir();
                l.setCatAge(CategorieAge.categoriser(l.getAge()));
            }
        }
    }

    public void evolutionHierarchie(){
        Random aleatoire = new Random();
        int proba = aleatoire.nextInt(101);

        for (Meute meute : meutes) {
            int cible = aleatoire.nextInt(meute.getMembres().size());
            for (Lycanthrope l : meute.getMembres()) {
                if (proba <30){
                    l.dominer(meute.getMembres().get(cible));
                }
            }
        }
    }

    public void determinerSaisonAmour(){
        if (temps % 8 == 0){
            for (Meute meute : meutes) {
                meute.reproduction();
            }
        }
    }

    public void omegaLosers(){
        for (Meute meute : meutes) {
            meute.declarerLycanthropeOmega();
        }
    }
    public void faucheuse(){
        for (Meute meute : meutes) {
            ArrayList<Lycanthrope> tous = new ArrayList<>();
            tous.addAll(meute.getSolitaires());
            tous.addAll(meute.getMembres());
            for (Lycanthrope l : tous){
                if (l.getAge() > 80){
                    l.trepasser();
                }
            }
        }
    }

    public void declencherHurlements(){
        Random aleatoire = new Random();
        for (Meute meute : meutes) {
            for (Lycanthrope l : meute.getMembres()){
                if (aleatoire.nextInt(101) < 20){
                    int rHurlement = aleatoire.nextInt(2);
                    l.emettreHurlement((rHurlement == 0) ? new HurlementDomination() : new HurlementAppartenance());
                }
            }
        }
    }
    public void avancerTemps() {
        detecterNouvelleMeute();
        omegaLosers();
        evolutionHierarchie();
        determinerSaisonAmour();
        vieillissement();
        faucheuse();
        declencherHurlements();
        temps++;
    }
}
