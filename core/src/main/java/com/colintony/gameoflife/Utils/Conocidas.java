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
    public static void ociladorMiddleweight(Tablero tablero,int x,int y)
    {
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
