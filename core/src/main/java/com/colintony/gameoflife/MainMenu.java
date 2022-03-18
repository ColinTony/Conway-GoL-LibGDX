package com.colintony.gameoflife;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.colintony.gameoflife.Screens.GoL;
import com.colintony.gameoflife.Screens.MenuInicio;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainMenu extends Game {

    private SpriteBatch batch;
    private MenuInicio menuInicio;
    //private GameOfLife gameOfLife;
    private GoL gameOfLife;
    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.gameOfLife = new GoL(this);
        //this.menuInicio = new MenuInicio(this);
        this.setScreen(this.gameOfLife);
    }



    // getSpriteBatch
    public SpriteBatch getSpriteBatch()
    {
        return this.batch;
    }

    @Override
    public void dispose() {
        super.dispose();
        this.batch = batch;
        this.screen.dispose();
    }
}
