package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.Contaminant;
import org.phantastikos.entite.creatures.comportements.Demoralisant;
import org.phantastikos.entite.creatures.comportements.Regenerant;
import org.phantastikos.entite.creatures.comportements.Vip;

/**
 * Classe représentant un Vampire, une sous-classe de {@link Creature}.
 * <p>
 * Le Vampire est une créature surnaturelle qui combine plusieurs comportements spéciaux définis par
 * les interfaces {@link Vip}, {@link Demoralisant}, {@link Contaminant}, et {@link Regenerant}.
 * </p>
 * <p>
 * En tant que Vampire, cette créature a un moral exceptionnellement élevé (100), une capacité à démoraliser
 * les autres créatures, à contaminer d'autres créatures avec des maladies et à se régénérer lorsqu'elle est blessée.
 * </p>
 */
public class Vampire extends Creature implements Vip, Demoralisant, Contaminant, Regenerant {

    /**
     * Constructeur de la classe `Vampire`.
     *
     * @param nom   le nom du Vampire.
     * @param sexe  le sexe du Vampire ('M', 'F', etc.).
     * @param poids le poids du Vampire (en kilogrammes).
     * @param taille la taille du Vampire (en centimètres).
     * @param age   l'âge du Vampire (en années).
     */
    public Vampire(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);

        // Initialise le moral élevé spécifique aux Vampires.
        setMoral(100);
    }
}
