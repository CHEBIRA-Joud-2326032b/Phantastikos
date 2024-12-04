package org.phantastikos.structures.hopital.services;

import org.phantastikos.entite.creatures.Creature;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un service médical dans un hôpital. Ce service est caractérisé par un nom, une superficie,
 * une capacité maximale d'accueil, un budget et une liste de créatures qui y sont prises en charge.
 *
 * Les services médicaux peuvent accueillir des créatures, les gérer et afficher des détails sur leurs caractéristiques.
 */
public class ServiceMedical {

    /**
     * Le nom du service médical.
     */
    private String nom;

    /**
     * La superficie du service médical en mètres carrés.
     */
    private double superficie;

    /**
     * La capacité maximale d'accueil de créatures dans ce service médical.
     */
    private int capaciteMax;

    /**
     * Le budget alloué au service médical.
     */
    private Budget budget;

    /**
     * La liste des créatures actuellement prises en charge dans le service médical.
     */
    private List<Creature> creatures;

    /**
     * Constructeur pour initialiser un service médical avec son nom, superficie, capacité maximale, budget et une liste vide de créatures.
     *
     * @param nom Le nom du service médical.
     * @param superficie La superficie du service médical.
     * @param capaciteMax La capacité maximale d'accueil du service.
     * @param budget Le budget alloué au service.
     */
    public ServiceMedical(String nom, double superficie, int capaciteMax, Budget budget) {
        this.nom = nom;
        this.superficie = superficie;
        this.capaciteMax = capaciteMax;
        this.budget = budget;
        this.creatures = new ArrayList<>();
    }

    /**
     * Retourne le nom du service médical.
     *
     * @return Le nom du service.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le budget alloué au service médical.
     *
     * @return Le budget du service.
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Modifie le budget du service médical.
     *
     * @param budget Le nouveau budget à attribuer au service.
     */
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    /**
     * Retourne la liste des créatures prises en charge dans le service médical.
     *
     * @return La liste des créatures.
     */
    public List<Creature> getCreatures() {
        return creatures;
    }

    /**
     * Ajoute une créature au service médical. Si la capacité maximale est atteinte, la créature ne sera pas ajoutée.
     *
     * @param creature La créature à ajouter au service.
     */
    public void ajouterCreature(Creature creature) {
        if (creatures.size() < capaciteMax) {
            creatures.add(creature);
            creature.setResidence(this);
        } else {
            System.out.println("Impossible d'ajouter la créature : capacité maximale atteinte !");
        }
    }

    /**
     * Enlève une créature du service médical.
     *
     * @param creature La créature à enlever.
     */
    public void enleverCreature(Creature creature) {
        creatures.remove(creature);
        creature.setResidence(null);
    }

    /**
     * Affiche les détails du service médical, y compris son nom, son budget et la liste des créatures prises en charge.
     *
     * @return Une chaîne de caractères contenant les détails du service médical.
     */
    public String afficherDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Service Médical : ").append(nom).append("\n");
        details.append("Budget : ").append(budget).append("\n");
        details.append("Liste des créatures :\n");
        for (Creature creature : creatures) {
            details.append("- ").append(creature.getNom()).append("\n");
        }
        return details.toString();
    }
}
