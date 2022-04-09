package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Shannon {
    private HashMap<Integer,BigInteger> map;
    private String binario;
    private int config;
    private String fileNamePlots;
    private String pathFilePlots="C:/Users/mrc0l/Documents/Complejos/GoL/core/src/main/java/plots/";

    public Shannon(HashMap<Integer, BigInteger> map, String binario, int config) {
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
        this.map = new HashMap<Integer,BigInteger>();
        this.binario = "";
        this.config = 0;
    }


    public void addMap(String binary)
    {
        this.binario = binary;
        this.binarioToConfig();

        if(this.map.containsKey(this.config))
        {
            BigInteger valor = (BigInteger) this.map.get(this.config);
            this.map.replace(this.config,valor.add(BigInteger.ONE));
        }else
            this.map.put(this.config,BigInteger.ONE);

        this.reset();
    }
    // Convertir String binario a Decimal
    private void binarioToConfig()
    {
        this.config = Integer.parseInt(this.binario,2);
    }


    public void writeTXTPlot()
    {
        // Escribir el txt
        FileHandle hadleFile = Gdx.files.absolute(this.pathFilePlots);
        for(int key : this.map.keySet())
            hadleFile.writeString(DataInfo.generacion+","+key+","+this.map.get(key)+"\n",true);

    }
    public void imprimeMap()
    {
        for(int key : this.map.keySet())
            System.out.println("key:"+key+","+this.map.get(key));
    }
    private void reset()
    {
        this.binario = "";
        this.config = 0;
    }
    // GETTERS AND SETTERS

    public Map<Integer, BigInteger> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, BigInteger> map) {
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
        return fileNamePlots;
    }

    public void setPathFilePlots(String pathFilePlots) {
        this.pathFilePlots = pathFilePlots;
    }
}
