package org.phantastikos.entite.creatures.lycanthropes.hurlements;

import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;

public class HurlementDomination implements HurlementStrategie{

    @Override
    public String emettre(Lycanthrope emetteur) {
        StringBuilder resultat = new StringBuilder(emetteur.getNom() + " exprime sa domination." + "\n");
        for (Meute meute : emetteur.getMeute().getColonie().getMeutes()) {
            for (Lycanthrope membre : meute.getMembres()) {
                if (!membre.equals(emetteur)) {
                    if (emetteur.getRang() == 'ω' || emetteur.getRang() <= membre.getRang()) {
                        resultat.append(membre.emettreHurlement(new HurlementAgressivite()));
                    } else {
                        resultat.append(membre.emettreHurlement(new HurlementSoumission()));
                    }
                }
            }
        }
        return resultat.toString();
    }
}
