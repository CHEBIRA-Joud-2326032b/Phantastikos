package org.phantastikos.entite.creatures.lycanthropes.hurlements;

import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;

public class HurlementDomination implements HurlementStrategie{

    @Override
    public String emettre(Lycanthrope emetteur) {
        for (Meute meute : emetteur.getMeute().getColonie().getMeutes()) {
            for (Lycanthrope membre : meute.getMembres()) {
                if (!membre.equals(emetteur)) {
                    if (emetteur.getRang() == 'ω' || emetteur.getRang() < membre.getRang()) {
                        membre.emettreHurlement(new HurlementAgressivite());
                    } else {
                        membre.emettreHurlement(new HurlementSoumission());
                    }
                }
            }
        }
        return emetteur.getNom() + " exprime sa domination.";
    }
}
