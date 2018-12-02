package com.dataproject.platforms;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.dataproject.platforms.PlatformStuff.Platform;


public class GameScreen implements Screen
{
    private Batch sb;
    private final int PPM = 100;
    private final int INITIAL_PLAT_AMT = 15;

    //Renderers
    private Box2DDebugRenderer b2dr;
    private Camera gameCam;

    //Box2d World
    private World world;
    private RayHandler rayHandler;

    private final Vector2 gravity = new Vector2(0f,-9.81f);
    private final boolean allowSleepingObjects = false;

    private BodyDef groundBodyDef;
    private final Vector2 groundPosition = new Vector2(-25, -10);
    private final Vector2 groundSize = new Vector2(Platforms.SCREEN_WIDTH+50, 20);
    private PolygonShape groundShape;
    private final FixtureDef groundFixtureDef = new FixtureDef();
    private Body ground;

    //Platforms
    private Platform[] p1_platforms;
    private Platform[] p2_platforms;

    public GameScreen(Batch sb)
    {
        this.sb = sb;
        b2dr = new Box2DDebugRenderer();
        gameCam = new OrthographicCamera();

        initializeWorld();

    }

    public void update(float dt)
    {
        world.step(1/60f, 6,2);
        rayHandler.update();
        gameCam.update();

        sb.setProjectionMatrix(gameCam.combined);
    }

    @Override
    public void render(float delta)
    {
        update(delta);

        //Clear screen before rendering next frame
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        sb.begin();
//        sb.end();


        rayHandler.render();
        b2dr.render(world, gameCam.combined);
    }

    private void initializeWorld()
    {
        //Initialize Box2d and create world
        Box2D.init();
        world = new World(gravity, allowSleepingObjects);
        rayHandler = new RayHandler(world);

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

            int platformSpacing = Platforms.SCREEN_HEIGHT/30;

            //Initialize left platforms
            p1_platforms = new Platform[INITIAL_PLAT_AMT];

            for(int i = 0; i < INITIAL_PLAT_AMT; i++)
            {
                p1_platforms[i] = new Platform(world, new Vector2(Platform.PLATFORM_WIDTH + Platforms.SCREEN_WIDTH/30f, groundPosition.y + groundSize.y + (i+1)*platformSpacing + i*Platform.PLATFORM_HEIGHT));
            }


            //Initialize right platforms

            p2_platforms = new Platform[INITIAL_PLAT_AMT];

            for(int i = 0; i< INITIAL_PLAT_AMT; i++)
            {
                p2_platforms[i] = new Platform(world, new Vector2(Platforms.SCREEN_WIDTH - Platform.PLATFORM_WIDTH - Platforms.SCREEN_WIDTH/30f, groundPosition.y + groundSize.y + (i+1)*platformSpacing + i*Platform.PLATFORM_HEIGHT));
            }
    }

    @Override
    public void resize(int width, int height)
    {
        gameCam.viewportWidth = width;
        gameCam.viewportHeight = height;
        gameCam.position.set(width/2f, height/2f, 0);
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
