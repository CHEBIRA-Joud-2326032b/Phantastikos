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

    /**
     * Constructeur de la classe Colonie.
     * Initialise la colonie avec une liste vide de meutes et un compteur de temps à 0.
     */
    public Colonie() {
        this.meutes = new ArrayList<>();
        temps = 0;
    }

    /**
     * Retourne la liste des meutes présentes dans la colonie.
     *
     * @return La liste des meutes.
     */
    public List<Meute> getMeutes() {
        return meutes;
    }

    /**
     * Définit la liste des meutes présentes dans la colonie.
     *
     * @param meutes La nouvelle liste des meutes.
     */
    public void setMeutes(List<Meute> meutes) {
        this.meutes = meutes;
    }

    /**
     * Retourne le temps actuel de la colonie.
     *
     * @return Le temps actuel de la colonie.
     */
    public int getTemps() {
        return temps;
    }

    /**
     * Définit le temps de la colonie.
     *
     * @param temps Le nouveau temps de la colonie.
     */
    public void setTemps(int temps) {
        this.temps = temps;
    }

    /**
     * Ajoute une meute à la colonie.
     * La meute se voit également attribuer cette colonie comme sa colonie.
     *
     * @param meute La meute à ajouter.
     */
    public void ajouterMeute(Meute meute) {
        meutes.add(meute);
        meute.setColonie(this);
    }

    /**
     * Détecte la création de nouvelles meutes.
     * Si un mâle et une femelle sont trouvés dans les solitaires, une nouvelle meute est formée.
     * La femelle et le mâle sont alors retirés des solitaires et ajoutés à la nouvelle meute.
     */
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

    /**
     * Fait vieillir tous les lycanthropes de toutes les meutes.
     * Catégorise leur âge en fonction de leur groupe d'âge.
     */
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

    /**
     * Gère l'évolution de la hiérarchie dans chaque meute.
     * Un lycanthrope peut dominer un autre lycanthrope dans la meute avec une probabilité aléatoire.
     */
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

    /**
     * Détermine si c'est la saison des amours.
     * Si le temps est divisible par 8, les meutes peuvent se reproduire.
     */
    public void determinerSaisonAmour(){
        if (temps % 8 == 0){
            for (Meute meute : meutes) {
                meute.reproduction();
            }
        }
    }

    /**
     * Déclare les lycanthropes "omega" dans chaque meute.
     * Cela permet de déterminer le lycanthrope ayant la position la plus basse dans la hiérarchie.
     */
    public void omegaLosers(){
        for (Meute meute : meutes) {
            meute.declarerLycanthropeOmega();
        }
    }

    /**
     * Fauche les lycanthropes âgés de plus de 80 ans dans chaque meute.
     * Ces lycanthropes sont considérés comme morts.
     */
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

    /**
     * Déclenche des hurlements aléatoires dans les meutes.
     * Chaque lycanthrope a une probabilité de 20% d'émettre un hurlement de domination ou d'appartenance.
     */
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

    /**
     * Avance le temps de la colonie d'un unité de temps et déclenche tous les processus liés à l'avancement du temps.
     * Cela inclut la détection de nouvelles meutes, le vieillissement, l'évolution de la hiérarchie, la saison des amours,
     * le fauchage des lycanthropes âgés, et le déclenchement des hurlements.
     */
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
