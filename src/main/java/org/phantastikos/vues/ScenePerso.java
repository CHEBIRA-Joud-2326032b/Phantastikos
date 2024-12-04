package org.phantastikos.vues;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Factory pour créer la scène du menu principal dans le jeu.
 * Cette classe hérite de {@link SceneFactory} et permet de personnaliser la création de la scène principale.
 * Elle est utilisée pour fournir la scène de menu principal à l'application FXGL.
 *
 * @see MenuPrincipal
 */
public class ScenePerso extends SceneFactory {

    /**
     * Crée et retourne un nouvel objet {@link FXGLMenu} représentant le menu principal du jeu.
     *
     * @return Une instance de {@link MenuPrincipal}, qui est un menu personnalisé pour l'application.
     */
    @NotNull
    @Override
    public FXGLMenu newMainMenu() {
        return new MenuPrincipal();
    }
}
