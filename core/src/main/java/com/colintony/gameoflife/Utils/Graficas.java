package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.HashMap;
import java.util.UUID;

public class Graficas
{
    private String fileNamePlots;
    private String pathFilePlots="C:/Users/mrc0l/Documents/Complejos/GoL/core/src/main/java/plots/";

    public Graficas() {
        UUID unique = UUID.randomUUID();
        this.fileNamePlots = "plots-";
        this.fileNamePlots+=unique.toString();
        this.fileNamePlots+=".txt";
        this.pathFilePlots+=this.fileNamePlots;
    }

    public void writeTXTPlot(HashMap<Integer,Integer> map) {
        // Escribir el txt
        FileHandle hadleFile = Gdx.files.absolute(this.pathFilePlots);
        if (!hadleFile.exists()) {
            for (int key : map.keySet())
                hadleFile.writeString(DataInfo.generacion + "," + key + "," + map.get(key) + "\n", true);
        }else
        {
            hadleFile.delete();
            for (int key : map.keySet())
                hadleFile.writeString(DataInfo.generacion + "," + key + "," + map.get(key) + "\n", true);
        }
    }

    public String getFileNamePlots() {
        return fileNamePlots;
    }

    public void setFileNamePlots(String fileNamePlots) {
        this.fileNamePlots = fileNamePlots;
    }

    public String getPathFilePlots() {
        return pathFilePlots;
    }

    public void setPathFilePlots(String pathFilePlots) {
        this.pathFilePlots = pathFilePlots;
    }
}
