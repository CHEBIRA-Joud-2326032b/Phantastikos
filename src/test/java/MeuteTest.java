import org.junit.jupiter.api.Test;
import org.phantastikos.entite.creatures.lycanthropes.CoupleAlpha;
import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MeuteTest {

    @Test
    void testAjouterLycanthrope() {
        Meute meute = new Meute(null, "Meute Sauvage", List.of(), "Hurlement Sauvage");
        Lycanthrope lycan = new Lycanthrope("Loup Test", 'F', 70, 1.75, 20, 12, 8);
        meute.ajouterLycanthrope(lycan);
        assertTrue(meute.getMembres().contains(lycan));
    }

    @Test
    void testChangerCoupleAlpha() {
        Lycanthrope male = new Lycanthrope("Alpha Male", 'M', 90, 1.9, 35, 20, 15);
        Lycanthrope femelle = new Lycanthrope("Alpha Femelle", 'F', 75, 1.7, 30, 18, 12);
        Meute meute = new Meute(null, "Meute Royale", List.of(male, femelle), "Hurlement Royal");
        Lycanthrope nouveauMale = new Lycanthrope("Nouveau Alpha", 'M', 85, 1.8, 28, 22, 14);
        meute.changerCoupleAlpha(nouveauMale);
        assertEquals(nouveauMale, meute.getCoupleAlpha().getMaleAlpha());
    }

    @Test
    void testDeclarerOmega() {
        Lycanthrope faible = new Lycanthrope("Faible", 'M', 60, 1.65, 20, 10, 5);
        Lycanthrope moyen = new Lycanthrope("Moyen", 'M', 75, 1.75, 25, 15, 8);
        Meute meute = new Meute(null, "Meute Moyenne", List.of(faible, moyen), "Hurlement Moyen");
        meute.declarerLycanthropeOmega();
        assertEquals('ω', faible.getRang());
    }
}
