package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Shannon {
    private Graficas graficas;
    private HashMap<Integer,Integer> map;
    private String binario;
    private int config;

    public Shannon(HashMap<Integer, Integer> map, String binario, int config) {
        this.map = map;
        this.binario = binario;
        this.config = config;
    }
    public Shannon()
    {
        this.graficas = new Graficas();
        this.map = new HashMap<Integer,Integer>();
        this.binario = "";
        this.config = 0;
    }

    public Shannon(HashMap<Integer, Integer> map, String binario, int config, String fileNamePlots, String pathFilePlots) {
        this.map = map;
        this.binario = binario;
        this.config = config;
    }

    public Shannon(HashMap<Integer, Integer> map) {
        this.map = map;
    }


    public void addMap(String binary)
    {
        this.binario = binary;
        this.binarioToConfig();

        if(this.map.containsKey(this.config))
        {
            int valor = (int) this.map.get(this.config);
            this.map.replace(this.config,valor+1);
        }else
            this.map.put(this.config,1);

        this.reset();
    }
    // Convertir String binario a Decimal
    private void binarioToConfig()
    {
        this.config = Integer.parseInt(this.binario,2);
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
        return this.graficas.getPathFilePlots();
    }

    public void escribirTXT() {
        this.graficas.writeTXTPlot(this.map);
    }
}
