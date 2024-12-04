package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.Vip;

/**
 * Classe représentant un Nain, une sous-classe de {@link Creature}.
 * <p>
 * Les Nains sont des créatures réputées pour leur robustesse et leur moral élevé. Ils implémentent
 * l'interface {@link Vip}, ce qui signifie qu'ils ont un comportement spécifique concernant l'attente
 * et l'impact sur leur moral.
 * </p>
 * <p>
 * Par défaut, un Nain commence avec un moral très élevé (95), ce qui le rend plus résistant au stress
 * ou aux situations négatives.
 * </p>
 */
public class Nain extends Creature implements Vip {

    /**
     * Constructeur de la classe `Nain`.
     *
     * @param nom   le nom du Nain.
     * @param sexe  le sexe du Nain ('M', 'F', etc.).
     * @param poids le poids du Nain (en kilogrammes).
     * @param taille la taille du Nain (en centimètres).
     * @param age   l'âge du Nain (en années).
     */
    public Nain(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);

        // Initialise le moral élevé spécifique aux Nains.
        setMoral(95);
    }
}
