package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.Vip;

/**
 * Classe représentant un Reptilien, une sous-classe de {@link Creature}.
 * <p>
 * Les Reptiliens sont des créatures dotées de comportements particuliers liés à leur psychologie et à leur moral.
 * Ils implémentent l'interface {@link Vip}, ce qui implique un comportement spécifique vis-à-vis de l'attente
 * et de l'impact de cette attente sur leur moral.
 * </p>
 * <p>
 * Par défaut, un Reptilien commence avec un moral de 80, ce qui le rend plutôt stable émotionnellement,
 * mais il peut être affecté négativement par l'attente prolongée.
 * </p>
 */
public class Reptilien extends Creature implements Vip {

    /**
     * Constructeur de la classe `Reptilien`.
     *
     * @param nom   le nom du Reptilien.
     * @param sexe  le sexe du Reptilien ('M', 'F', etc.).
     * @param poids le poids du Reptilien (en kilogrammes).
     * @param taille la taille du Reptilien (en centimètres).
     * @param age   l'âge du Reptilien (en années).
     */
    public Reptilien(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);

        // Initialise le moral spécifique aux Reptiliens.
        setMoral(80);
    }
}
