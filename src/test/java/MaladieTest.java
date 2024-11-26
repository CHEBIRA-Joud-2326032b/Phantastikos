import org.example.maladies.Maladie;
import org.example.maladies.TypeMaladie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MaladieTest {

    @Test
    void augmenterNiveauTest() {
        Maladie pec = new Maladie(TypeMaladie.PEC);
        pec.augmenterNiveau(3);
        assertEquals(3, pec.getNiveauActuel());
    }

    @Test
    void diminuerNiveauTest() {
        Maladie pec = new Maladie(TypeMaladie.PEC);
        pec.augmenterNiveau(3);
        pec.diminuerNiveau(2);
        assertEquals(1, pec.getNiveauActuel());
    }

    @Test
    void estLetaleTest() {
        Maladie pec = new Maladie(TypeMaladie.PEC);
        assertFalse(pec.estLetal());
        pec.augmenterNiveau(6);
        assertTrue(pec.estLetal());
    }
}

