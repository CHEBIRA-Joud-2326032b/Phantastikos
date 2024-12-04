package org.phantastikos.structures.hopital.services;

import org.phantastikos.entite.creatures.Creature;

/**
 * Représente un centre de quarantaine dans un hôpital, un type de service médical destiné à isoler les créatures contagieuses.
 * Ce service médical possède un niveau d'isolation, et permet l'ajout de créatures contagieuses uniquement.
 */
public class CentreQuarantaine extends ServiceMedical {

    /**
     * Indique si le centre de quarantaine est activé ou non pour l'isolation des créatures.
     */
    private boolean isolation;

    /**
     * Constructeur pour créer un centre de quarantaine avec un nom, une superficie, une capacité maximale, un budget et un statut d'isolation.
     *
     * @param nom Le nom du centre de quarantaine.
     * @param superficie La superficie du centre de quarantaine.
     * @param capaciteMax La capacité maximale d'accueil du centre de quarantaine.
     * @param budget Le budget alloué au centre de quarantaine.
     * @param isolation Le statut d'isolation du centre (vrai si isolation activée, faux sinon).
     */
    public CentreQuarantaine(String nom, double superficie, int capaciteMax, Budget budget, boolean isolation) {
        super(nom, superficie, capaciteMax, budget);
        this.isolation = isolation;
    }

    /**
     * Retourne l'état de l'isolation dans le centre de quarantaine.
     *
     * @return `true` si l'isolation est activée, `false` sinon.
     */
    public boolean isIsolation() {
        return isolation;
    }

    /**
     * Modifie l'état de l'isolation du centre de quarantaine.
     *
     * @param isolation Le nouveau statut d'isolation (vrai pour activer l'isolation, faux pour la désactiver).
     */
    public void setIsolation(boolean isolation) {
        this.isolation = isolation;
    }

    /**
     * Ajoute une créature au centre de quarantaine.
     * La créature ne peut être ajoutée que si elle est contagieuse.
     *
     * @param creature La créature à ajouter.
     * @throws IllegalArgumentException Si la créature n'est pas contagieuse, une exception est lancée.
     */
    @Override
    public void ajouterCreature(Creature creature) {
        if (!creature.isContagieuse()) {
            throw new IllegalArgumentException("Seules les créatures contagieuses peuvent être ajoutées à un centre de quarantaine.");
        }
        super.ajouterCreature(creature);
    }

    /**
     * Affiche les détails du centre de quarantaine, y compris son état d'isolation.
     *
     * @return Une chaîne de caractères contenant les détails du centre, y compris l'état d'isolation.
     */
    @Override
    public String afficherDetails() {
        StringBuilder details = new StringBuilder(super.afficherDetails());
        details.append("Isolation : ").append(isolation ? "Activée" : "Désactivée").append("\n");
        return details.toString();
    }
}
