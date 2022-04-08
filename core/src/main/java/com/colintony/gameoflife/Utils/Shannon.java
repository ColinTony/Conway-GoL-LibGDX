package com.colintony.gameoflife.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Shannon {
    private Map<Integer,Integer> map;
    private String binario;
    private int config;
    private String fileNamePlots;
    private final String pathFilePlots="C:/Users/mrc0l/Documents/Complejos/GoL/core/src/main/java/plots/";

    public Shannon(Map<Integer, Integer> map, String binario, int config) {
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

        this.map = new HashMap<Integer,Integer>();
        this.binario = "";
        this.config = 0;
    }


    public void addMap()
    {
        this.binarioToConfig();
        if(!this.map.containsKey(this.config))
            this.map.put(this.config,1);
        else
        {
            int value = this.map.get(this.config);
            this.map.put(this.config,value++);
        }

    }
    // Convertir String binario a Decimal
    private void binarioToConfig()
    {
        this.config = Integer.parseInt(this.binario,2);
    }

    public void addBinaryChar(int numero)
    {
        this.binario += numero;
        if(binario.length() == 9)
        {
            this.addMap();
            this.writeTXTPlot();
            this.reset();
        }
    }
    private void writeTXTPlot()
    {
        // Escribir el txt
    }
    public void reset()
    {
        this.binario = "";
        this.config = 0;
    }
    // GETTERS AND SETTERS

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
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
