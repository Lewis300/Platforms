package com.dataproject.platforms.PlatformStuff;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dataproject.platforms.Platforms;
import com.dataproject.platforms.Powerups.Powerup;

public class Platform extends Actor
{
    public static final float PLATFORM_WIDTH = Platforms.SCREEN_WIDTH/7f;
    public static final float PLATFORM_HEIGHT = Platforms.SCREEN_HEIGHT/100f;

    //Box2d
    private World gameWorld;

    private Body platform;
    private BodyDef platBodyDef;
    private FixtureDef platFixDef = new FixtureDef();
    private PolygonShape platShape;
    private final Vector2 size = new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT);
    private final Vector2 position = new Vector2();

    //Animation
    private boolean animateDestroy = false;
    public boolean selfDestruct = false;

    public Platform(World world, Vector2 position)
    {
        gameWorld = world;
        this.position.set(position);

        init();
    }

    public void update(float dt)
    {
        if(selfDestruct){deathClock+=dt;}

        if(deathClock>5.0f){destroy();}
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        if(animateDestroy)
        {
            // Animate destruction
        }
        super.draw(batch, parentAlpha);
    }

    private void init()
    {
        initBox2d();
    }

    public Vector2 getPosition() {
        return position;
    }

    private void initBox2d()
    {
        //Initialize the platforms BodyDef
            platBodyDef = new BodyDef();
            platBodyDef.type = BodyDef.BodyType.StaticBody;
            platBodyDef.gravityScale = 4; //Makes sure the platform doesent spontaneously fall
            platBodyDef.position.set(position);
           // platBodyDef.active = false;

        //Initialize the FixtureDef characteristics
            platShape = new PolygonShape();
            platShape.setAsBox(size.x, size.y);
        /*
            Set characteristics of this object (characteristics are defined by the FixtureDef)
            ...
         */
            platFixDef.restitution = 1f;
            platFixDef.shape = platShape;
            platFixDef.density = 4;
        //Add body to game world define body with the FixtureDef
            platform = gameWorld.createBody(platBodyDef);
            platform.createFixture(platFixDef).setUserData("platform");
    }

    private float deathClock = 0f;
    public void setDynamic(boolean selfDestruct)
    {
        platform.setType(BodyDef.BodyType.DynamicBody);
        //platform.setActive(true);
        platform.setGravityScale(10);
        this.selfDestruct = selfDestruct;
    }

    public void setActive(boolean b){platform.setActive(b);}

    public void destroy()
    {
       animateDestroy = true;
       platform.setActive(false);
       gameWorld.destroyBody(platform);
    }

}
