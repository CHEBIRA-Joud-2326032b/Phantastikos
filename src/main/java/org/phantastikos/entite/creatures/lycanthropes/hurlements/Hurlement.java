package org.phantastikos.entite.creatures.lycanthropes.hurlements;

import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;

public class Hurlement {
    private HurlementStrategie type;
    private Lycanthrope emetteur;

    public Hurlement(Lycanthrope emetteur, HurlementStrategie type) {
        this.emetteur = emetteur;
        this.type = type;
    }

    public String emettre() {
        return type.emettre(emetteur);
    }


}

