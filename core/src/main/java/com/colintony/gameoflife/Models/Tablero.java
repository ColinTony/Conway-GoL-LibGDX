package com.colintony.gameoflife.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.colintony.gameoflife.Utils.DataInfo;
import com.colintony.gameoflife.Utils.Analisis;

public class Tablero {
    private boolean grid[][];
    private Analisis analisis;
    private String binary;
    private boolean isDataCheck;
    private int widthGrid;
    private int heigthGrid;
    private int s_min;
    private int s_max;
    private int b_min;
    private int b_max;

    private float R_T;
    private float G_T;
    private float B_T;
    private float R_C;
    private float G_C;
    private float B_C;

    public Tablero(float chance,int widthGrid, int heigthGrid)
    {
        this.widthGrid = widthGrid;
        this.heigthGrid = heigthGrid;
        this.s_min = 2;
        this.s_max = 3;
        this.b_min = 3;
        this.b_max = 3;

        this.analisis = new Analisis();
        this.isDataCheck = true;
        this.grid = new boolean[this.widthGrid][this.heigthGrid];
        createRandomWorld(chance);
        // Coloeres default Tablero
        this.R_T = 1f;
        this.G_T = 1f;
        this.B_T = 1f;
        // Colores default Celula
        this.R_C = 0f;
        this.G_C = 0f;
        this.B_C = 0f;
    }
    public Tablero(int s_min,int s_max,int b_min,int b_max,int widthGrid, int heigthGrid)
    {
        this.widthGrid = widthGrid;
        this.heigthGrid = heigthGrid;
        this.s_min = s_min;
        this.s_max = s_max;
        this.b_min = b_min;
        this.b_max = b_max;

        this.analisis = new Analisis();
        this.isDataCheck = true;
        this.grid = new boolean[this.widthGrid][this.heigthGrid];
        createRandomWorld(0f);
        // Coloeres default Tablero
        this.R_T = 1f;
        this.G_T = 1f;
        this.B_T = 1f;
        // Colores default Celula
        this.R_C = 0f;
        this.G_C = 0f;
        this.B_C = 0f;
    }
    public Tablero(){};
    public Tablero(boolean[][] grid) {
        this.grid = grid;
    }
    public Tablero(Analisis sh) {
        this.analisis = sh;
    }

    public Tablero(boolean[][] grid, Analisis analisis, String binary) {
        this.grid = grid;
        this.analisis = analisis;
        this.binary = binary;
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
    /*
        Creando un mundo Taroide
        Crearemos un Update2 el cual se encarara de
        revisar el grid de forma taroidal
     */
    public void updateTaroide()
    {

        for (int x = 0; x < this.grid[0].length; x++) {
            for (int y = 0; y < this.grid[0].length; y++) {
                int vecinos;
                if(this.isDataCheck)
                    vecinos = checkApariciones(x,y);
                else
                    vecinos = check(x,y);

                boolean status = this.grid[x][y];
                reglasCheck(vecinos,status,x,y);
            }
        }
        DataInfo.generacion++;
        String info = "Celulas vivas" + ","+DataInfo.generacion+","+DataInfo.celulasVivas+"\n";
        String infoLog = "Logaritmo" + ","+DataInfo.generacion+","+Math.log10(DataInfo.celulasVivas)+"\n";
        this.analisis.addListInfoCelulas(info);
        this.analisis.addListInfoCelulasLog(infoLog);
    }
    // Default regla R(S_min,S_max,B_min,B_max).
    // R(2,3,3,3).
    private void reglasCheck(int vecinos,boolean status,int x,int y)
    {

        if(status)
        {
            if((this.s_min<=vecinos) && (vecinos <= this.s_max))
                alive(status,x,y);
            else
                die(status,x,y);
        }else
        {
            if((this.b_min<=vecinos) && (vecinos <= this.s_max))
                alive(status,x,y);
            else
                die(status,x,y);
        }
    }
    public int checkApariciones(int x, int y)
    {
        int width = this.grid.length;
        int heigth = this.grid[0].length;

        int xMenos = x - 1 < 0 ? width-1 : x-1;
        int xMas = x+1 >= width?0:x+1;
        int yMenos = y-1<0 ? heigth-1 : y-1;
        int yMas = y+1>= heigth ? 0:y+1;

        int vecinos = 0;
        this.binary = "";

        vecinos += this.grid[xMas][yMenos] ? binarioAdd(false,1):binarioAdd(false,0);   // derecha-Abajo
        vecinos += this.grid[x][yMenos] ? binarioAdd(false,1):binarioAdd(false,0);;      // abajo
        vecinos += this.grid[xMenos][yMenos] ? binarioAdd(false,1):binarioAdd(false,0);; // izquierda-abajo


        vecinos += this.grid[xMas][y] ? binarioAdd(false,1):binarioAdd(false,0);;        // derecha
        vecinos += this.grid[x][y] ? binarioAdd(true,1):binarioAdd(true,0);;           // Centro
        vecinos += this.grid[xMenos][y] ? binarioAdd(false,1):binarioAdd(false,0);;      // izquierda


        vecinos += this.grid[xMas][yMas] ? binarioAdd(false,1):binarioAdd(false,0);;     // derecha-arriba
        vecinos += this.grid[x][yMas] ? binarioAdd(false,1):binarioAdd(false,0);;        // arriba
        vecinos += this.grid[xMenos][yMas] ? binarioAdd(false,1):binarioAdd(false,0);;   // izqeruida-arriba

        this.analisis.addMap(this.binary);
        return vecinos;
    }
    /*

     */
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

    /*
        Vecinos check-binario
     */
    public int binarioAdd(boolean iAM, int ret)
    {
        this.binary += ret;

        return iAM ? 0 : ret;
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


    public void txtMap() {
        this.analisis.escribirTXT();
    }
    public void txtCelulas()
    {
        this.analisis.escribirTXTCelulas();
    }
    public void txtCelulasLog()
    {
        this.analisis.escribirTXTCelulasLog();
    }
    public String getNameFile()
    {
        return this.analisis.getPathFilePlots();
    }
    public String getNameFileCelulas()
    {
        return this.analisis.getPathFilePlotsCelulas();
    }
    public String getNameFileCelulasLog() {
        return this.analisis.getPathFilePlotsCelulasLog();
    }

    public float getR_T() {
        return R_T;
    }

    public void setR_T(float r_T) {
        R_T = r_T;
    }

    public float getG_T() {
        return G_T;
    }

    public void setG_T(float g_T) {
        G_T = g_T;
    }

    public float getB_T() {
        return B_T;
    }

    public void setB_T(float b_T) {
        B_T = b_T;
    }

    public float getR_C() {
        return R_C;
    }

    public void setR_C(float r_C) {
        R_C = r_C;
    }

    public float getG_C() {
        return G_C;
    }

    public void setG_C(float g_C) {
        G_C = g_C;
    }

    public float getB_C() {
        return B_C;
    }

    public void setB_C(float b_C) {
        B_C = b_C;
    }

    public boolean isDataCheck() {
        return isDataCheck;
    }

    public int getS_min() {
        return s_min;
    }

    public void setS_min(int s_min) {
        this.s_min = s_min;
    }

    public int getS_max() {
        return s_max;
    }

    public void setS_max(int s_max) {
        this.s_max = s_max;
    }

    public int getB_min() {
        return b_min;
    }

    public void setB_min(int b_min) {
        this.b_min = b_min;
    }

    public int getB_max() {
        return b_max;
    }

    public void setB_max(int b_max) {
        this.b_max = b_max;
    }

    public int getWidthGrid() {
        return widthGrid;
    }

    public void setWidthGrid(int widthGrid) {
        this.widthGrid = widthGrid;
    }

    public int getHeigthGrid() {
        return heigthGrid;
    }

    public void setHeigthGrid(int heigthGrid) {
        this.heigthGrid = heigthGrid;
    }

    public void setDataCheck(boolean dataCheck) {
        isDataCheck = dataCheck;
    }
}
