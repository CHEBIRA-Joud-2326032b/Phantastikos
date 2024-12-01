package org.phantastikos.vues;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import org.jetbrains.annotations.NotNull;

public class ScenePerso extends SceneFactory {

    @NotNull
    @Override
    public FXGLMenu newMainMenu() {
        return new MenuPrincipal();
    }

}