package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.colintony.gameoflife.Models.Tablero;

public class Conocidas {

    /*
        ---------*---------
        ----------*--------
        -----*----*--------
        ------*****--------
     */
    public static void naveMiddleweight(Tablero tablero,int x,int y)
    {
        try{
            boolean grid_conocidas[][] = tablero.getGrid();

            grid_conocidas[x][y] = true;
            grid_conocidas[x+1][y-1] = true;
            grid_conocidas[x+2][y-1] = true;
            grid_conocidas[x+3][y-1] = true;
            grid_conocidas[x+4][y-1] = true;
            grid_conocidas[x+5][y-1] = true;
            grid_conocidas[x+5][y] = true;
            grid_conocidas[x+5][y+1] = true;
            grid_conocidas[x+4][y+2] = true;

            tablero.setGrid(grid_conocidas);
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println("Ignorando errror");
        }

    }
    /*
        -------------------
        -------*-----------
        --------*----------
        ------***----------
     */
    public static void naveGlider(Tablero tablero,int x,int y)
    {
        try{
            boolean grid_conocidas[][] = tablero.getGrid();

            grid_conocidas[x][y] = true;
            grid_conocidas[x+1][y] = true;
            grid_conocidas[x+2][y] = true;
            grid_conocidas[x+2][y+1]=true;
            grid_conocidas[x+1][y+2]=true;

            tablero.setGrid(grid_conocidas);
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println("Ignorando error");
        }

    }
    /*
        -------------------
        --------*----------
        ---------**--------
        -------**----------
        ---------*---------
        -------------------
     */
    public static void ociladorClock(Tablero tablero,int x,int y)
    {
        try
        {
            boolean grid_conocidas[][] = tablero.getGrid();

            grid_conocidas[x][y] = true;
            grid_conocidas[x+1][y-1] = true;
            grid_conocidas[x+2][y-1] = true;
            grid_conocidas[x][y-2] = true;
            grid_conocidas[x-1][y-2] = true;
            grid_conocidas[x+1][y-3] = true;


            tablero.setGrid(grid_conocidas);
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println("Ignorando error");
        }
    }

    /*
        -------------------
        --------**-******--
        --------**-******--
        --------**---------
        --------**-----**--
        --------**-----**--
        --------**-----**--
        ---------------**--
        --------******-**--
        --------******-**--
        -------------------
        -------------------
     */
    public static void ociladorGalaxy(Tablero tablero,int x,int y)
    {
        try
        {
            boolean grid_conocidas[][] = tablero.getGrid();

            for(int i=0; i<2; i++)
            {
                for(int j = 0; j<6; j++)
                {
                    grid_conocidas[x+i][y-j] = true;
                    grid_conocidas[x+7+i][y-3-j]=true;
                }
            }

            for(int i=0; i<6; i++)
            {
                for(int j = 0; j<2; j++)
                {
                    grid_conocidas[x+3+i][y-j] = true;
                    grid_conocidas[x+i][y-7-j] = true;
                }
            }

            tablero.setGrid(grid_conocidas);
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println("Ignorando error");
        }
    }
    /*
        -------------------
        -------*-----------
        ---------*--------
        ------**--***------
        -------------------
        -------------------
     */
    public static void acron(Tablero tablero,int x , int y)
    {
        try
        {
            boolean grid_conocidas[][] = tablero.getGrid();
            grid_conocidas[x][y] = true;
            grid_conocidas[x][y-2] = true;
            grid_conocidas[x-1][y-2] = true;
            grid_conocidas[x+3][y-2] = true;
            grid_conocidas[x+4][y-2] = true;
            grid_conocidas[x+5][y-2] = true;
            grid_conocidas[x+2][y-1] = true;

            tablero.setGrid(grid_conocidas);
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println("Ignorando error");
        }
    }
    public static void onlyCelula(Tablero tablero,int x,int y)
    {
        try
        {
            if(tablero.getGrid()[x][y])
                Gdx.app.postRunnable(()->tablero.getGrid()[x][y] = false);
            else
                Gdx.app.postRunnable(()->tablero.getGrid()[x][y] = true);
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println(e);
        }
    }
}
