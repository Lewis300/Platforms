package com.dataproject.platforms.PlatformStuff;

import box2dLight.RayHandler;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dataproject.platforms.Platforms;

public class Platform extends Actor
{
    //Box2d
    private World gameWorld;

    private Body platform;
    private BodyDef platBodyDef;
    private FixtureDef platFixDef = new FixtureDef();
    private PolygonShape platShape;
    private final Vector2 size = new Vector2(Platforms.SCREEN_WIDTH/7f, Platforms.SCREEN_HEIGHT/25f);
    private final Vector2 position = new Vector2();

    public Platform(World world)
    {
        gameWorld = world;

        init();
    }

    private void init()
    {
        initBox2d();
    }

    private void initBox2d()
    {
        initPosition();

        //Initialize the platforms BodyDef
            platBodyDef = new BodyDef();
            platBodyDef.type = BodyDef.BodyType.KinematicBody;
            platBodyDef.position.set(position);

        //Initialize the FixtureDef characteristics
            platShape = new PolygonShape();
            platShape.setAsBox(size.x, size.y);
        /*
            Set characteristics of this object (characteristics are defined by the FixtureDef)
            ...
         */
            platFixDef.shape = platShape;

        //Add body to game world define body with the FixtureDef
            platform = gameWorld.createBody(platBodyDef);
            platform.createFixture(platFixDef);


    }

    private void initPosition()
    {

    }
}
