package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.Vip;

/**
 * Classe représentant un Elfe, une sous-classe de {@link Creature}.
 * <p>
 * Les Elfes sont des créatures spéciales caractérisées par un moral élevé par défaut (80).
 * Ils implémentent le comportement {@link Vip}, ce qui signifie qu'ils ont des attentes
 * spécifiques et que leur moral peut diminuer rapidement lorsqu'ils attendent trop longtemps.
 */
public class Elfe extends Creature implements Vip {

    /**
     * Constructeur de la classe `Elfe`.
     *
     * @param nom   le nom de l'elfe.
     * @param sexe  le sexe de l'elfe ('M', 'F', etc.).
     * @param poids le poids de l'elfe (en kilogrammes).
     * @param taille la taille de l'elfe (en centimètres).
     * @param age   l'âge de l'elfe (en années).
     */
    public Elfe(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);

        // Initialise le moral élevé spécifique aux Elfes.
        setMoral(80);
    }
}
