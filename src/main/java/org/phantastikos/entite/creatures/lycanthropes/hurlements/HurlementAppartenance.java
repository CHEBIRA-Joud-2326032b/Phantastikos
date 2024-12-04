package org.phantastikos.entite.creatures.lycanthropes.hurlements;

import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;

public class HurlementAppartenance implements HurlementStrategie{

    @Override
    public String emettre(Lycanthrope emetteur) {
        StringBuilder resultat = new StringBuilder();
        resultat.append(emetteur.getNom()).append(": ").append(emetteur.getMeute().getDescriptionHurlement()).append("\n");

        for (Lycanthrope recepteur : emetteur.getMeute().getMembres()) {
            if (!recepteur.equals(emetteur)) {
                resultat.append(recepteur.emettreHurlement(new HurlementAppartenanceReponse())).append("\n");
            }
        }

        for (Meute autre : emetteur.getMeute().getColonie().getMeutes()) {
            if (!autre.equals(emetteur.getMeute())) {
                resultat.append("La meute ").append(autre.getNom()).append(": ").append(autre.getDescriptionHurlement()).append("\n");
            }
        }

        return resultat.toString().trim();
    }
}
