package org.phantastikos.entite.creatures.lycanthropes.hurlements;

import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;

public class HurlementSoumission implements HurlementStrategie{

    @Override
    public String emettre(Lycanthrope emetteur) {
        return emetteur.getNom() + " exprime sa soumission.";
    }
}
