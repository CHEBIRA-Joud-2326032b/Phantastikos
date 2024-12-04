package org.phantastikos.utils;

import java.util.Calendar;
import java.util.Random;

/**
 * Cette classe génère des noms aléatoires composés de voyelles et de consonnes.
 * Les noms générés suivent un modèle où les positions impaires sont des voyelles et les positions paires des consonnes.
 * La longueur des noms générés peut être définie à la création de l'instance, avec une longueur par défaut de 7 caractères si la valeur n'est pas valide.
 */
public class NameGenerator {

    /**
     * La différence entre les lettres 'A' et 'Z' dans l'alphabet (25 caractères).
     */
    private static final int diffBetweenAtoZ = 25;

    /**
     * La valeur ASCII du caractère 'a'.
     */
    private static final int charValueOfa = 97;

    /**
     * Le dernier nom généré, utilisé pour éviter la répétition du même nom.
     */
    private String lastGeneratedName = "";

    /**
     * La longueur du nom à générer.
     */
    int length;

    /**
     * Tableau des voyelles utilisées pour la génération de noms.
     */
    char[] vowels = {
            'a', 'e', 'i', 'o', 'u'
    };

    /**
     * Constructeur pour initialiser le générateur de noms avec une longueur spécifiée.
     * Si la longueur spécifiée est inférieure à 5 ou supérieure à 10, elle sera fixée à 7.
     *
     * @param lengthOfName La longueur du nom à générer. Doit être comprise entre 5 et 10 inclus.
     */
    public NameGenerator(int lengthOfName) {
        if (lengthOfName < 5 || lengthOfName > 10) {
            lengthOfName = 7;
        }

        this.length = lengthOfName;
    }

    /**
     * Génère un nom aléatoire en suivant les règles suivantes :
     * - Les positions impaires (1, 3, 5, etc.) contiennent des voyelles.
     * - Les positions paires (2, 4, 6, etc.) contiennent des consonnes.
     *
     * Le nom commence par une majuscule.
     *
     * @return Le nom généré.
     */
    public String getName() {
        for (;;) {
            Random randomNumberGenerator = new Random(Calendar.getInstance()
                    .getTimeInMillis());

            char[] nameInCharArray = new char[length];

            // Génère le nom caractère par caractère
            for (int i = 0; i < length; i++) {
                if (positionIsOdd(i)) {
                    nameInCharArray[i] = getVowel(randomNumberGenerator);  // Position impaire : voyelle
                } else {
                    nameInCharArray[i] = getConsonant(randomNumberGenerator);  // Position paire : consonne
                }
            }

            // Met la première lettre en majuscule
            nameInCharArray[0] = (char) Character
                    .toUpperCase(nameInCharArray[0]);

            String currentGeneratedName = new String(nameInCharArray);

            // Vérifie si le nom généré est différent du dernier
            if (!currentGeneratedName.equals(lastGeneratedName)) {
                lastGeneratedName = currentGeneratedName;
                return currentGeneratedName;
            }
        }
    }

    /**
     * Vérifie si l'index passé en paramètre correspond à une position impaire.
     *
     * @param i L'index à vérifier.
     * @return true si l'index est impair, false sinon.
     */
    private boolean positionIsOdd(int i) {
        return i % 2 == 0;
    }

    /**
     * Génère une consonne aléatoire.
     * La consonne générée ne sera jamais une voyelle.
     *
     * @param randomNumberGenerator Le générateur de nombres aléatoires.
     * @return Une consonne aléatoire.
     */
    private char getConsonant(Random randomNumberGenerator) {
        for (;;) {
            char currentCharacter = (char) (randomNumberGenerator
                    .nextInt(diffBetweenAtoZ) + charValueOfa);
            if (currentCharacter == 'a' || currentCharacter == 'e'
                    || currentCharacter == 'i' || currentCharacter == 'o'
                    || currentCharacter == 'u')
                continue;  // Ignore les voyelles
            else
                return currentCharacter;  // Retourne la consonne générée
        }
    }

    /**
     * Génère une voyelle aléatoire parmi celles définies dans le tableau {@code vowels}.
     *
     * @param randomNumberGenerator Le générateur de nombres aléatoires.
     * @return Une voyelle aléatoire.
     */
    private char getVowel(Random randomNumberGenerator) {
        return vowels[randomNumberGenerator.nextInt(vowels.length)];
    }
}
