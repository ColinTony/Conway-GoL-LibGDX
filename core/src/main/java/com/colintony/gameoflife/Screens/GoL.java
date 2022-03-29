package com.colintony.gameoflife.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.colintony.gameoflife.MainMenu;
import com.colintony.gameoflife.Models.Celula;
import com.colintony.gameoflife.Models.Tablero;
import com.colintony.gameoflife.Utils.ConfigGame;
import com.colintony.gameoflife.Utils.DataInfo;
import com.colintony.gameoflife.Utils.ScreenInfo;

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
        this.renderer.setColor(Color.BLACK);

        this.tablero = new Tablero(0.87645f);
        this.dimensions = new Vector2(ConfigGame.WIDTH_PANTALLA / (float) this.tablero.getGrid()[0].length, ConfigGame.HEIGTH_PANTALLA / (float) this.tablero.getGrid().length);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        renderer.setProjectionMatrix(camera.combined);
        this.fpsLogger.log();


        this.inputGameStatus();
        this.inputsCamera();
        this.inputCelulas();
        this.batch.begin();
        if(this.screenInfo.isMostrarControles())
            this.screenInfo.dibujar(this.batch);
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
                // this.tablero.updateTaroide(); // taroide
                this.tablero.updateTaroide(); // no taoride
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


    // ------- Fin REvision taroide
    /*
     INOPUTSSS CAMERA
     */
    public void inputsCamera()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q))
            this.screenInfo.setModeBorders(!this.screenInfo.isModeBorders());

        if(Gdx.input.isKeyJustPressed(Input.Keys.D))
            this.screenInfo.setMostrarControles(!this.screenInfo.isMostrarControles());

        if(Gdx.input.isKeyPressed(Input.Keys.Z))
            this.camera.zoom = 1.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.C))
            this.camera.zoom = 0.080f;

        if(Gdx.input.isKeyPressed(Input.Keys.W))
            if(this.camera.zoom > 0.080f)
                this.camera.zoom -= 0.008f;

        if(Gdx.input.isKeyPressed(Input.Keys.S))
            if(this.camera.zoom <= 1.0)
                this.camera.zoom += 0.008f;

        // MOVIMIENTOS
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.camera.translate(6, 0);
            this.screenInfo.getPosInfoCell().setX(this.screenInfo.getPosInfoCell().getX()+6);
            this.screenInfo.getPosController().setX(this.screenInfo.getPosController().getX()+6);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.camera.translate(-6, 0);
            this.screenInfo.getPosInfoCell().setX(this.screenInfo.getPosInfoCell().getX()-6);
            this.screenInfo.getPosController().setX(this.screenInfo.getPosController().getX()-6);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.camera.translate(0, -6);
            this.screenInfo.getPosInfoCell().setY(this.screenInfo.getPosInfoCell().getY()-6);
            this.screenInfo.getPosController().setY(this.screenInfo.getPosController().getY()-6);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.camera.translate(0, 6);
            this.screenInfo.getPosInfoCell().setY(this.screenInfo.getPosInfoCell().getY()+6);
            this.screenInfo.getPosController().setY(this.screenInfo.getPosController().getY()+6);
        }
    }
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
    }

    // TODO: Resolver el punto del grid y el esapcio
    public void inputCelulas()
    {
        if(Gdx.input.justTouched())
        {
            Vector3 mousePos = this.camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY() , 0));
            int x = Gdx.input.getDeltaX((int) mousePos.x);
            int y = Gdx.input.getDeltaY((int) mousePos.y);

            int deltaX = Math.round (mousePos.x % this.dimensions.x);
            int deltaY = Math.round (mousePos.y % this.dimensions.y);

            if( deltaX != 0 || deltaY !=0)
                mousePos.x -=deltaX;

            if( (y % this.dimensions.y) == 0)
                mousePos.y-=deltaY;


            int finalX = Math.round(mousePos.x/this.dimensions.x);
            int finalY = Math.round (mousePos.y/this.dimensions.y);

            if(this.tablero.getGrid()[finalX][finalY])
                Gdx.app.postRunnable(()->this.tablero.getGrid()[finalX][finalY] = false);
            else
                Gdx.app.postRunnable(()->this.tablero.getGrid()[finalX][finalY] = true);
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
    }

    @Override
    public void resume() {
        super.resume();
    }
}
