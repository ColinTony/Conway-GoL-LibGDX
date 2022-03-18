package com.colintony.gameoflife.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Celula extends Actor {
    // finals
    public final int NEXT = 5;
    public final int WIDTH_SIZE = 5;
    public final int HEIGTH_SIZE = 5;
    // posicion de la celula
    private Position position;
    // estado y celula
    private boolean isAlive;
    private Texture celda;


    public Celula(int x, int y,boolean isAlive)
    {
        this.position = new Position(x,y);
        this.isAlive = isAlive;
        this.imageInit();
    }

    public Celula(Position position,boolean isAlive)
    {
        this.position =position;
        this.isAlive = isAlive;
        this.imageInit();
    }
    private void imageInit()
    {
        if(isAlive)
            this.celda = new Texture(Gdx.files.internal("blanca.png"));
        else
            this.celda = new Texture(Gdx.files.internal("negra.png"));
    }


    // cambiar estado
    public void cambiarEstado()
    {
        if(this.isAlive)
        {
            this.celda = new Texture(Gdx.files.internal("negra.png"));
            this.isAlive = false;
        }else
        {
            this.celda = new Texture(Gdx.files.internal("blanca.png"));
            this.isAlive = true;
        }
    }

    // Checar si existe una celula
    public boolean checkExist(int x, int y)
    {
        boolean existe = false;

        if(x == this.position.getX() && y == this.position.getY())
            existe = true;

        return existe;
    }
    public void render(SpriteBatch batch)
    {
        batch.draw(this.celda,position.getX(),this.position.getY(),WIDTH_SIZE,HEIGTH_SIZE);
    }

    public void nextGen()
    {

    }
    // GETTERS AND SETTERS

    public int getXPos() {
        return this.position.getX();
    }

    public void setXPos(int x) {
        this.position.setX(x);
    }

    public int getYPos() {
        return this.position.getY();
    }

    public void setYPos(int y) {
        this.position.setY(y);
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Texture getCelda() {
        return celda;
    }

    public void setCelda(Texture celda) {
        this.celda = celda;
    }
}
