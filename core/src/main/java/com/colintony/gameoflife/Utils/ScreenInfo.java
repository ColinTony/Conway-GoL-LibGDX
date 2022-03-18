package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.colintony.gameoflife.Models.Position;

import javax.xml.crypto.Data;

public class ScreenInfo {
    private boolean mostrarControles;

    private BitmapFont bitMapFont;
    private Position posController;
    private Position posInfoCell;


    public ScreenInfo()
    {
        this.posInfoCell = new Position(0,100);
        this.mostrarControles = true;
        this.posController = new Position(ConfigGame.WIDTH_PANTALLA-300,ConfigGame.HEIGTH_PANTALLA-150);
        this.bitMapFont = new BitmapFont(Gdx.files.internal("fontData.fnt"),
                Gdx.files.internal("fontData.png"),false);
    }
    public void dibujar(SpriteBatch batch)
    {
        String INSTRUCCIONES = "Zoom + : W \n" +
                "Zoom - : S \n" + "Zoom max: C \n"  + "Zoom min: Z \n" +
                "Pause: P\nReanudar: R\nDerecha: Rigth\nIzquierda: Left\n" +
                "Arriba: UP\nAbajo: Down\nOcultar controles: D";

        String CELULAS_VIVAS = " Celulas vivas: ";
        String CELULAR_MUERTAS=" Celulas muertas: ";

        CELULAR_MUERTAS += DataInfo.celulasMuertas;
        CELULAS_VIVAS += DataInfo.celulasVivas;

        String INFO = CELULAR_MUERTAS + " ---- " + CELULAS_VIVAS;
        bitMapFont.setColor(Color.CYAN);
        bitMapFont.draw(batch,INSTRUCCIONES,this.posController.getX(),this.posController.getY());
        bitMapFont.draw(batch,INFO,this.posInfoCell.getX(),this.posInfoCell.getY());
    }

    public boolean isMostrarControles() {
        return mostrarControles;
    }

    public void setMostrarControles(boolean mostrarControles) {
        this.mostrarControles = mostrarControles;
    }

    public Position getPosController() {
        return posController;
    }

    public void setPosController(Position posController) {
        this.posController = posController;
    }

    public Position getPosInfoCell() {
        return posInfoCell;
    }

    public void setPosInfoCell(Position posInfoCell) {
        this.posInfoCell = posInfoCell;
    }
}
