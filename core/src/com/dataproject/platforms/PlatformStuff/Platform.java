package com.dataproject.platforms.PlatformStuff;

import box2dLight.RayHandler;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dataproject.platforms.Platforms;

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

    public Platform(World world, Vector2 position)
    {
        gameWorld = world;
        this.position.set(position);

        init();
    }

    private void init()
    {
        initBox2d();
    }

    private void initBox2d()
    {
        //Initialize the platforms BodyDef
            platBodyDef = new BodyDef();
            platBodyDef.type = BodyDef.BodyType.DynamicBody;
            platBodyDef.gravityScale = 0; //Makes sure the platform doesent spontaneously fall
            platBodyDef.position.set(position);

        //Initialize the FixtureDef characteristics
            platShape = new PolygonShape();
            platShape.setAsBox(size.x, size.y);
        /*
            Set characteristics of this object (characteristics are defined by the FixtureDef)
            ...
         */
            platFixDef.restitution = 1;
            platFixDef.shape = platShape;

        //Add body to game world define body with the FixtureDef
            platform = gameWorld.createBody(platBodyDef);
            platform.createFixture(platFixDef);
    }

}
