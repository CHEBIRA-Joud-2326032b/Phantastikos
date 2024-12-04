import org.phantastikos.entite.etats.maladies.Maladie;
import org.phantastikos.entite.etats.maladies.TypeMaladie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour la gestion des maladies.
 * Ces tests valident les comportements des différentes méthodes des maladies,
 * telles que l'augmentation et la diminution du niveau de la maladie, ainsi que la vérification de sa létalité.
 */
public class MaladieTest {

    /**
     * Teste la méthode augmenterNiveau() pour augmenter le niveau d'une maladie.
     * Vérifie que le niveau de la maladie est correctement augmenté.
     */
    @Test
    void augmenterNiveauTest() {
        Maladie pec = new Maladie(TypeMaladie.PEC);
        pec.augmenterNiveau(3);
        // Vérifie que le niveau de la maladie est correctement augmenté à 3
        assertEquals(3, pec.getNiveauActuel());
    }

    /**
     * Teste la méthode diminuerNiveau() pour diminuer le niveau d'une maladie.
     * Vérifie que le niveau de la maladie est correctement diminué après augmentation préalable.
     */
    @Test
    void diminuerNiveauTest() {
        Maladie pec = new Maladie(TypeMaladie.PEC);
        pec.augmenterNiveau(3); // Augmente d'abord le niveau à 3
        pec.diminuerNiveau(2);   // Puis le diminue de 2
        // Vérifie que le niveau est bien de 1 après la diminution
        assertEquals(1, pec.getNiveauActuel());
    }

    /**
     * Teste la méthode estLetale() pour déterminer si la maladie est létale.
     * Vérifie que la maladie devient létale après un certain niveau d'augmentation.
     */
    @Test
    void estLetaleTest() {
        Maladie pec = new Maladie(TypeMaladie.PEC);
        // Vérifie que la maladie n'est pas létale au départ
        assertFalse(pec.estLetal());
        pec.augmenterNiveau(6); // Augmente le niveau de la maladie à 6
        // Vérifie que la maladie devient létale après avoir atteint un certain niveau
        assertTrue(pec.estLetal());
    }
}
