package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.colintony.gameoflife.Models.Position;

public class Menu extends Actor
{
    // musica
    private Music music;
    // sprites y texturas
    private Texture flecha;
    private Sprite sprite;
    private Position posFlecha;
    private Position initFlecha;
    private int seleccion;

    // Texto
    private BitmapFont bitMapFont;
    private Position posTexto;
    private String opcionesMenu[];

    public Menu()
    {
        // musica
        this.music = Gdx.audio.newMusic(Gdx.files.internal("menu8bit.mp3"));
        // menu
        this.bitMapFont = new BitmapFont(Gdx.files.internal("fontData.fnt"),
                Gdx.files.internal("fontData.png"),false);
        this.posTexto = new Position((ConfigGame.WIDTH_PANTALLA/2)-300,(ConfigGame.HEIGTH_PANTALLA/2)+200);
        this.posFlecha = new Position(this.posTexto.getX()-100,this.posTexto.getY()-25);
        this.initFlecha = new Position(this.posTexto.getX()-100,this.posTexto.getY()-25);
        // texto y seleccion
        this.opcionesMenu = new String[3];
        this.opcionesMenu[0] = "Conway - Game Of Life";
        this.opcionesMenu[1] = "Automatas 2D";
        this.opcionesMenu[2] = "Salir";
        this.seleccion = 0;

        // Felcha de seleccion
        this.flecha = new Texture(Gdx.files.internal("flecha.png"));
        this.sprite = new Sprite(this.flecha);
        this.sprite.setSize(30f,30f);
        this.sprite.setPosition(this.posFlecha.getX(),this.posFlecha.getY());

        // iniciando musica
        this.music.setLooping(true);
        this.music.setVolume(0.25f);
        this.music.play();

    }

    public void dibujarMenu(SpriteBatch batch)
    {
        for(int i = 0; i<this.opcionesMenu.length; i++)
            bitMapFont.draw(batch,this.opcionesMenu[i],this.posTexto.getX(),this.posTexto.getY()-(i*50));

        this.sprite.draw(batch);
    }


    public void moverFlecha(boolean isArriba)
    {
        if(isArriba)
        {

            this.sprite.setPosition(this.sprite.getX(),this.sprite.getY()+50);
            this.seleccion--;
        }else
        {
            this.sprite.setPosition(this.sprite.getX(),this.sprite.getY()-50);
            this.seleccion++;
        }

        if(((this.opcionesMenu.length) <= this.seleccion) || this.seleccion < 0)
        {
            this.sprite.setPosition(this.initFlecha.getX(),this.initFlecha.getY());
            this.seleccion = 0;
        }
    }
    public int retornarSeleccion()
    {
        return this.seleccion;
    }

    public void dispose()
    {
        this.music.dispose();
        this.bitMapFont.dispose();
        this.flecha.dispose();
    }
}
