package org.phantastikos.entite.creatures.lycanthropes.hurlements;

import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;

public class HurlementAppartenanceReponse implements HurlementStrategie{

    @Override
    public String emettre(Lycanthrope emetteur) {
        return emetteur.getNom() + " répond au hurlement d'appartenance.\n";
    }
}
