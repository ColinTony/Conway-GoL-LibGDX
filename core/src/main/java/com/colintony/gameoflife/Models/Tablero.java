package com.colintony.gameoflife.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.colintony.gameoflife.Utils.ConfigGame;
import com.colintony.gameoflife.Utils.DataInfo;

public class Tablero {
    private boolean grid[][];
    private boolean grid_conocida[][];

    public Tablero(float chance)
    {
        this.grid = new boolean[ConfigGame.GRID_WIDTH][ConfigGame.GRID_HEIGTH];
        createRandomWorld(chance);
    }
    /*
   Metodos para Crear el mundo
    */

    // RANDOM BOLLEAN CON CHANCE DE PROBABILIDAD
    public void createRandomWorld(float chance)
    {
        for (int x = 0; x < this.grid[0].length; x++) {
            for (int y = 0; y < this.grid[0].length; y++) {
                boolean rand = MathUtils.randomBoolean(chance);
                this.grid[x][y] = rand;
                if(rand)
                    DataInfo.celulasVivas++;
                else
                    DataInfo.celulasMuertas++;
            }
        }
    }
    public void insertarConocida()
    {

    }
    /*
        Creando un mundo Taroide
        Crearemos un Update2 el cual se encarara de
        revisar el grid de forma taroidal
     */
    public void updateTaroide()
    {
        DataInfo.generacion++;
        for (int x = 0; x < this.grid[0].length; x++) {
            for (int y = 0; y < this.grid[0].length; y++) {
                int vecinos = check(x,y);
                boolean status = this.grid[x][y];

                if(status) // vivos
                {
                    if(vecinos == 2 || vecinos == 3)
                        alive(status,x,y);
                    if(vecinos < 2)
                        die(status,x,y);
                    if(vecinos > 3)
                        die(status,x,y);
                }else
                if(vecinos == 3)
                    alive(status,x,y);
            }
        }
    }
    public int check(int x, int y)
    {
        int width = this.grid.length;
        int heigth = this.grid[0].length;

        int xMenos = x - 1 < 0 ? width-1 : x-1;
        int xMas = x+1 >= width?0:x+1;
        int yMenos = y-1<0 ? heigth-1 : y-1;
        int yMas = y+1>= heigth ? 0:y+1;

        int vecinos = 0;
        vecinos += this.grid[x][yMas] ? 1:0;        // arriba
        vecinos += this.grid[x][yMenos] ? 1:0;      // abajo
        vecinos += this.grid[xMenos][y] ? 1:0;      // izquierda
        vecinos += this.grid[xMas][y] ? 1:0;        // derecha
        vecinos += this.grid[xMenos][yMenos] ? 1:0; // izquierda-abajo
        vecinos += this.grid[xMenos][yMas] ? 1:0;   // izqeruida-arriba
        vecinos += this.grid[xMas][yMas] ? 1:0;     // derecha-arriba
        vecinos += this.grid[xMas][yMenos] ? 1:0;   // derecha-Abajo

        return vecinos;
    }
    // Funciones de vida o muerte
    // xPos y yPos son las cordenadas de la celula en x-axis y y-axis
    public void die(boolean status,int xPos , int yPos)
    {
        if(status == true)
        {
            Gdx.app.postRunnable(() -> this.grid[xPos][yPos] = false);
            DataInfo.celulasVivas--;
            DataInfo.celulasMuertas++;
        }
    }
    public void alive(boolean status,int xPos , int yPos)
    {
        if(status == false)
        {
            Gdx.app.postRunnable(() -> this.grid[xPos][yPos] = true);
            DataInfo.celulasMuertas--;
            DataInfo.celulasVivas++;
        }
    }
    // metodo update que ira actualizando la pantalla con las reglas.
    public void update()
    {
        DataInfo.generacion++;
        for (int x = 0; x < this.grid[0].length; x++) {
            for (int y = 0; y < this.grid[0].length; y++) {
                if (this.grid[x][y])
                {
                    if(isCrowded(x, y) || isAlone(x, y))
                        die(this.grid[x][y],x, y);
                } else {
                    if(isAlive(x, y))
                        alive(this.grid[x][y],x, y);
                }
            }
        }

    }

    // Checando las reglas
    public boolean isCrowded(int xPos,int yPos)
    {
        return getVecinos(xPos,yPos) > 3;
    }

    public boolean isAlone(int xPos, int yPos)
    {
        return getVecinos(xPos,yPos) < 2;
    }
    public boolean isAlive(int xPos, int yPos)
    {
        return getVecinos(xPos,yPos) == 3;
    }

    // obtener vecinos
    private int getVecinos(int xPos, int yPos)
    {
        int numVecinos=0;
        numVecinos += getVecinos(xPos, yPos, 1, 0);
        numVecinos += getVecinos(xPos, yPos, -1, 0);

        numVecinos += getVecinos(xPos, yPos, 0, 1);
        numVecinos += getVecinos(xPos, yPos, 0, -1);

        numVecinos += getVecinos(xPos, yPos, 1, 1);
        numVecinos += getVecinos(xPos, yPos, 1, -1);

        numVecinos += getVecinos(xPos, yPos, -1, 1);
        numVecinos += getVecinos(xPos, yPos, -1, -1);
        return numVecinos;
    }
    /*
     Como estamos guardando en el grid 1 y 0 que son
     true o false se puede hacer este operador ternario
     el cual devolvera 1 o 0 dependiedno el valor de la celula
     en la posicion del grid
     */
    private int getVecinos(int xPos, int yPos, int xOffset, int yOffset)
    {
        try{
            return this.grid[xPos+xOffset][yPos+yOffset] ? 1 : 0;
        }catch (IndexOutOfBoundsException e){


        }
        return 0;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
    }
}
