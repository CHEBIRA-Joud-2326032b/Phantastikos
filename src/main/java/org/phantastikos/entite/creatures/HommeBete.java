package org.phantastikos.entite.creatures;

import org.phantastikos.entite.creatures.comportements.Triage;

/**
 * Classe représentant un Homme-Bête, une sous-classe de {@link Creature}.
 * <p>
 * Les Hommes-Bêtes sont des créatures avec des comportements spéciaux liés à la solidarité et à la gestion des
 * relations avec les autres créatures. Ils implémentent l'interface {@link Triage}, ce qui signifie qu'ils
 * prennent en compte leur entourage pour déterminer leur moral.
 * </p>
 * <p>
 * Cette classe marque également les Hommes-Bêtes comme étant contagieux, ce qui est un comportement unique pour cette
 * créature.
 * </p>
 */
public class HommeBete extends Creature implements Triage {

    /**
     * Constructeur de la classe `HommeBete`.
     *
     * @param nom   le nom de l'Homme-Bête.
     * @param sexe  le sexe de l'Homme-Bête ('M', 'F', etc.).
     * @param poids le poids de l'Homme-Bête (en kilogrammes).
     * @param taille la taille de l'Homme-Bête (en centimètres).
     * @param age   l'âge de l'Homme-Bête (en années).
     */
    public HommeBete(String nom, char sexe, int poids, int taille, int age) {
        super(nom, sexe, poids, taille, age);

        // Initialise le moral élevé spécifique aux Hommes-Bêtes.
        setMoral(80);
    }

    /**
     * Indique si l'Homme-Bête est contagieux.
     * <p>
     * Cette méthode retourne toujours {@code true}, signifiant que l'Homme-Bête peut transmettre des maladies.
     * </p>
     *
     * @return {@code true} car l'Homme-Bête est contagieux.
     */
    @Override
    public boolean isContagieuse() {
        return true;
    }
}
