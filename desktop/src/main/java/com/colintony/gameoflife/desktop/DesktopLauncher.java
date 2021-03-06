package com.colintony.gameoflife.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.colintony.gameoflife.MainMenu;
import com.colintony.gameoflife.Utils.ConfigGame;

/** Launches the desktop (LWJGL) application. */
public class DesktopLauncher {
    public static void main(String[] args) {
        createApplication();
    }

    private static LwjglApplication createApplication() {
        return new LwjglApplication(new MainMenu(), getDefaultConfiguration());
    }

    private static LwjglApplicationConfiguration getDefaultConfiguration() {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = "sistemas complejos - Colin Heredia Luis Antonio";
        configuration.width = ConfigGame.WIDTH_PANTALLA;
        configuration.height = ConfigGame.HEIGTH_PANTALLA;
        configuration.foregroundFPS = 100;
        return configuration;
    }
}