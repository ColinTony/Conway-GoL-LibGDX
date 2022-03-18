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
import com.colintony.gameoflife.Utils.ConfigGame;
import com.colintony.gameoflife.Utils.DataInfo;
import com.colintony.gameoflife.Utils.ScreenInfo;

public class GoL extends PantallaAbstract implements Disposable {
    private SpriteBatch batch;
    private DataInfo dataInfo;
    private ScreenInfo screenInfo;
    private OrthographicCamera camera;

    // Inicio GoL variables
    private ShapeRenderer renderer;
    private boolean[][] grid_cells;

    //Dimensiones de una sola celula
    private Vector2 dimensions;
    private FPSLogger fpsLogger; // para checar los fps en consola

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

        this.grid_cells = new boolean[ConfigGame.GRID_WIDTH][ConfigGame.GRID_HEIGTH];

        this.createRandomWorld(0.01984f);

        this.dimensions = new Vector2(ConfigGame.WIDTH_PANTALLA / (float) grid_cells[0].length, ConfigGame.HEIGTH_PANTALLA / (float) grid_cells.length);
    }
    public void createRandomWorld()
    {
        for (int x = 0; x < grid_cells[0].length; x++) {
            for (int y = 0; y < grid_cells[0].length; y++) {
                this.grid_cells[x][y] = MathUtils.randomBoolean();
            }
        }
    }
    public void createWorldEmpty()
    {
        for (int x = 0; x < grid_cells[0].length; x++) {
            for (int y = 0; y < grid_cells[0].length; y++) {
                this.grid_cells[x][y] = MathUtils.randomBoolean();
            }
        }
    }
    // RANDOM BOLLEAN CON CHANCE DE PROBABILIDAD
    public void createRandomWorld(float chance)
    {
        for (int x = 0; x < grid_cells[0].length; x++) {
            for (int y = 0; y < grid_cells[0].length; y++) {
                this.grid_cells[x][y] = MathUtils.randomBoolean(chance);
            }
        }
    }
    public void createWorldAllDie()
    {
        for (int x = 0; x < grid_cells[0].length; x++) {
            for (int y = 0; y < grid_cells[0].length; y++) {
                this.grid_cells[x][y] = false;
            }
        }
    }
    public void createWorldAllAlive()
    {
        for (int x = 0; x < grid_cells[0].length; x++) {
            for (int y = 0; y < grid_cells[0].length; y++) {
                this.grid_cells[x][y] = true;
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        renderer.setProjectionMatrix(camera.combined);
        this.fpsLogger.log();

        final float dt = Gdx.graphics.getDeltaTime();
        this.update(dt);
        this.inputGameStatus();
        this.inputsCamera();
        switch(state)
        {
            case PAUSE:
                break;
            case RUN:
                this.renderer.begin(ShapeRenderer.ShapeType.Filled);
                {
                    for (int x = 0; x < grid_cells[0].length; x++)
                        for (int y = 0; y < grid_cells[0].length; y++)
                            if (grid_cells[x][y])
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

    // metodo update que ira actualizando la pantalla con las reglas.
    public void update(float dt)
    {
        for (int x = 0; x < grid_cells[0].length; x++) {
            for (int y = 0; y < grid_cells[0].length; y++) {
                if (grid_cells[x][y])
                {
                    if(isCrowded(x, y) || isAlone(x, y))
                        die(x, y);
                } else {
                    if(isAlive(x, y))
                        alive(x, y);
                }
            }
        }

    }
    // Funciones de vida o muerte
    // xPos y yPos son las cordenadas de la celula en x-axis y y-axis
    public void die(int xPos , int yPos)
    {
        Gdx.app.postRunnable(() -> grid_cells[xPos][yPos] = false);
    }
    public void alive(int xPos , int yPos)
    {
        Gdx.app.postRunnable(() -> grid_cells[xPos][yPos] = true);
    }

    // Checando las reglas
    public boolean isCrowded(int xPos,int yPos)
    {
        return getVecinos(xPos,yPos) > 3;
    }

    public boolean isAlone(int xPos, int yPos)
    {
        return getVecinos(xPos,yPos) < 2;
    }
    public boolean isAlive(int xPos, int yPos)
    {
        return getVecinos(xPos,yPos) == 3;
    }

    // obtener vecinos
    private int getVecinos(int xPos, int yPos)
    {
        int numVecinos=0;
        numVecinos += getVecinos(xPos, yPos, 1, 0);
        numVecinos += getVecinos(xPos, yPos, -1, 0);

        numVecinos += getVecinos(xPos, yPos, 0, 1);
        numVecinos += getVecinos(xPos, yPos, 0, -1);

        numVecinos += getVecinos(xPos, yPos, 1, 1);
        numVecinos += getVecinos(xPos, yPos, 1, -1);

        numVecinos += getVecinos(xPos, yPos, -1, 1);
        numVecinos += getVecinos(xPos, yPos, -1, -1);
        return numVecinos;
    }
    /*
     Como estamos guardando en el grid 1 y 0 que son
     true o false se puede hacer este operador ternario
     el cual devolvera 1 o 0 dependiedno el valor de la celula
     en la posicion del grid
     */
    private int getVecinos(int xPos, int yPos, int xOffset, int yOffset)
    {
        try{
            return grid_cells[xPos+xOffset][yPos+yOffset] ? 1 : 0 ;
        }catch (IndexOutOfBoundsException e){
        }
        return 0;
    }

    public void inputsCamera()
    {
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
    }

    // TODO: Resolver el punto del grid y el esapcio
    public void inputCelulas()
    {
        if(Gdx.input.justTouched())
        {
            Vector3 mousePos=this.camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY() , 0));
            int x = Gdx.input.getDeltaX();
            int y = Gdx.input.getDeltaY();

            int deltaX = (int)mousePos.x % 5;
            int deltaY = (int)mousePos.y % 5;

            if( deltaX != 0 || deltaY !=0)
                mousePos.x -=deltaX;

            if( (y%5) == 0)
                mousePos.y-=deltaY;

            Gdx.app.postRunnable(()->grid_cells[x][y] = false);

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
