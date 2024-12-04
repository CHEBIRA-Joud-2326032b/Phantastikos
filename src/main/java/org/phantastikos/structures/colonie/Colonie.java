package org.phantastikos.structures.colonie;

import org.phantastikos.entite.creatures.lycanthropes.Meute;

import java.util.ArrayList;
import java.util.List;

public class Colonie {
    private List<Meute> meutes;

    public Colonie() {
        this.meutes = new ArrayList<>();
    }

    public List<Meute> getMeutes() {
        return meutes;
    }

    public void setMeutes(List<Meute> meutes) {
        this.meutes = meutes;
    }

    public void ajouterMeute(Meute meute) {
        meutes.add(meute);
        meute.setColonie(this);
    }
}
