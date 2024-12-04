import org.junit.jupiter.api.Test;
import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;
import org.phantastikos.entite.creatures.lycanthropes.hurlements.HurlementAgressivite;
import org.phantastikos.entite.creatures.lycanthropes.hurlements.HurlementAppartenance;
import org.phantastikos.entite.creatures.lycanthropes.hurlements.HurlementDomination;
import org.phantastikos.entite.creatures.lycanthropes.hurlements.HurlementStrategie;
import org.phantastikos.structures.colonie.Colonie;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HurlementTest {

    @Test
    void testHurlementAgressivite() {
        Lycanthrope aggressor = new Lycanthrope("Loup Enragé", 'M', 85, 190, 30, 20, 15);
        HurlementStrategie strategie = new HurlementAgressivite();
        String resultat = strategie.emettre(aggressor);
        assertEquals("Loup Enragé exprime son agressivité !", resultat);
    }

    @Test
    void testHurlementAppartenance() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute Sombre", List.of(), "Hurlement Sombre");
        colonie.ajouterMeute(meute);
        Lycanthrope membre = new Lycanthrope("Membre", 'M', 70, 180, 25, 14, 8, 'γ', meute);
        meute.setMembres(List.of(membre));
        HurlementStrategie strategie = new HurlementAppartenance();
        String resultat = strategie.emettre(membre);
        assertTrue(resultat.contains("Hurlement Sombre"));
    }

    @Test
    void testHurlementDomination() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute Dominante", List.of(), "Hurlement Puissant");
        colonie.ajouterMeute(meute);
        Lycanthrope dominateur = new Lycanthrope("Alpha Dominateur", 'M', 90, 190, 35, 25, 18, 'α', meute);
        HurlementStrategie strategie = new HurlementDomination();
        String resultat = strategie.emettre(dominateur);
        assertTrue(resultat.contains("exprime sa domination"));
    }
}
