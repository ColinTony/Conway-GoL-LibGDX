package com.colintony.gameoflife.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.colintony.gameoflife.Models.Tablero;

import java.util.UUID;

public class DataInfo {
    public static int celulasMuertas = 0;
    public static int celulasVivas = 0;
    public static int generacion = 0;


    public static void saveConfig(Tablero tablero)
    {
        Json json = new Json();
        String tableroJson = json.toJson(tablero);
        UUID unique = UUID.randomUUID();
        String fileName = "config-";
        fileName+=unique.toString();
        fileName+=".json";

        FileHandle fileJson = Gdx.files.absolute("./"+fileName);
        fileJson.writeString(tableroJson,true);
    }
}
