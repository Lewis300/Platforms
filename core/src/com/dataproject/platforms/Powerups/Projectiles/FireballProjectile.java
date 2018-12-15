package com.dataproject.platforms.Powerups.Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dataproject.platforms.PlatformStuff.Platform;

import java.util.ArrayList;

public class FireballProjectile extends Actor
{
    private int fireballWidth;
    private int fireballHeight;
    private World gameWorld;

    private Texture fireTexture;
    private Body fireBody;
    private BodyDef fireBodyDef;
    private FixtureDef fireFixDef = new FixtureDef();
    private PolygonShape fireShape;
    private Vector2 size;
    private final Vector2 position = new Vector2();

    public FireballProjectile(World world, Vector2 position)
    {
        gameWorld = world;
        this.position.set(position);

        init();
    }

    private void initBox2d(){
        //Making the body def for the fireball
        fireBodyDef = new BodyDef();
        fireBodyDef.gravityScale = 3;
        fireBodyDef.type = BodyDef.BodyType.DynamicBody;

        //Setting the texture, and getting its width and height to set the size
        fireTexture = new Texture("Powerups/FireBallPowerupImage.png");
        fireballWidth = fireTexture.getWidth();
        fireballHeight = fireTexture.getHeight();
        size = new Vector2(fireballWidth,fireballHeight);

        //Making the shape according to size
        fireShape = new PolygonShape();
        fireShape.setAsBox(size.x, size.y);

        //Setting the fixture def properties
        fireFixDef.shape = fireShape;

        //Creating the body in the world
        fireBody = gameWorld.createBody(fireBodyDef);
        fireBody.createFixture(fireFixDef);

        /*
        Need to find way to set texture on box2d object
         */
    }

    private void init()
    {
        initBox2d();
    }

    public void destroy()
    {
        fireBody.setActive(false);
    }
}