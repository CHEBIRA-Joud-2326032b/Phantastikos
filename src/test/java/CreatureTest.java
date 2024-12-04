import org.phantastikos.entite.creatures.Elfe;
import org.phantastikos.entite.creatures.HommeBete;
import org.phantastikos.entite.etats.maladies.Maladie;
import org.phantastikos.entite.etats.maladies.TypeMaladie;
import org.phantastikos.structures.hopital.services.Budget;
import org.phantastikos.structures.hopital.services.ServiceMedical;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour les différentes fonctionnalités des créatures.
 * Ces tests valident les comportements des différentes entités et méthodes dans le cadre du jeu.
 */
public class CreatureTest {

    /**
     * Teste la méthode attendre() d'un HommeBete lorsque son moral est déjà normal.
     * Vérifie que le moral reste inchangé après l'attente.
     */
    @Test
    void attendreTest() {
        ServiceMedical residence = new ServiceMedical("HPP",1000,70, Budget.INEXISTANT);
        HommeBete hommeBete = new HommeBete("Prout", 'Z', 78, 187, 256);
        residence.ajouterCreature(hommeBete);
        hommeBete.attendre();
        assertEquals(70, hommeBete.getMoral());
    }

    /**
     * Teste la méthode hurler() d'un HommeBete lorsque son moral est faible.
     * Vérifie que le compteur de hurlements s'incrémente après l'attente.
     */
    @Test
    void hurlerTest() {
        ServiceMedical residence = new ServiceMedical("HPP",800,50, Budget.INEXISTANT);
        HommeBete hommeBete = new HommeBete("Prout", 'Z', 78, 187, 256);
        residence.ajouterCreature(hommeBete);
        hommeBete.setMoral(0);
        hommeBete.attendre();
        assertEquals(1, hommeBete.getCptHurlements());
    }

    /**
     * Teste le comportement d'un HommeBete qui se met en colère après plusieurs hurlements.
     * Vérifie que le nombre de hurlements est bien supérieur ou égal à 3.
     */
    @Test
    void sEmporterTest() {
        HommeBete hommeBete = new HommeBete("Prout", 'Z', 78, 187, 256);
        hommeBete.setCptHurlements(3);
        hommeBete.hurler();
        assertTrue(hommeBete.getCptHurlements() >= 3);
    }

    /**
     * Teste la méthode tomberMalade() d'un HommeBete en l'infectant avec une maladie.
     * Vérifie que la maladie est correctement ajoutée à la liste des maladies de l'HommeBete.
     */
    @Test
    void tomberMaladeTest() {
        HommeBete hommeBete = new HommeBete("Prout", 'Z', 78, 187, 256);
        Maladie maladie = new Maladie(TypeMaladie.FOMO);
        hommeBete.tomberMalade(maladie);
        assertTrue(hommeBete.getMaladies().contains(maladie));
    }

    /**
     * Teste la méthode etreSoignee() pour un HommeBete malade.
     * Vérifie que la maladie est guérie et que le moral de l'HommeBete est correctement modifié.
     */
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

    /**
     * Teste la méthode trepasser() d'un HommeBete avec une chance de survie faible.
     * Vérifie que l'HommeBete meurt lorsque sa chance de survie est nulle.
     * Vérifie qu'il ne meurt pas lorsque la chance de survie est élevée.
     */
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

    /**
     * Teste la méthode contaminer() d'un HommeBete.
     * Vérifie que la maladie est transmise à une autre créature.
     */
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

    /**
     * Teste la méthode attendre() pour un Elfe avec un moral initial élevé.
     * Vérifie que l'attente n'affecte pas le moral.
     */
    @Test
    void vipAttendreTest() {
        Elfe elfe = new Elfe("VIP-Elfe", 'F', 50, 160, 100);
        elfe.attendre();
        elfe.attendre();
        assertEquals(50, elfe.getMoral());
    }

    /**
     * Teste la méthode attendre() pour un HommeBete dans une situation de triage.
     * Vérifie que le moral d'un HommeBete augmente en fonction du service médical.
     */
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
