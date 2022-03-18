package com.colintony.gameoflife.Screens;

import com.badlogic.gdx.Screen;
import com.colintony.gameoflife.MainMenu;

public class PantallaAbstract implements Screen {
    protected MainMenu game;

    public PantallaAbstract(MainMenu game)
    {
        this.game = game;
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
