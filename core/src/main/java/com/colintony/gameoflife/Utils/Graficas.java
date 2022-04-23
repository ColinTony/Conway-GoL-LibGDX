package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Graficas
{
    private String fileNamePlots;
    private String fileNamePlotsCelulas;
    private String fileNamePlotsLog;
    private String pathFilePlots="C:/Users/mrc0l/Documents/Complejos/GoL/core/src/main/java/plots/";
    private String pathFilePlotsCelulas="C:/Users/mrc0l/Documents/Complejos/GoL/core/src/main/java/plots/";
    private String pathFilePlotsLog="C:/Users/mrc0l/Documents/Complejos/GoL/core/src/main/java/plots/";

    public Graficas() {
        UUID unique = UUID.randomUUID();
        this.fileNamePlotsCelulas = "plots-celulas-";
        this.fileNamePlotsLog = "plots-log-";
        this.fileNamePlots = "plots-configs-";

        this.fileNamePlots  +=unique.toString();
        this.fileNamePlotsCelulas += unique.toString();
        this.fileNamePlotsLog += unique.toString();

        this.fileNamePlots+=".txt";
        this.fileNamePlotsCelulas+=".txt";
        this.fileNamePlotsLog +=".txt";

        this.pathFilePlotsLog+=this.fileNamePlotsLog;
        this.pathFilePlots+=this.fileNamePlots;
        this.pathFilePlotsCelulas+=this.fileNamePlotsCelulas;
    }

    public void writeTXTPlotConfig(HashMap<Integer,Integer> map) {
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

    public void writeTXTPlotCelulas(ArrayList<String> celulasInfo) {
        // Escribir el txt
        FileHandle hadleFile = Gdx.files.absolute(this.pathFilePlotsCelulas);

        if (!hadleFile.exists()) {
            for (String info : celulasInfo)
                hadleFile.writeString(info, true);
        }else
        {
            hadleFile.delete();
            for (String info : celulasInfo)
                hadleFile.writeString(info, true);
        }
    }

    public void writeTXTPlotLog(ArrayList<String> celulasInfo)
    {
        FileHandle hadleFile = Gdx.files.absolute(this.pathFilePlotsLog);

        if (!hadleFile.exists()) {
            for (String info : celulasInfo) {
                hadleFile.writeString(info, true);
            }
        }else
        {
            hadleFile.delete();
            for (String info : celulasInfo)
                hadleFile.writeString(info, true);
        }
    }

    public String getFileNamePlots() {
        return fileNamePlots;
    }
    public String getFileNamePlotsCelulas() {
        return fileNamePlotsCelulas;
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

    public String getFileNamePlotsLog() {
        return fileNamePlotsLog;
    }

    public void setFileNamePlotsLog(String fileNamePlotsLog) {
        this.fileNamePlotsLog = fileNamePlotsLog;
    }

    public String getPathFilePlotsLog() {
        return pathFilePlotsLog;
    }

    public void setPathFilePlotsLog(String pathFilePlotsLog) {
        this.pathFilePlotsLog = pathFilePlotsLog;
    }
}
