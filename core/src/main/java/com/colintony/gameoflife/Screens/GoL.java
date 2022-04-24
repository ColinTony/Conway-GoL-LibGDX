package com.colintony.gameoflife.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.colintony.gameoflife.MainMenu;
import com.colintony.gameoflife.Models.Tablero;
import com.colintony.gameoflife.Utils.ConfigGame;
import com.colintony.gameoflife.Utils.DataInfo;
import com.colintony.gameoflife.Utils.InputsEvents;
import com.colintony.gameoflife.Utils.ScreenInfo;

import javax.swing.JOptionPane;

public class GoL extends PantallaAbstract implements Disposable {
    private SpriteBatch batch;
    private DataInfo dataInfo;
    private ScreenInfo screenInfo;
    private OrthographicCamera camera;
    private Tablero tablero;


    // Inicio GoL variables
    private ShapeRenderer renderer;

    //Dimensiones de una sola celula
    private Vector2 dimensions;
    private FPSLogger fpsLogger; // para checar los fps en consola
    private float dt;

    // end GoL variabels

    // ESTADO DEL JUEGO
    public enum STATE
    {
        PAUSE,
        RUN,
        RESUME,
        STOPPED
    }
    private STATE state = STATE.PAUSE;


    public GoL(MainMenu game) {
        super(game);

        // Dibujo e Informacion
        this.batch = this.game.getSpriteBatch();
        this.dataInfo = new DataInfo();
        this.screenInfo = new ScreenInfo();

        // iniciando la cmaara
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, ConfigGame.WIDTH_PANTALLA,ConfigGame.HEIGTH_PANTALLA);
        this.camera.translate(0,0);
        this.camera.update();


        // CREANDO MUNDO
        this.fpsLogger = new FPSLogger();

        this.renderer = new ShapeRenderer();
        this.renderer.setAutoShapeType(true);

        this.tablero = new Tablero(0f,500,500);
        this.dimensions = new Vector2(ConfigGame.WIDTH_PANTALLA / (float) this.tablero.getGrid()[0].length, ConfigGame.HEIGTH_PANTALLA / (float) this.tablero.getGrid().length);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(this.tablero.getR_T(),this.tablero.getG_T(),this.tablero.getB_T(),1);
        this.renderer.setColor(new Color(this.tablero.getR_C(),this.tablero.getG_C(),this.tablero.getB_C(),1));
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        renderer.setProjectionMatrix(camera.combined);
        //this.fpsLogger.log();

        this.inputGameStatus();
        InputsEvents.inputConocidas(this.camera,this.dimensions,this.tablero,this.screenInfo.getConocida());
        InputsEvents.inputsCamera(this.screenInfo,this.camera,this.tablero);

        this.batch.begin();
        if(this.screenInfo.isMostrarControles())
            this.screenInfo.dibujar(this.batch,this.tablero.getS_min(),this.tablero.getS_max(),this.tablero.getB_min(),this.tablero.getB_max(),this.state);
        this.batch.end();
        if(this.screenInfo.isModeBorders())
            this.renderer.begin(ShapeRenderer.ShapeType.Line);
        else
            this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        switch(state)
        {
            case PAUSE:
                {
                for (int x = 0; x < this.tablero.getGrid()[0].length; x++)
                    for (int y = 0; y < this.tablero.getGrid()[0].length; y++)
                        if (this.tablero.getGrid()[x][y])
                            this.renderer.rect(x * dimensions.x, y * dimensions.y, dimensions.x, dimensions.y);
                }
                break;
            case RUN:
                this.dt = Gdx.graphics.getDeltaTime();
                this.tablero.updateTaroide(); // taroide
                {
                    for (int x = 0; x < this.tablero.getGrid()[0].length; x++)
                        for (int y = 0; y < this.tablero.getGrid()[0].length; y++)
                            if (this.tablero.getGrid()[x][y])
                                this.renderer.rect(x * dimensions.x, y * dimensions.y, dimensions.x, dimensions.y);
                }
                break;

            case RESUME:
                break;
            case STOPPED:
                break;
        }

        this.renderer.end();
    }
    /*
        Inputs STATUS GAME
     */
    public void inputGameStatus()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.P))
        {
            this.state = STATE.PAUSE;
            pause();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.R))
        {
            this.state = STATE.RUN;
            resume();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.N))
        {
            this.state = STATE.PAUSE;
            this.tablero.update();
        }
        // Cambiar colores aleatoriamente
        if(Gdx.input.isKeyJustPressed(Input.Keys.J))
        {
            this.tablero.setR_T((float)Math.random());
            this.tablero.setG_T((float)Math.random());
            this.tablero.setB_T((float)Math.random());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.K))
        {
            this.tablero.setR_C((float)Math.random());
            this.tablero.setG_C((float)Math.random());
            this.tablero.setB_C((float)Math.random());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            this.state = GoL.STATE.PAUSE;
            DataInfo.saveConfig(tablero);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F))
        {
            this.state = GoL.STATE.PAUSE;
            this.pause();
            this.tablero = DataInfo.loadConfig(this.tablero);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.X))
        {
            this.state = GoL.STATE.PAUSE;
            float respuesta = Float.parseFloat(JOptionPane.showInputDialog("Densidad de celulas vivas: ", "0.8"));
            this.tablero = new Tablero(respuesta,this.tablero.getWidthGrid(),this.tablero.getHeigthGrid());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.H))
        {
            this.state = GoL.STATE.PAUSE;
            String respuesta = JOptionPane.showInputDialog("tablero 500x500: ", "500x500");
            respuesta = respuesta.trim();
            String[] tabTam = respuesta.split("x");
            int w = Integer.parseInt(tabTam[0]);
            int h = Integer.parseInt(tabTam[1]);
            this.tablero = new Tablero(0f,w,h);
            // iniciando la cmaara
            this.camera = new OrthographicCamera();
            this.camera.setToOrtho(false, ConfigGame.WIDTH_PANTALLA,ConfigGame.HEIGTH_PANTALLA);
            this.camera.translate(0,0);
            this.camera.update();
            this.dimensions = new Vector2(ConfigGame.WIDTH_PANTALLA / (float) this.tablero.getGrid()[0].length, ConfigGame.HEIGTH_PANTALLA / (float) this.tablero.getGrid().length);
            this.tablero.update();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.L))
        {
            this.state = GoL.STATE.PAUSE;
            String respuesta = JOptionPane.showInputDialog("Reglas R(S_min,S_max,B_min,B_max): ", "2,3,3,3");
            String[]valores = respuesta.split(",");
            this.tablero.setS_min(Integer.parseInt(valores[0]));
            this.tablero.setS_max(Integer.parseInt(valores[1]));
            this.tablero.setB_min(Integer.parseInt(valores[2]));
            this.tablero.setB_max(Integer.parseInt(valores[3]));
        }

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        this.renderer.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
        this.state = STATE.PAUSE;

    }

    @Override
    public void resume() {
        super.resume();
    }
}
