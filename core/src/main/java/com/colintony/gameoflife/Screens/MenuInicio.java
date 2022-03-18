package com.colintony.gameoflife.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.colintony.gameoflife.MainMenu;
import com.colintony.gameoflife.Utils.Menu;

public class MenuInicio extends PantallaAbstract {

    private SpriteBatch batch;
    private Menu menu;

    public MenuInicio(MainMenu game) {
        super(game);
        this.batch = this.game.getSpriteBatch();
        this.menu = new Menu();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.begin();
        this.menu.dibujarMenu(batch);
        this.batch.end();

        inputsMenu();
    }

    public void inputsMenu() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            this.menu.moverFlecha(false);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            this.menu.moverFlecha(true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            int select = this.menu.retornarSeleccion();
            System.out.println(select);
            switch (select)
            {
                case 0:
                    // practica Conway GameOfLife
                    // this.game.setScreen(practicaHormiga);
                    break;

                case 1:
                    // salir
                    break;
                case 2:
                    // salir
                    break;

                default:
                    System.out.println("No deberia haber errores para llegar aqui");
                    break;
            }
        }
    }
}
