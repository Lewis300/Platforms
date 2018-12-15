package com.dataproject.platforms.Powerups.Projectiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FireballProjectile extends Actor
{
    private final Vector2 INITAL_VELOCITY = new Vector2(0, -20);
    private final double MASS = 0.2;

    private World gameWorld;

    private Body fireBody;
    private BodyDef fireBodyDef;
    private FixtureDef fireFixtureDef;
    private PolygonShape fireShape;

    public FireballProjectile(World world, Vector2 position)
    {
        gameWorld = world;
    }

    private void initBox2d(){
        fireBodyDef = new BodyDef();
        fireBodyDef.type = BodyDef.BodyType.DynamicBody;
    }
}
