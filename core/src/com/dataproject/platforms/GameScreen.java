package com.dataproject.platforms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.dataproject.platforms.PlatformStuff.Platform;


public class GameScreen implements Screen
{
    private SpriteBatch sb;

    //Box2d World
    private World world;
    private final Vector2 gravity = new Vector2(0f,-9.81f);
    private final boolean allowSleepingObjects = false;

    private BodyDef groundBodyDef;
    private final Vector2 groundPosition = new Vector2(-10, -100);
    private final Vector2 groundSize = new Vector2(50 + Platforms.SCREEN_WIDTH, 10);
    private final PolygonShape groundShape = new PolygonShape();
    private final FixtureDef groundFixtureDef = new FixtureDef();
    private Body ground;

    //Platforms
    private Platform[] p1_platforms;
    private Platform[] p2_platforms;

    public GameScreen(SpriteBatch sb)
    {
        this.sb = sb;

        initializeWorld();

    }

    @Override
    public void render(float delta)
    {
        //Clear screen before rendering next frame
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.end();
    }

    private void initializeWorld()
    {
        //Initialize Box2d and create world
        Box2D.init();
        world = new World(gravity, allowSleepingObjects);

        //Initialize the ground body and add to the Box2d world
            groundBodyDef = new BodyDef();
            groundBodyDef.type = BodyDef.BodyType.StaticBody;
            groundBodyDef.position.set(groundPosition);

            groundShape.setAsBox(groundSize.x, groundSize.y);
            groundFixtureDef.shape = groundShape;

            ground = world.createBody(groundBodyDef);
            ground.createFixture(groundFixtureDef);

        //Initialize platforms

    }

    @Override
    public void resize(int width, int height) {

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

    }


}
