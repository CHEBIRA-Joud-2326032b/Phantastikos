package org.phantastikos.entite.creatures.lycanthropes;

import org.phantastikos.structures.colonie.Colonie;

import java.util.List;

public class Meute {
    private Colonie colonie;
    private String nom;
    private List<Lycanthrope> membres;
    private CoupleAlpha coupleAlpha;
    private String descriptionHurlement;


    public Meute(Colonie colonie, String nom, List<Lycanthrope> membres, String descriptionHurlement) {
        this.colonie = colonie;
        this.nom = nom;
        this.membres = membres;
        this.descriptionHurlement = descriptionHurlement;
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

    public void changerCoupleAlpha(Lycanthrope nouveauMaleAlpha) {
        if (coupleAlpha == null) {
            coupleAlpha = new CoupleAlpha(this);

        }else {
            coupleAlpha.getFemelleAlpha().setRang(coupleAlpha.getMaleAlpha().getRang());
            coupleAlpha.setMaleAlpha(nouveauMaleAlpha);
            coupleAlpha.setFemelleAlpha(coupleAlpha.trouverFemelleAlpha());
        }

    }
    public void baisserRangMeute(){
        for (Lycanthrope l : membres){
            l.baisserRang();
        }
    }

    public void declarerLycanthropeOmega(){
        int moyenneNiveau = 0;
        for (Lycanthrope l : membres){
            moyenneNiveau += l.getNiveau();
        }
        moyenneNiveau = moyenneNiveau / membres.size();
        for (Lycanthrope l : membres){
            if (l.getNiveau() <= moyenneNiveau - 20){
                l.setRang('ω');
            }
        }
    }
    public void reproduction(){
        coupleAlpha.donnerNaissance();
    }

}
