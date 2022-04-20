package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.colintony.gameoflife.Models.Position;

import javax.xml.crypto.Data;

public class ScreenInfo {
    private boolean mostrarControles;
    private boolean modeBorders;
    private boolean modeCheckData;
    private BitmapFont bitMapFont;
    private Position posController;
    private Position posInfoCell;
    private int conocida;
    private String [] figurasConocidas;

    public ScreenInfo()
    {
        this.modeBorders = false;
        this.modeCheckData = true;
        this.posInfoCell = new Position(0,50);
        this.mostrarControles = true;
        this.posController = new Position(5,ConfigGame.HEIGTH_PANTALLA-10);
        this.bitMapFont = new BitmapFont();
        this.conocida = 0;
        this.figurasConocidas = new String[]{"Celula",
                "Nave - Middleweight",
                "Nave - Glider",
                "Ocilador - Kok's Galaxy",
                "Ocilador - Clock",
                "Config - Acron"};
    }
    public void dibujar(SpriteBatch batch)
    {
        String dataInfo = this.modeCheckData ? "Mode check: Activado" : "Mode check: Desactivado";
        String INSTRUCCIONES = " Zoom + : W | " +
                "Zoom - : S | " + "Zoom max: C | "  + "Zoom min: Z | " +
                "Pause: P | Reanudar: R | Derecha: Rigth | Izquierda: Left | " +
                "Arriba: UP | Abajo: Down | Ocultar controles: D | Siguiente gen: N |"+
                "Debug: Q | Cambiar color Celula: K | Cambiar color fondo: J | Reset figura colocar: space \n\n";
        INSTRUCCIONES += "| Guardar configuracion : G | Cargar configuracion: F | Ver grafica configuraciones: V | Ver grafica celulas vivas: B"+
        "| Mode checkData: M ";
        INSTRUCCIONES += "| Cambiar figura colocar: I - ";

        if(conocida < this.figurasConocidas.length)
            INSTRUCCIONES+=this.figurasConocidas[conocida];
        else{
            this.conocida = 0;
            INSTRUCCIONES+=this.figurasConocidas[conocida];
        }

        
        String CELULAS_VIVAS = " Celulas vivas: ";
        String CELULAR_MUERTAS=" Celulas muertas: ";
        String GENERACION = "GENERACION ACTUAL: ";

        CELULAR_MUERTAS += DataInfo.celulasMuertas;
        CELULAS_VIVAS += DataInfo.celulasVivas;
        GENERACION += DataInfo.generacion;

        String INFO = CELULAR_MUERTAS + " ---- " + CELULAS_VIVAS +" ---- "+ GENERACION;
        INFO += "\t\t        " + dataInfo;
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

    public boolean isModeBorders() {
        return modeBorders;
    }

    public void setModeBorders(boolean modeBorders) {
        this.modeBorders = modeBorders;
    }

    public int getConocida() {
        return conocida;
    }

    public void setConocida(int conocida) {
        this.conocida = conocida;
    }

    public String[] getFigurasConocidas() {
        return figurasConocidas;
    }

    public void setFigurasConocidas(String[] figurasConocidas) {
        this.figurasConocidas = figurasConocidas;
    }

    public boolean isModeCheckData() {
        return modeCheckData;
    }

    public void setModeCheckData(boolean modeCheckData) {
        this.modeCheckData = modeCheckData;
    }
}
