package org.phantastikos.entite.creatures.lycanthropes;

import org.phantastikos.structures.colonie.Colonie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
