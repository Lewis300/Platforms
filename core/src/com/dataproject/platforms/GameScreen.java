package com.dataproject.platforms;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.dataproject.platforms.HudStuff.HudHandler;
import com.dataproject.platforms.HudStuff.PowerupPrompt;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Powerups.Fireball;
import com.dataproject.platforms.Powerups.Lightning;
import com.dataproject.platforms.Powerups.Powerup;
import com.dataproject.platforms.Powerups.Wave;
import com.dataproject.platforms.Utilities.ProababilityTools;
import com.dataproject.platforms.Utilities.WorldContactListener;
import finnstr.libgdx.liquidfun.ParticleDebugRenderer;
import finnstr.libgdx.liquidfun.ParticleSystem;
import finnstr.libgdx.liquidfun.ParticleSystemDef;

import java.util.ArrayList;


public class GameScreen implements Screen
{
    private Batch sb;
    private static final int PPM = 100;
    private static final int INITIAL_PLAT_AMT = 15;
    public static final int HUD_HEIGHT = Platforms.SCREEN_HEIGHT/7;

    //Renderers
    private Box2DDebugRenderer b2dr;
    private Camera gameCam;

    //Box2d World
    private World world;
    private RayHandler rayHandler;
    public static final float AMBIENT_LIGHT = 1f;
    private PointLight sun;
    private Vector2 sunPos;

    private final Vector2 gravity = new Vector2(0f,-9.81f);
    private final boolean allowSleepingObjects = false;

    private BodyDef groundBodyDef;
    private final Vector2 groundPosition = new Vector2(-2, -15);
    private final Vector2 groundSize = new Vector2(Platforms.SCREEN_WIDTH*3, 20);
    private PolygonShape groundShape;
    private final FixtureDef groundFixtureDef = new FixtureDef();
    private Body ground;

    //Platforms
    private ArrayList<Platform> p1_platforms;
    private ArrayList<Platform> p2_platforms;

    //Players
    private Player p1; //on the left
    private Player p2; //on the right

    public Powerup currentRoll;

    //Liquidfun
    private ParticleSystem psys;
    private ParticleDebugRenderer pdr;

    //Rendering
    private float timePassed = 0;

    //Background and SB
    private Sprite background;
    private Texture backtex;

    //Hud
    private HudHandler HH;

    public GameScreen(Batch sb)
    {
        this.sb = sb;
        b2dr = new Box2DDebugRenderer();
        pdr = new ParticleDebugRenderer(Color.BLUE, 100000);
        gameCam = new OrthographicCamera();
        sunPos = new Vector2(Platforms.SCREEN_WIDTH/2, Platforms.SCREEN_HEIGHT/2 -120);

        backtex = new Texture("Powerups/ThunderBoltTexture.png");

        background = new Sprite();
        background.setRegion(backtex);

       // HH = new HudHandler();
    }

    private boolean hudAdded = false;

    public void handleTurn(boolean isItP1Turn)
    {
        if(isItP1Turn == true)
        {
            currentRoll = ProababilityTools.roll(p1, p2);
        }
        else
        {
            currentRoll = ProababilityTools.roll(p2, p1);
        }
    }

    public void handleInput(float dt)
    {
//        HH.handleInput(dt);
    }

    private  boolean spawnednewWave = false;
    private  boolean spawnednewWave2 = false;
    private  boolean spawnednewWave3 = false;
    private  boolean spawnednewWave4 = false;
    public void update(float dt)
    {
        timePassed+=dt;
        handleInput(dt);

        //Destroy bodies before world steps
        world.step(1/60f, 6,3, 1);

        rayHandler.setCombinedMatrix(gameCam.combined);
        //rayHandler.update();
        gameCam.update();

        sb.setProjectionMatrix(gameCam.combined);


        if(timePassed>5 && timePassed<6 && spawnednewWave == false)
        {
            spawnednewWave = true;
            ProababilityTools.roll(p1, p2).use(p2);
        }

        if(timePassed>12 && spawnednewWave2 == false)
        {
            spawnednewWave2 = true;
            ProababilityTools.roll(p2, p1).use(p1);
        }

        if(timePassed>18 && spawnednewWave3 == false)
        {
            spawnednewWave3 = true;
            ProababilityTools.roll(p1, p2).use(p2);
        }

        if(timePassed>24 && spawnednewWave4 == false)
        {
            spawnednewWave4 = true;
            ProababilityTools.roll(p2, p1).use(p1);
        }


        //MAccaroni
        try
        {
            for(Platform p: p1_platforms) {p.update(dt);}
            for(Platform p: p2_platforms) {p.update(dt);}
        }
        catch (Exception e){}

        Wave.update(dt);
        Lightning.update(dt);
    }

    @Override
    public void render(float delta)
    {
        update(delta);

        //Clear screen before rendering next frame
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        Lightning.render(sb, delta);
        Fireball.render(sb, delta);
        sb.end();


        b2dr.render(world, gameCam.combined);
        //HH.render(sb, delta);
        rayHandler.updateAndRender();


        pdr.render(Wave.psys, 1, gameCam.combined);



        WorldContactListener.update(delta);
    }

    private boolean worldInitialized = false;
    private void initializeWorld()
    {
        if(worldInitialized){return;}


        //Initialize Box2d and create world
        //Box2D.init();
        world = new World(gravity, allowSleepingObjects);
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(AMBIENT_LIGHT);
        //rayHandler.setAmbientLight(1,1,1,1);
        sun = new PointLight(rayHandler, 600, Color.YELLOW, 1450, 0,0);
        sun.setXray(true);
        sun.setSoftnessLength(0f);
        sun.setPosition(sunPos);

        //Initialize the ground body and add to the Box2d world
            groundBodyDef = new BodyDef();
            groundBodyDef.type = BodyDef.BodyType.StaticBody;
            groundBodyDef.position.set(groundPosition);

            groundShape = new PolygonShape();
            groundShape.setAsBox(groundSize.x, groundSize.y);
            groundFixtureDef.shape = groundShape;

            ground = world.createBody(groundBodyDef);
            ground.createFixture(groundFixtureDef);


        //Initialize platforms

            int platformSpacing = (int)(gameCam.viewportHeight/27f);

            //Initialize left platforms
            p1_platforms = new ArrayList<Platform>();

            for(int i = 0; i < INITIAL_PLAT_AMT; i++)
            {
                p1_platforms.add(new Platform(world, new Vector2(Platform.PLATFORM_WIDTH + gameCam.viewportWidth/30f, -35+HUD_HEIGHT + gameCam.viewportHeight/10f + (i+1)*platformSpacing + i*Platform.PLATFORM_HEIGHT)));

            }


            //Initialize right platforms

            p2_platforms = new ArrayList<Platform>();

            for(int i = 0; i< INITIAL_PLAT_AMT; i++)
            {
                p2_platforms.add(new Platform(world, new Vector2(gameCam.viewportWidth - Platform.PLATFORM_WIDTH - gameCam.viewportWidth/30f, -35+ HUD_HEIGHT + gameCam.viewportHeight/10f + (i+1)*platformSpacing + i*Platform.PLATFORM_HEIGHT)));
            }


        //Initialize water
            ParticleSystemDef psysDef = new ParticleSystemDef();
            psysDef.pressureStrength = 1000;
            psysDef.destroyByAge = true;
            psysDef.lifetimeGranularity = 10;
            psysDef.maxCount = 2000;
            psysDef.pressureStrength =1000;
            psys = new ParticleSystem(world, psysDef);

        //Initialize Players
            Wave.init(psys, world);
            Fireball.init(world);
            Lightning.init();
            p1 = new Player(world, p1_platforms); p1.onRightSide = false;
            p2 = new Player(world, p2_platforms); p2.onRightSide = true;

        world.setContactListener(new WorldContactListener(world, p1, p2));

        worldInitialized = true;



    }
    
    private void addHud()
    {
        hudAdded = true;
        Gdx.graphics.setDisplayMode(Platforms.SCREEN_WIDTH, Platforms.SCREEN_HEIGHT+HUD_HEIGHT, false);
    }

    @Override
    public void resize(int width, int height)
    {
        gameCam.viewportWidth = width;
        gameCam.viewportHeight = height;
        {gameCam.position.set(width/2f, height/2f, 0);}

        if(!worldInitialized){initializeWorld();}
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose()
    {
        world.dispose();
        rayHandler.dispose();
    }


}
