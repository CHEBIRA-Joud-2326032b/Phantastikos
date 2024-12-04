package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.Triage;

/**
 * Classe représentant un Zombie, une sous-classe de {@link Creature}.
 * <p>
 * Le Zombie est une créature réanimée, caractérisée par un moral très bas et des comportements influencés par
 * son état physique et son entourage. Il implémente l'interface {@link Triage}, ce qui affecte son moral
 * en fonction de la solidarité avec les autres créatures.
 * </p>
 * <p>
 * Par défaut, un Zombie commence avec un moral très faible (10), ce qui reflète son état de dégradation. Il
 * possède également une capacité de régénération, ce qui le rend capable de se réparer progressivement.
 * </p>
 */
public class Zombie extends Creature implements Triage {

    /**
     * Constructeur de la classe `Zombie`.
     *
     * @param nom   le nom du Zombie.
     * @param sexe  le sexe du Zombie ('M', 'F', etc.).
     * @param poids le poids du Zombie (en kilogrammes).
     * @param taille la taille du Zombie (en centimètres).
     * @param age   l'âge du Zombie (en années).
     */
    public Zombie(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);

        // Initialise le moral spécifique aux Zombies (10).
        setMoral(10);
    }

    /**
     * Indique si le Zombie est régénérant.
     * <p>
     * Cette méthode retourne toujours {@code true}, ce qui signifie que le Zombie peut se régénérer au fil du temps,
     * malgré son état de décomposition.
     * </p>
     *
     * @return {@code true} car le Zombie possède une capacité de régénération.
     */
    @Override
    public boolean isRegenerante() {
        return true;
    }
}
