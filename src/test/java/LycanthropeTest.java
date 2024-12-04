import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;
import org.phantastikos.structures.colonie.Colonie;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LycanthropeTest {

    @Test
    void testCalculNiveau() {
        Lycanthrope lycanthrope = new Lycanthrope("Fenrir", 'M', 80, 185, 25, 15, 10);
        assertEquals(175, lycanthrope.getNiveau());
    }

    @Test
    void testDominerSuccess() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute des Ombres", List.of(), "Hurlement de l'ombre");
        colonie.ajouterMeute(meute);
        Lycanthrope alpha = new Lycanthrope("Alpha", 'M', 85, 190, 30, 20, 15, 'α', meute);
        Lycanthrope alphae = new Lycanthrope("femme", 'F', 12 ,12 ,32 ,12 ,12 , 'g', meute);
        meute.changerCoupleAlpha(alpha);
        Lycanthrope beta = new Lycanthrope("Beta", 'M', 75, 180, 25, 15, 10, 'β', meute);
        assertTrue(alpha.dominer(beta));
        assertEquals('α', beta.getRang());
        assertEquals('β', alpha.getRang());
    }

    @Test
    void testDominerFailure() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute des Ombres", List.of(), "Hurlement de l'ombre");
        colonie.ajouterMeute(meute);
        Lycanthrope omega = new Lycanthrope("Omega", 'M', 60, 170, 20, 10, 5, 'ω', meute);
        Lycanthrope beta = new Lycanthrope("Beta", 'M', 75, 180, 25, 15, 10, 'β', meute);
        assertFalse(omega.dominer(beta));
    }

    @Test
    void testQuitterMeute() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute des Ombres", List.of(), "Hurlement de l'ombre");
        colonie.ajouterMeute(meute);
        Lycanthrope lycan = new Lycanthrope("Loup Solitaire", 'F', 70, 175, 28, 12, 8, 'γ', meute);
        lycan.quitterMeute();
        assertNull(lycan.getMeute());
    }

    @Test
    void testDernierDeSonSexe() {

        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute des Ombres", List.of(), "Hurlement de l'ombre");
        colonie.ajouterMeute(meute);
        Lycanthrope male = new Lycanthrope("Mâle1", 'M', 80, 180, 30, 15, 10, 'β', meute);
        meute.setMembres(List.of(male));
        assertTrue(male.dernierDeSonSexe());
    }
}
