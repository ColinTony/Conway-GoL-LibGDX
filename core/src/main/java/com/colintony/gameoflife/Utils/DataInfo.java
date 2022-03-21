package com.colintony.gameoflife.Utils;

public class DataInfo {
    public static int celulasMuertas = 0;
    public static int celulasVivas = 0;
    public static int totalCel = celulasMuertas+celulasVivas;
    public static int generacion = 0;

    public static void updateData()
    {
        totalCel = celulasMuertas+celulasVivas;
    }

    public static void addCelda(boolean isBlanca)
    {
        if(isBlanca)
            celulasVivas++;
        else
            celulasMuertas++;

        updateData();
    }
    public static void cambiarCelda(boolean isBlanca)
    {
        if(isBlanca)
        {
            celulasVivas--;
            celulasMuertas++;
        }else
        {
            celulasMuertas--;
            celulasVivas++;
        }
        updateData();
    }
    public static void nuevaGeneracion()
    {
        generacion++;
    }
}
