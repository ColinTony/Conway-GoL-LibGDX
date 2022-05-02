package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.colintony.gameoflife.Models.Tablero;

import javax.xml.crypto.Data;

public class Conocidas {

    public static boolean is100Glider = true;

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

            DataInfo.celulasVivas += 9;
            DataInfo.celulasMuertas -= 9;

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
    public static void naveGliderDIAB(Tablero tablero,int x,int y)
    {
        int xt = x;
        int yt=y;
        if(is100Glider) {
            for (int i = 0; i <= 10; i++)
            {
                x -= 5;
                for(int j =0; j <= 10; j++)
                {
                    try{
                        boolean grid_conocidas[][] = tablero.getGrid();

                        grid_conocidas[x][y] = true;
                        grid_conocidas[x+1][y] = true;
                        grid_conocidas[x+2][y] = true;
                        grid_conocidas[x+2][y+1]=true;
                        grid_conocidas[x+1][y+2]=true;

                        DataInfo.celulasVivas += 5;
                        DataInfo.celulasMuertas -= 5;

                        tablero.setGrid(grid_conocidas);
                    }catch (IndexOutOfBoundsException e)
                    {
                        System.out.println("Ignorando error");
                    }
                    y-=5;
                }
                x = xt;
                y = yt;
                x -= (i*5);

            }
        }else
        {
            try{
                boolean grid_conocidas[][] = tablero.getGrid();

                grid_conocidas[x][y] = true;
                grid_conocidas[x+1][y] = true;
                grid_conocidas[x+2][y] = true;
                grid_conocidas[x+2][y+1]=true;
                grid_conocidas[x+1][y+2]=true;

                DataInfo.celulasVivas += 5;
                DataInfo.celulasMuertas -= 5;

                tablero.setGrid(grid_conocidas);
            }catch (IndexOutOfBoundsException e)
            {
                System.out.println("Ignorando error");
            }
        }



    }

    /*
        -------------------
        -------*-----------
        ------*------------
        ------***----------
     */
    public static void naveGliderIDAB(Tablero tablero,int x,int y)
    {
        int xt = x;
        int yt=y;
        if(is100Glider)
        {
            for(int i=0; i<=10; i++)
            {
                x -= 5;
                for(int j=0; j<=10; j++)
                {
                    try{
                        boolean grid_conocidas[][] = tablero.getGrid();

                        grid_conocidas[x][y] = true;
                        grid_conocidas[x+1][y] = true;
                        grid_conocidas[x+2][y] = true;
                        grid_conocidas[x][y+1]=true;
                        grid_conocidas[x+1][y+2]=true;

                        DataInfo.celulasVivas += 5;
                        DataInfo.celulasMuertas -= 5;

                        tablero.setGrid(grid_conocidas);
                    }catch (IndexOutOfBoundsException e)
                    {
                        System.out.println("Ignorando error");
                    }
                    y-=5;
                }
                x = xt;
                y = yt;
                x -= (i*5);
            }

        }else
        {
            try{
                boolean grid_conocidas[][] = tablero.getGrid();

                grid_conocidas[x][y] = true;
                grid_conocidas[x+1][y] = true;
                grid_conocidas[x+2][y] = true;
                grid_conocidas[x][y+1]=true;
                grid_conocidas[x+1][y+2]=true;

                DataInfo.celulasVivas += 5;
                DataInfo.celulasMuertas -= 5;

                tablero.setGrid(grid_conocidas);
            }catch (IndexOutOfBoundsException e)
            {
                System.out.println("Ignorando error");
            }
        }


    }

    public static void naveGliderIDBA(Tablero tablero,int x,int y)
    {
        int xt = x;
        int yt=y;
        if(is100Glider)
        {
            for(int i=0; i<=10; i++)
            {
                x -= 5;
                for(int j=0; j<=10; j++)
                {
                    try{
                        boolean grid_conocidas[][] = tablero.getGrid();

                        grid_conocidas[x][y] = true;
                        grid_conocidas[x+1][y] = true;
                        grid_conocidas[x+2][y] = true;
                        grid_conocidas[x][y-1]=true;
                        grid_conocidas[x+1][y-2]=true;

                        DataInfo.celulasVivas += 5;
                        DataInfo.celulasMuertas -= 5;

                        tablero.setGrid(grid_conocidas);
                    }catch (IndexOutOfBoundsException e)
                    {
                        System.out.println("Ignorando error");
                    }
                    y-=5;
                }
                x = xt;
                y = yt;
                x -= (i*5);
            }

        }else
        {
            try{
                boolean grid_conocidas[][] = tablero.getGrid();

                grid_conocidas[x][y] = true;
                grid_conocidas[x+1][y] = true;
                grid_conocidas[x+2][y] = true;
                grid_conocidas[x][y-1]=true;
                grid_conocidas[x+1][y-2]=true;

                DataInfo.celulasVivas += 5;
                DataInfo.celulasMuertas -= 5;

                tablero.setGrid(grid_conocidas);
            }catch (IndexOutOfBoundsException e)
            {
                System.out.println("Ignorando error");
            }
        }


    }

    public static void naveGliderDIBA(Tablero tablero,int x,int y)
    {
        int xt = x;
        int yt=y;
        if(is100Glider)
        {
            for(int i=0; i<=10; i++)
            {
                x -= 5;
                for(int j=0; j<=10; j++)
                {
                    try{
                        boolean grid_conocidas[][] = tablero.getGrid();

                        grid_conocidas[x][y] = true;
                        grid_conocidas[x+1][y] = true;
                        grid_conocidas[x+2][y] = true;
                        grid_conocidas[x+2][y-1]=true;
                        grid_conocidas[x+1][y-2]=true;

                        DataInfo.celulasVivas += 5;
                        DataInfo.celulasMuertas -= 5;

                        tablero.setGrid(grid_conocidas);
                    }catch (IndexOutOfBoundsException e)
                    {
                        System.out.println("Ignorando error");
                    }
                    y-=5;
                }
                x = xt;
                y = yt;
                x -= (i*5);
            }

        }else
        {
            try{
                boolean grid_conocidas[][] = tablero.getGrid();

                grid_conocidas[x][y] = true;
                grid_conocidas[x+1][y] = true;
                grid_conocidas[x+2][y] = true;
                grid_conocidas[x+2][y-1]=true;
                grid_conocidas[x+1][y-2]=true;

                DataInfo.celulasVivas += 5;
                DataInfo.celulasMuertas -= 5;

                tablero.setGrid(grid_conocidas);
            }catch (IndexOutOfBoundsException e)
            {
                System.out.println("Ignorando error");
            }
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

            DataInfo.celulasVivas += 6;
            DataInfo.celulasMuertas -= 6;
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

        DataInfo.celulasVivas += 12*4;
        DataInfo.celulasMuertas -= 12*4;
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
            DataInfo.celulasVivas += 7;
            DataInfo.celulasMuertas -= 7;
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
            if(tablero.getGrid()[x][y]) {
                Gdx.app.postRunnable(() -> tablero.getGrid()[x][y] = false);
                DataInfo.celulasMuertas += 1;
                DataInfo.celulasVivas += -1;
            }
            else {
                Gdx.app.postRunnable(() -> tablero.getGrid()[x][y] = true);
                DataInfo.celulasMuertas += -1;
                DataInfo.celulasVivas += 1;

            }
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println(e);
        }
    }
}
