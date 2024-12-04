import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.phantastikos.entite.creatures.lycanthropes.Lycanthrope;
import org.phantastikos.entite.creatures.lycanthropes.Meute;
import org.phantastikos.structures.colonie.Colonie;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour les comportements des Lycanthropes.
 * Ces tests valident diverses actions que peuvent effectuer les Lycanthropes,
 * telles que le calcul du niveau, la domination, le départ de la meute, et la vérification du dernier de son sexe.
 */
class LycanthropeTest {

    /**
     * Teste le calcul du niveau d'un Lycanthrope.
     * Vérifie que la méthode de calcul du niveau fonctionne correctement en se basant sur les attributs du Lycanthrope.
     */
    @Test
    void testCalculNiveau() {
        Lycanthrope lycanthrope = new Lycanthrope("Fenrir", 'M', 80, 185, 25, 15, 10);
        // Le niveau est la somme des attributs : 80 + 185 + 25 + 15 + 10 = 175
        assertEquals(175, lycanthrope.getNiveau());
    }

    /**
     * Teste la domination réussie d'un Lycanthrope sur un autre.
     * Vérifie que le Lycanthrope dominé change de rang après avoir été dominé par un Lycanthrope plus puissant.
     */
    @Test
    void testDominerSuccess() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute des Ombres", List.of(), "Hurlement de l'ombre");
        colonie.ajouterMeute(meute);
        Lycanthrope alpha = new Lycanthrope("Alpha", 'M', 85, 190, 30, 20, 15, 'α', meute);
        Lycanthrope alphae = new Lycanthrope("femme", 'F', 12 ,12 ,32 ,12 ,12 , 'g', meute);
        meute.changerCoupleAlpha(alpha);
        Lycanthrope beta = new Lycanthrope("Beta", 'M', 75, 180, 25, 15, 10, 'β', meute);
        assertTrue(alpha.dominer(beta)); // Vérifie si la domination réussit
        assertEquals('α', beta.getRang()); // Vérifie que Beta est devenu Alpha
        assertEquals('β', alpha.getRang()); // Vérifie que Alpha est rétrogradé en Beta
    }

    /**
     * Teste la domination échouée d'un Lycanthrope sur un autre.
     * Vérifie que la domination échoue si le Lycanthrope dominé est plus fort ou équivalent.
     */
    @Test
    void testDominerFailure() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute des Ombres", List.of(), "Hurlement de l'ombre");
        colonie.ajouterMeute(meute);
        Lycanthrope omega = new Lycanthrope("Omega", 'M', 60, 170, 20, 10, 5, 'ω', meute);
        Lycanthrope beta = new Lycanthrope("Beta", 'M', 75, 180, 25, 15, 10, 'β', meute);
        assertFalse(omega.dominer(beta)); // Vérifie que Omega échoue à dominer Beta
    }

    /**
     * Teste la méthode permettant à un Lycanthrope de quitter sa meute.
     * Vérifie que le Lycanthrope n'a plus de meute après avoir quitté.
     */
    @Test
    void testQuitterMeute() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute des Ombres", List.of(), "Hurlement de l'ombre");
        colonie.ajouterMeute(meute);
        Lycanthrope lycan = new Lycanthrope("Loup Solitaire", 'F', 70, 175, 28, 12, 8, 'γ', meute);
        lycan.quitterMeute();
        // Vérifie que la meute du Lycanthrope est désormais nulle après avoir quitté la meute
        assertNull(lycan.getMeute());
    }

    /**
     * Teste si un Lycanthrope est le dernier de son sexe dans la meute.
     * Vérifie qu'un Lycanthrope masculin est bien le dernier de son sexe dans la meute s'il n'y a pas d'autres mâles.
     */
    @Test
    void testDernierDeSonSexe() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(null, "Meute des Ombres", List.of(), "Hurlement de l'ombre");
        colonie.ajouterMeute(meute);
        Lycanthrope male = new Lycanthrope("Mâle1", 'M', 80, 180, 30, 15, 10, 'β', meute);
        meute.setMembres(List.of(male)); // Ajoute un seul mâle dans la meute
        // Vérifie que le Lycanthrope est le dernier de son sexe, car il n'y a pas d'autres mâles
        assertTrue(male.dernierDeSonSexe());
    }
}
