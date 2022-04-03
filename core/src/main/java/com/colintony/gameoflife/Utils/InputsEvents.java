package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.colintony.gameoflife.Models.Tablero;

public class InputsEvents {


    // ------- Fin REvision taroide
    /*
     INOPUTSSS CAMERA
     */
    public static void inputsCamera(ScreenInfo screenInfo, OrthographicCamera camera)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q))
            screenInfo.setModeBorders(!screenInfo.isModeBorders());

        if(Gdx.input.isKeyJustPressed(Input.Keys.D))
            screenInfo.setMostrarControles(!screenInfo.isMostrarControles());

        if(Gdx.input.isKeyPressed(Input.Keys.Z))
            camera.zoom = 1.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.C))
            camera.zoom = 0.080f;

        if(Gdx.input.isKeyPressed(Input.Keys.W))
            if(camera.zoom > 0.080f)
                camera.zoom -= 0.008f;

        if(Gdx.input.isKeyPressed(Input.Keys.S))
            if(camera.zoom <= 1.0)
                camera.zoom += 0.008f;

        // MOVIMIENTOS
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(6, 0);
            screenInfo.getPosInfoCell().setX(screenInfo.getPosInfoCell().getX()+6);
            screenInfo.getPosController().setX(screenInfo.getPosController().getX()+6);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-6, 0);
            screenInfo.getPosInfoCell().setX(screenInfo.getPosInfoCell().getX()-6);
            screenInfo.getPosController().setX(screenInfo.getPosController().getX()-6);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -6);
            screenInfo.getPosInfoCell().setY(screenInfo.getPosInfoCell().getY()-6);
            screenInfo.getPosController().setY(screenInfo.getPosController().getY()-6);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, 6);
            screenInfo.getPosInfoCell().setY(screenInfo.getPosInfoCell().getY()+6);
            screenInfo.getPosController().setY(screenInfo.getPosController().getY()+6);
        }


    }
    /*
        Con esta funcion se hacen los calculos para detectar en donde toco el grid
        asi modificar el valor de esa celda.
     */
    public static void inputCelulas(OrthographicCamera camera, Vector2 dimensions, Tablero tablero)
    {
        if(Gdx.input.justTouched())
        {
            Vector3 mousePos = camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY() , 0));
            int x = Gdx.input.getDeltaX((int) mousePos.x);
            int y = Gdx.input.getDeltaY((int) mousePos.y);

            int deltaX = Math.round (mousePos.x % dimensions.x);
            int deltaY = Math.round (mousePos.y % dimensions.y);

            if( deltaX != 0 || deltaY !=0)
                mousePos.x -=deltaX;

            if( (y % dimensions.y) == 0)
                mousePos.y-=deltaY;


            int finalX = Math.round(mousePos.x/dimensions.x);
            int finalY = Math.round (mousePos.y/dimensions.y);
            try
            {
                if(tablero.getGrid()[finalX][finalY])
                    Gdx.app.postRunnable(()->tablero.getGrid()[finalX][finalY] = false);
                else
                    Gdx.app.postRunnable(()->tablero.getGrid()[finalX][finalY] = true);
            }catch (IndexOutOfBoundsException e)
            {
                System.out.println(e);
            }

        }
    }
    /*
        Input de configuraciones conocidas
     */
    public static void inputConocidas(OrthographicCamera camera, Vector2 dimensions, Tablero tablero)
    {
        if(Gdx.input.justTouched())
        {
            Vector3 mousePos = camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY() , 0));
            int x = Gdx.input.getDeltaX((int) mousePos.x);
            int y = Gdx.input.getDeltaY((int) mousePos.y);

            int deltaX = Math.round (mousePos.x % dimensions.x);
            int deltaY = Math.round (mousePos.y % dimensions.y);

            if( deltaX != 0 || deltaY !=0)
                mousePos.x -=deltaX;

            if( (y % dimensions.y) == 0)
                mousePos.y-=deltaY;


            int finalX = Math.round(mousePos.x/dimensions.x);
            int finalY = Math.round (mousePos.y/dimensions.y);
            try
            {
                if(tablero.getGrid()[finalX][finalY])
                    Gdx.app.postRunnable(()->tablero.getGrid()[finalX][finalY] = false);
                else
                    Gdx.app.postRunnable(()->tablero.getGrid()[finalX][finalY] = true);
            }catch (IndexOutOfBoundsException e)
            {
                System.out.println(e);
            }

        }
    }
}
