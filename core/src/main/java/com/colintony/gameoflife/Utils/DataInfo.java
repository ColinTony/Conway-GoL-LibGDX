package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.colintony.gameoflife.Models.Tablero;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class DataInfo {
    public static int celulasMuertas = 0;
    public static int celulasVivas = 0;
    public static int generacion = 0;
    public static String pathSave = "core/src/main/java/saves/";
    public static String pathPythonScript = "C:/Users/mrc0l/Documents/Complejos/GoL/core/src/main/java/plots/plots.py";
    public static String pathChoose = "";

    /*
        Save configuracion del tablero.
     */
    public static void saveConfig(Tablero tablero)
    {

        tablero.setCelulasVivas(celulasVivas);
        tablero.setCelulasMuertas(celulasMuertas);

        Json json = new Json();
        String tableroJson = json.toJson(tablero);
        UUID unique = UUID.randomUUID();
        String fileName = "config-";
        fileName+=unique.toString();
        fileName+=".json";

        FileHandle fileJson = Gdx.files.absolute(pathSave+fileName);
        fileJson.writeString(tableroJson,true);
    }
    /*
        Cargando un archivo de json
        un configuracion
     */
    public static Tablero loadConfig(Tablero tablero)
    {
        JFileChooser chooser = new JFileChooser();

        chooser.setDialogTitle("Elige una configuración");
        chooser.setCurrentDirectory(new File(pathSave));
        chooser.requestFocus();
        chooser.requestFocusInWindow();
        //TODO filtrar correctamente json
        int returnValue = chooser.showOpenDialog(null);

        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            pathChoose = chooser.getSelectedFile().getName();
            FileHandle file = Gdx.files.absolute(pathSave+pathChoose);
            Json json = new Json();
            Tablero tabTmp = json.fromJson(Tablero.class,file);
            celulasMuertas = tabTmp.getCelulasMuertas();
            celulasVivas = tabTmp.getCelulasVivas();
            generacion = tablero.getGeneracion();
            return tabTmp;
        }

        return tablero;
    }

    /*
        Charts
     */
    public static void chart(String nameFile) throws IOException
    {
        System.out.println("---"+nameFile);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(()->
                {
                    try {
                        List<String> command = new LinkedList<>();
                        command.add("C:/Program Files/Python3/python");
                        command.add(pathPythonScript);
                        command.add(nameFile);
                        //ProcessBuilder need to separate string into list string not one long string
                        ProcessBuilder pb = new ProcessBuilder(command);
                        Process p = pb.start(); // Start the process.

                    }catch ( IOException e) {
                        e.printStackTrace();
                    }

                });

            }
        });
        thread.start();
    }

}
