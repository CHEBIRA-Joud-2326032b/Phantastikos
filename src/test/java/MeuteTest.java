import org.junit.jupiter.api.Test;
import org.phantastikos.entite.creatures.lycanthropes.CoupleAlpha;
import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour la gestion des meutes et des Lycanthropes.
 * Ces tests valident les méthodes permettant d'ajouter des Lycanthropes à une meute,
 * de changer le couple Alpha et de déclarer un Lycanthrope comme Omega.
 */
class MeuteTest {

    /**
     * Teste l'ajout d'un Lycanthrope dans une meute.
     * Vérifie que le Lycanthrope est bien ajouté à la liste des membres de la meute.
     */
    @Test
    void testAjouterLycanthrope() {
        Meute meute = new Meute(null, "Meute Sauvage", List.of(), "Hurlement Sauvage");
        Lycanthrope lycan = new Lycanthrope("Loup Test", 'F', 70, 175, 20, 12, 8);
        meute.ajouterLycanthrope(lycan);
        // Vérifie que le Lycanthrope a bien été ajouté à la liste des membres de la meute
        assertTrue(meute.getMembres().contains(lycan));
    }

    /**
     * Teste le changement de couple Alpha dans une meute.
     * Vérifie que le nouveau Lycanthrope devient le mâle Alpha du couple alpha de la meute.
     */
    @Test
    void testChangerCoupleAlpha() {
        Lycanthrope male = new Lycanthrope("Alpha Male", 'M', 90, 190, 35, 20, 15);
        Lycanthrope femelle = new Lycanthrope("Alpha Femelle", 'F', 75, 170, 30, 18, 12);
        Meute meute = new Meute(null, "Meute Royale", List.of(male, femelle), "Hurlement Royal");
        Lycanthrope nouveauMale = new Lycanthrope("Nouveau Alpha", 'M', 85, 180, 28, 22, 14);
        meute.changerCoupleAlpha(nouveauMale);
        // Vérifie que le nouveau mâle est bien devenu le mâle Alpha du couple alpha
        assertEquals(nouveauMale, meute.getCoupleAlpha().getMaleAlpha());
    }

    /**
     * Teste la méthode de déclaration d'un Omega dans une meute.
     * Vérifie que le Lycanthrope le plus faible dans la meute est bien déclaré comme Omega.
     */
    @Test
    void testDeclarerOmega() {
        Lycanthrope faible = new Lycanthrope("Faible", 'M', 60, 165, 20, 10, 5);
        Lycanthrope moyen = new Lycanthrope("Moyen", 'M', 75, 175, 25, 15, 8);
        Meute meute = new Meute(null, "Meute Moyenne", List.of(faible, moyen), "Hurlement Moyen");
        meute.declarerLycanthropeOmega();
        // Vérifie que le Lycanthrope le plus faible (faible) a bien été déclaré Omega (rang 'ω')
        assertEquals('ω', faible.getRang());
    }
}
