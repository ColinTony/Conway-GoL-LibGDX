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
        this.posInfoCell = new Position(0,50);
        this.mostrarControles = true;
        this.posController = new Position(5,ConfigGame.HEIGTH_PANTALLA-10);
        this.bitMapFont = new BitmapFont();
    }
    public void dibujar(SpriteBatch batch)
    {
        String INSTRUCCIONES = " Zoom + : W | " +
                "Zoom - : S | " + "Zoom max: C | "  + "Zoom min: Z | " +
                "Pause: P | Reanudar: R | Derecha: Rigth | Izquierda: Left | " +
                "Arriba: UP | Abajo: Down | Ocultar controles: D | Siguiente gen: N |";

        String CELULAS_VIVAS = " Celulas vivas: ";
        String CELULAR_MUERTAS=" Celulas muertas: ";
        String GENERACION = "GENERACION ACTUAL: ";

        CELULAR_MUERTAS += DataInfo.celulasMuertas;
        CELULAS_VIVAS += DataInfo.celulasVivas;
        GENERACION += DataInfo.generacion;

        String INFO = CELULAR_MUERTAS + " ---- " + CELULAS_VIVAS +" ---- "+ GENERACION;
        bitMapFont.setColor(Color.RED);
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
