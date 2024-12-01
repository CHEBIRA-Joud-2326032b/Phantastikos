package org.phantastikos.entite.creatures.lycanthropes;

import org.phantastikos.structures.colonie.Colonie;

import java.util.List;

public class Meute {
    private Colonie colonie;
    private String nom;
    private List<Lycanthrope> membres;
    private CoupleAlpha coupleAlpha;


    public Meute(List<Lycanthrope> membres) {
        this.membres = membres;
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

    public void ajouterLycanthrope(Lycanthrope nouveau) {
        membres.add(nouveau);
    }

    public void enleverLycanthrope(Lycanthrope ancien){
        membres.remove(ancien);
    }
    public void changerCoupleAlpha(Lycanthrope nouveauMaleAlpha, Lycanthrope nouvelleFemelleAlpha) {
        coupleAlpha.getFemelleAlpha().setRang(coupleAlpha.getMaleAlpha().getRang());
        coupleAlpha.setMaleAlpha(nouveauMaleAlpha);
        coupleAlpha.setFemelleAlpha(nouvelleFemelleAlpha);
    }
    public void baisserRangMeute(){
        for (Lycanthrope l : membres){
            l.baisserRang();
        }
    }

    public void reproduction(){
        coupleAlpha.donnerNaissance();
    }
}
