package org.phantastikos.entite.creatures;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class CreatureFactory {

    private static final List<Class<? extends Creature>> TYPES_CREATURES = List.of(
            Elfe.class, Nain.class, HommeBete.class, Vampire.class, Zombie.class, Orque.class, Reptilien.class
    );

    public static Creature creerCreatureAleatoire() {
        Random rand = new Random();

        // Sélectionne un type de créature aléatoire parmi les sous-classes
        Class<? extends Creature> typeCreature = TYPES_CREATURES.get(rand.nextInt(TYPES_CREATURES.size()));

        // Génère des attributs aléatoires
        String nom = typeCreature.getSimpleName() + "_" + rand.nextInt(1000);
        char sexe = rand.nextBoolean() ? 'M' : 'F';
        int poids = rand.nextInt(100) + 50; // 50 à 150 kg
        int taille = rand.nextInt(50) + 150; // 150 à 200 cm
        int age = rand.nextInt(100) + 1; // 1 à 100 ans

        try {
            // Instancie la créature via son constructeur
            return typeCreature.getConstructor(String.class, char.class, int.class, int.class, int.class)
                    .newInstance(nom, sexe, poids, taille, age);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la créature : " + e.getMessage(), e);
        }
    }
}
