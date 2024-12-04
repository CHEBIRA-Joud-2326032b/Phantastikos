package org.phantastikos.entite.creatures.lycanthropes;

import org.phantastikos.structures.colonie.Colonie;

import java.util.*;

public class Meute {
    private Colonie colonie;
    private String nom;
    private List<Lycanthrope> membres;
    private CoupleAlpha coupleAlpha;
    private String descriptionHurlement;
    private List<Lycanthrope> solitaires;


    public Meute(Colonie colonie, String nom, List<Lycanthrope> membres, String descriptionHurlement) {
        this.colonie = colonie;
        this.nom = nom;
        this.membres = new ArrayList<Lycanthrope>(membres);
        this.solitaires = new ArrayList<Lycanthrope>();
        this.descriptionHurlement = descriptionHurlement;
        coupleAlpha = new CoupleAlpha(this);
    }

    public Colonie getColonie() {
        return colonie;
    }

    public void setColonie(Colonie colonie) {
        this.colonie = colonie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Lycanthrope> getMembres() {
        return membres;
    }

    public void setMembres(List<Lycanthrope> membres) {
        this.membres = membres;
    }

    public CoupleAlpha getCoupleAlpha() {
        return coupleAlpha;
    }

    public void setCoupleAlpha(CoupleAlpha coupleAlpha) {
        this.coupleAlpha = coupleAlpha;
    }

    public String getDescriptionHurlement() {
        return descriptionHurlement;
    }

    public void setDescriptionHurlement(String descriptionHurlement) {
        this.descriptionHurlement = descriptionHurlement;
    }
    public void ajouterLycanthrope(Lycanthrope nouveau) {
        membres.add(nouveau);
    }

    public void enleverLycanthrope(Lycanthrope ancien){
        membres.remove(ancien);
    }

    public Map<String, String> recupererAttributs(){
        Map<String, String> attributs = new HashMap<>();
        attributs.put("nom", nom);
        attributs.put("nombre de membres", ""+membres.size());
        attributs.put("coupleAlpha", coupleAlpha.getMaleAlpha().getNom()+"❤"+coupleAlpha.getFemelleAlpha().getNom());
        attributs.put("descriptionHurlement", descriptionHurlement);
        return attributs;
    }

    public void changerCoupleAlpha(Lycanthrope nouveauMaleAlpha) {
        Lycanthrope femelleAlpha = coupleAlpha.trouverFemelleAlpha();
        if (coupleAlpha == null) {
            coupleAlpha = new CoupleAlpha(this);

        } else if (femelleAlpha == null) {
            colonie.getMeutes().remove(this);
        }
        else {
            coupleAlpha.getFemelleAlpha().setRang(coupleAlpha.getMaleAlpha().getRang());
            coupleAlpha.setMaleAlpha(nouveauMaleAlpha);
            coupleAlpha.setFemelleAlpha(femelleAlpha);
            actualiserNiveau();
        }


    }
    public void baisserRangMeute(){
        for (Lycanthrope l : membres){
            l.baisserRang();
        }
        actualiserNiveau();
    }

    public void actualiserNiveau(){
        for (Lycanthrope l : membres){
            l.calculNiveau();
        }
    }

    public void actualiserSolitaire(){
        Random aleatoire = new Random();
        List<Lycanthrope> copie = new ArrayList<>(membres);
        for (Lycanthrope l : copie){
            if (l.getRang() == 'ω' && 25 > aleatoire.nextInt(101)){
                l.quitterMeute();
            }
            actualiserNiveau();
        }
    }
    public String declarerLycanthropeOmega() {
        StringBuilder log = new StringBuilder();
        int moyenneNiveau = 0;

        log.append("Déclaration des Lycanthropes de rang Omega :\n");

        for (Lycanthrope l : membres) {
            moyenneNiveau += l.getNiveau();
        }
        moyenneNiveau = moyenneNiveau / membres.size();
        log.append("Niveau moyen des membres : ").append(moyenneNiveau).append(".\n");

        for (Lycanthrope l : membres) {
            if (l.getNiveau() <= moyenneNiveau - 20 && l.getRang() != 'α') {
                l.setRang('ω');
                log.append(l.getNom()).append(" est maintenant un Omega (niveau : ").append(l.getNiveau()).append(").\n");
            }
        }

        actualiserNiveau();
        log.append("Les niveaux des membres ont été actualisés.\n");

        return log.toString();
    }

    public void reproduction(){
        coupleAlpha.donnerNaissance();
    }

    public List<Lycanthrope> getSolitaires() {
        return solitaires;
    }

    public void setSolitaires(List<Lycanthrope> solitaires) {
        this.solitaires = solitaires;
    }

    public void ajouterSolitaire(Lycanthrope l){
        solitaires.add(l);
    }
    public void enleverSolitaire(Lycanthrope l){
        solitaires.remove(l);
    }

}
