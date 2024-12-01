import org.phantastikos.entite.creatures.Elfe;
import org.phantastikos.entite.creatures.HommeBete;
import org.phantastikos.entite.etats.maladies.Maladie;
import org.phantastikos.entite.etats.maladies.TypeMaladie;
import org.phantastikos.structures.hopital.services.Budget;
import org.phantastikos.structures.hopital.services.ServiceMedical;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {

    @Test
    void attendreTest() {
        ServiceMedical residence = new ServiceMedical("HPP",1000,70, Budget.INEXISTANT);
        HommeBete hommeBete = new HommeBete("Prout", 'Z', 78, 187, 256);
        residence.ajouterCreature(hommeBete);
        hommeBete.attendre();
        assertEquals(70, hommeBete.getMoral());
    }

    @Test
    void hurlerTest() {
        ServiceMedical residence = new ServiceMedical("HPP",800,50, Budget.INEXISTANT);
        HommeBete hommeBete = new HommeBete("Prout", 'Z', 78, 187, 256);
        residence.ajouterCreature(hommeBete);
        hommeBete.setMoral(0);
        hommeBete.attendre();
        assertEquals(1, hommeBete.getCptHurlements());
    }

    @Test
    void sEmporterTest() {
        HommeBete hommeBete = new HommeBete("Prout", 'Z', 78, 187, 256);
        hommeBete.setCptHurlements(3);
        hommeBete.hurler();
        assertTrue(hommeBete.getCptHurlements() >= 3);
    }

    @Test
    void tomberMaladeTest() {
        HommeBete hommeBete = new HommeBete("Prout", 'Z', 78, 187, 256);
        Maladie maladie = new Maladie(TypeMaladie.FOMO);
        hommeBete.tomberMalade(maladie);
        assertTrue(hommeBete.getMaladies().contains(maladie));
    }

    @Test
    void etreSoigneeTest() {
        HommeBete hommeBete = new HommeBete("Prout", 'Z', 78, 187, 256);
        Maladie maladie = new Maladie(TypeMaladie.FOMO);
        hommeBete.ajouterMaladie(maladie);
        hommeBete.etreSoignee(maladie);
        if (!hommeBete.getMaladies().contains(maladie)) {
            assertEquals(120, hommeBete.getMoral());
        } else {
            assertTrue(maladie.getNiveauActuel() < 10);
            assertEquals(100, hommeBete.getMoral());
        }
    }

    @Test
    void trepasserTest() {
        HommeBete hommeBete = new HommeBete("Prout", 'Z', 78, 187, 256);
        hommeBete.setChance(0);
        boolean isDead = hommeBete.trepasser();
        assertTrue(isDead);
        hommeBete.setChance(101);
        isDead = hommeBete.trepasser();
        assertFalse(isDead);
    }

    @Test
    void contaminerTest() {
        ServiceMedical residence = new ServiceMedical("HPP",1800,250, Budget.FAIBLE);
        HommeBete infecteur = new HommeBete("Infecteur", 'M', 80, 180, 30);
        HommeBete cible = new HommeBete("Cible", 'F', 70, 170, 40);

        residence.ajouterCreature(infecteur);
        residence.ajouterCreature(cible);

        Maladie fomo = new Maladie(TypeMaladie.FOMO);
        infecteur.ajouterMaladie(fomo);

        infecteur.contaminer();
        boolean cibleContaminee = cible.getMaladies().stream()
                .anyMatch(m -> m.getType().equals(TypeMaladie.FOMO));

        assertTrue(cibleContaminee);
    }

    @Test
    void vipAttendreTest() {
        Elfe elfe = new Elfe("VIP-Elfe", 'F', 50, 160, 100);
        elfe.attendre();
        elfe.attendre();
        assertEquals(50, elfe.getMoral());
    }

    @Test
    void triageAttendreTest() {
        ServiceMedical residence = new ServiceMedical("HPP",600,70, Budget.MEDIOCRE);
        HommeBete hommeBete1 = new HommeBete("Solitaire", 'M', 60, 170, 50);
        HommeBete hommeBete2 = new HommeBete("Ami", 'F', 70, 180, 60);

        residence.ajouterCreature(hommeBete1);
        residence.ajouterCreature(hommeBete2);

        hommeBete1.attendre();
        assertEquals(75, hommeBete1.getMoral());
    }
}
