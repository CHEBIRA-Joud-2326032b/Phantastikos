package org.phantastikos.structures.colonie;

import org.phantastikos.entite.creatures.lycanthropes.CategorieAge;
import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;
import org.phantastikos.entite.creatures.lycanthropes.hurlements.Hurlement;
import org.phantastikos.entite.creatures.lycanthropes.hurlements.HurlementAppartenance;
import org.phantastikos.entite.creatures.lycanthropes.hurlements.HurlementDomination;
import org.phantastikos.entite.creatures.lycanthropes.hurlements.HurlementStrategie;

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

    public String detecterNouvelleMeute() {
        StringBuilder log = new StringBuilder();
        ArrayList<Meute> copie = new ArrayList<>(meutes);
        log.append("Détection de nouvelles meutes :\n");

        for (Meute meute : copie) {
            Lycanthrope male = meute.getSolitaires().stream()
                    .filter(l -> l.getSexe() == 'M' && l.getCatAge() == CategorieAge.ADULTE)
                    .findFirst()
                    .orElse(null);
            Lycanthrope femelle = meute.getSolitaires().stream()
                    .filter(l -> l.getSexe() == 'F' && l.getCatAge() == CategorieAge.ADULTE)
                    .findFirst()
                    .orElse(null);
            if (male != null && femelle != null) {
                String nouvelleMeuteNom = "Meute " + (meutes.size() + 1);
                ajouterMeute(new Meute(this, nouvelleMeuteNom, new ArrayList<>(List.of(male, femelle)), "Hurlement " + (meutes.size() + 1)));
                meute.enleverLycanthrope(femelle);
                meute.enleverLycanthrope(male);
                meute.enleverSolitaire(femelle);
                meute.enleverSolitaire(male);

                log.append("Nouvelle meute formée : ").append(nouvelleMeuteNom).append(" avec ").append(male.getNom()).append(" et ").append(femelle.getNom()).append(".\n");
            }
        }

        if (log.toString().equals("Détection de nouvelles meutes :\n")) {
            log.append("Aucune nouvelle meute détectée.\n");
        }

        return log.toString();
    }


    public String vieillissement() {
        StringBuilder log = new StringBuilder("Vieillissement des lycanthropes :\n");
        for (Meute meute : meutes) {
            ArrayList<Lycanthrope> tous = new ArrayList<>();
            tous.addAll(meute.getSolitaires());
            tous.addAll(meute.getMembres());
            for (Lycanthrope l : tous) {
                int ageAvant = l.getAge();
                l.vieillir();
                l.setCatAge(CategorieAge.categoriser(l.getAge()));
                log.append(l.getNom())
                        .append(" : ")
                        .append(ageAvant)
                        .append(" ans -> ")
                        .append(l.getAge())
                        .append(" ans.\n");
            }
        }
        return log.toString();
    }


    public String evolutionHierarchie() {
        StringBuilder log = new StringBuilder();
        Random aleatoire = new Random();
        int proba = aleatoire.nextInt(101);

        log.append("Évolution de la hiérarchie :\n");
        ArrayList<Meute> copie = new ArrayList<>(meutes);
        for (Meute meute : copie) {
            for (Lycanthrope l : meute.getMembres()) {
                int cible = aleatoire.nextInt(meute.getMembres().size());
                if (proba < 30) {
                    Lycanthrope cibleLycan = meute.getMembres().get(cible);
                    if(l.dominer(cibleLycan)){
                        log.append(l.getNom()).append(" a dominé ").append(cibleLycan.getNom()).append(" dans la ").append(meute.getNom()).append(".\n");
                    }
                    else {
                        log.append(l.getNom()).append(" n'a pas réussi dominé ").append(cibleLycan.getNom()).append(" dans la ").append(meute.getNom()).append(".\n");
                    }

                }
            }
        }
        return log.toString();
    }


    public String determinerSaisonAmour() {
        StringBuilder log = new StringBuilder();
        if (temps % 6 == 0) {
            log.append("C'est la saison des amours !\n");
            for (Meute meute : meutes) {
                meute.reproduction();
                log.append("Reproduction dans la ").append(meute.getNom()).append(".\n");
            }
        } else {
            log.append("Pas de saison des amours pour ce cycle.\n");
        }
        return log.toString();
    }


    public String omegaLosers(){
        StringBuilder log = new StringBuilder("Déclaration des Omégas :\n");
        for (Meute meute : meutes) {
            log.append(meute.declarerLycanthropeOmega()).append("\n");
        }
        return log.toString();
    }

    public String faucheuse() {
        StringBuilder log = new StringBuilder();
        log.append("Intervention de la Faucheuse :\n");
        ArrayList<Meute> copie = new ArrayList<>(meutes);
        for (Meute meute : copie) {
            ArrayList<Lycanthrope> tous = new ArrayList<>();
            tous.addAll(meute.getSolitaires());
            tous.addAll(meute.getMembres());
            for (Lycanthrope l : tous) {
                char rang = l.getRang();
                if (l.getAge() > 80) {
                    log.append(l.getNom()).append(" (Rang: ").append(rang).append(") est décédé à l'âge de ").append(l.getAge()).append(".\n");
                    l.trepasser();
                    if (rang == 'α') {
                        Lycanthrope maleAlpha = meute.getCoupleAlpha().trouverMaleAlpha();
                        if (maleAlpha != null){
                            meute.changerCoupleAlpha(maleAlpha);
                            log.append("Changement de couple Alpha dans la ").append(meute.getNom()).append(".\n");
                        }
                        else {
                            meutes.remove(meute);
                            log.append("Il n'y avait pas de remplaçant pour le male Alpha donc la meute ").append(meute.getNom()).append(" est dissoute !");
                        }

                    }
                }
            }
        }
        return log.toString();
    }


    public void seulOuMalAccompagne(){
        for (Meute meute : meutes) {
            meute.actualiserSolitaire();
        }
    }
    public String declencherHurlements() {
        StringBuilder log = new StringBuilder();
        Random aleatoire = new Random();
        log.append("Déclenchement des hurlements :\n");

        for (Meute meute : meutes) {
            for (Lycanthrope l : meute.getMembres()) {
                if (aleatoire.nextInt(101) < 20) {
                    int rHurlement = aleatoire.nextInt(2);
                    HurlementStrategie hurlement = (rHurlement == 0) ? new HurlementDomination() : new HurlementAppartenance();

                    log.append(l.emettreHurlement(hurlement)).append("\n");
                }
            }
        }
        return log.toString();
    }


    public String avancerTemps() {

        String rapport = "Jour " + (temps + 1) + " :\n" + omegaLosers() + "\n" +
                evolutionHierarchie() + "\n" +
                determinerSaisonAmour() + "\n" +
                vieillissement() + "\n" +
                detecterNouvelleMeute() + "\n" +
                faucheuse() + "\n" +
                declencherHurlements() + "\n";
        seulOuMalAccompagne();
        temps++;
        return rapport;
    }

}
