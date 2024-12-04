package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.Triage;

/**
 * Classe représentant un Orque, une sous-classe de {@link Creature}.
 * <p>
 * Les Orcs sont des créatures robustes avec des comportements influencés par leur entourage. Ils implémentent
 * l'interface {@link Triage}, ce qui affecte leur moral en fonction de la solidarité avec les autres créatures.
 * </p>
 * <p>
 * Par défaut, un Orque commence avec un moral modéré (60), et comme les Hommes-Bêtes, ils sont également
 * contagieux, pouvant transmettre des maladies aux autres créatures.
 * </p>
 */
public class Orque extends Creature implements Triage {

    /**
     * Constructeur de la classe `Orque`.
     *
     * @param nom   le nom de l'Orque.
     * @param sexe  le sexe de l'Orque ('M', 'F', etc.).
     * @param poids le poids de l'Orque (en kilogrammes).
     * @param taille la taille de l'Orque (en centimètres).
     * @param age   l'âge de l'Orque (en années).
     */
    public Orque(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);

        // Initialise le moral spécifique aux Orcs (60).
        setMoral(60);
    }

    /**
     * Indique si l'Orque est contagieux.
     * <p>
     * Cette méthode retourne toujours {@code true}, ce qui signifie que l'Orque peut transmettre des maladies.
     * </p>
     *
     * @return {@code true} car l'Orque est contagieux.
     */
    @Override
    public boolean isContagieuse() {
        return true;
    }
}
