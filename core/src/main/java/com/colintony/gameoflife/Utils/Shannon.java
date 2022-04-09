package com.colintony.gameoflife.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Shannon {
    private HashMap<Integer,Integer> map;
    private String binario;
    private int config;
    private String fileNamePlots;
    private String pathFilePlots="C:/Users/mrc0l/Documents/Complejos/GoL/core/src/main/java/plots/";

    public Shannon(HashMap<Integer, Integer> map, String binario, int config) {
        this.map = map;
        this.binario = binario;
        this.config = config;
    }
    public Shannon()
    {
        UUID unique = UUID.randomUUID();
        this.fileNamePlots = "plots-";
        this.fileNamePlots+=unique.toString();
        this.fileNamePlots+=".txt";
        this.pathFilePlots+=this.fileNamePlots;
        this.map = new HashMap<Integer,Integer>();
        this.binario = "";
        this.config = 0;
    }


    public void addMap(String binary)
    {
        this.binario = binary;
        this.binarioToConfig();

        if(this.map.containsKey(this.config))
        {
            int valor = this.map.get(this.config);
            this.map.replace(this.config,valor++);
        }else
            this.map.put(this.config,1);

        this.reset();
    }
    // Convertir String binario a Decimal
    private void binarioToConfig()
    {
        this.config = Integer.parseInt(this.binario,2);
    }


    private void writeTXTPlot()
    {
        // Escribir el txt

    }
    public void imprimeMap()
    {
        System.out.println("Valores del map");
        for(int key : this.map.keySet())
            System.out.println("key:"+key+","+this.map.get(key));
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

}
