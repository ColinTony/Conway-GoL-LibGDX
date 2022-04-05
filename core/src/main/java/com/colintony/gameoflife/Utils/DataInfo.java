package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.colintony.gameoflife.Models.Tablero;

import java.io.File;
import java.util.UUID;

import javax.swing.JFileChooser;

public class DataInfo {
    public static int celulasMuertas = 0;
    public static int celulasVivas = 0;
    public static int generacion = 0;
    public static String pathSave = "core/src/main/java/saves/";
    public static String pathChoose = "";

    /*
        Save configuracion del tablero.
     */
    public static void saveConfig(Tablero tablero)
    {
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
        //TODO filtrar correctamente json
        int returnValue = chooser.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            pathChoose = chooser.getSelectedFile().getName();
            System.out.println(pathSave+pathChoose);
            FileHandle file = Gdx.files.absolute(pathSave+pathChoose);
            String fileJson = file.readString();
            System.out.println(fileJson);
            System.out.println(file);
            Json json = new Json();
            Tablero tabTmp = json.fromJson(Tablero.class,file);
            return tabTmp;
        }else
        {
            System.out.println("No pude crear nada");
        }

        return tablero;
    }
}
