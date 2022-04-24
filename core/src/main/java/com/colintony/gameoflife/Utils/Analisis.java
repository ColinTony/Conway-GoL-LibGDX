package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.colintony.gameoflife.Models.Tablero;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Analisis {
    private Graficas graficas;
    private HashMap<Integer,Integer> map;
    private ArrayList<String> listCelulas;
    private ArrayList<String> listCelulasLog;
    private String binario;
    private int config;

    public Analisis(HashMap<Integer, Integer> map, String binario, int config) {
        this.map = map;
        this.binario = binario;
        this.config = config;
    }
    public Analisis()
    {
        this.graficas = new Graficas();
        this.map = new HashMap<Integer,Integer>();
        this.listCelulas = new ArrayList<String>();
        this.listCelulasLog = new ArrayList<String>();
        this.binario = "";
        this.config = 0;
    }

    public Analisis(HashMap<Integer, Integer> map, String binario, int config, String fileNamePlots, String pathFilePlots) {
        this.map = map;
        this.binario = binario;
        this.config = config;
    }

    public Analisis(HashMap<Integer, Integer> map) {
        this.map = map;
    }


    public void addMap(String binary)
    {
        this.binario = binary;
        this.binarioToConfig();

        if(this.map.containsKey(this.config))
        {
            int valor = this.map.get(this.config);
            valor++;
            this.map.replace(this.config,valor);
        }else
            this.map.put(this.config,1);

        this.reset();
    }
    // Convertir String binario a Decimal
    private void binarioToConfig()
    {
        this.config = Integer.parseInt(this.binario,2);
    }

    public void addListInfoCelulas(String add)
    {
        this.listCelulas.add(add);
    }
    public void addListInfoCelulasLog(String add)
    {
        this.listCelulasLog.add(add);
    }

    private void reset()
    {
        this.binario = "";
        this.config = 0;
    }
    // GETTERS AND SETTERS

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Integer> map) {
        this.map = map;
    }

    public String getBinario() {
        return binario;
    }

    public void setBinario(String binario) {
        this.binario = binario;
    }

    public int getConfig() {
        return config;
    }

    public void setConfig(int config) {
        this.config = config;
    }

    public String getPathFilePlots() {
        return this.graficas.getFileNamePlots();
    }
    public String getPathFilePlotsCelulas() {
        return this.graficas.getFileNamePlotsCelulas();
    }
    public String getPathFilePlotsCelulasLog(){return this.graficas.getFileNamePlotsLog();}

    public void escribirTXTShannon(Tablero tablero) {
        this.graficas.writeTXTPlotShannon(this.map,tablero);
    }
    public void escribirTXTCelulas() {
        this.graficas.writeTXTPlotCelulas(this.listCelulas);
    }
    public void escribirTXTCelulasLog()
    {
        this.graficas.writeTXTPlotLog(this.listCelulasLog);
    }
}
