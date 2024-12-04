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

/**
 * Classe de tests unitaires pour les différentes stratégies de hurlement des Lycanthropes.
 * Ces tests valident les comportements des hurlements en fonction des stratégies d'agressivité, d'appartenance et de domination.
 */
class HurlementTest {

    /**
     * Teste la stratégie de hurlement d'agressivité d'un Lycanthrope.
     * Vérifie que le hurlement exprime correctement l'agressivité de la créature.
     */
    @Test
    void testHurlementAgressivite() {
        Lycanthrope aggressor = new Lycanthrope("Loup Enragé", 'M', 85, 190, 30, 20, 15);
        HurlementStrategie strategie = new HurlementAgressivite();
        String resultat = strategie.emettre(aggressor);
        // Vérifie que le hurlement exprime l'agressivité du lycanthrope
        assertEquals("Loup Enragé exprime son agressivité !", resultat);
    }

    /**
     * Teste la stratégie de hurlement d'appartenance d'un Lycanthrope à une meute.
     * Vérifie que le hurlement inclut bien le nom de la meute à laquelle appartient la créature.
     */
    @Test
    void testHurlementAppartenance() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute Sombre", List.of(), "Hurlement Sombre");
        colonie.ajouterMeute(meute);
        Lycanthrope membre = new Lycanthrope("Membre", 'M', 70, 180, 25, 14, 8, 'γ', meute);
        meute.setMembres(List.of(membre));
        HurlementStrategie strategie = new HurlementAppartenance();
        String resultat = strategie.emettre(membre);
        // Vérifie que le hurlement inclut le nom de la meute
        assertTrue(resultat.contains("Hurlement Sombre"));
    }

    /**
     * Teste la stratégie de hurlement de domination d'un Lycanthrope.
     * Vérifie que le hurlement exprime correctement la domination du Lycanthrope.
     */
    @Test
    void testHurlementDomination() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute Dominante", List.of(), "Hurlement Puissant");
        colonie.ajouterMeute(meute);
        Lycanthrope dominateur = new Lycanthrope("Alpha Dominateur", 'M', 90, 190, 35, 25, 18, 'α', meute);
        HurlementStrategie strategie = new HurlementDomination();
        String resultat = strategie.emettre(dominateur);
        // Vérifie que le hurlement exprime la domination du Lycanthrope
        assertTrue(resultat.contains("exprime sa domination"));
    }
}
