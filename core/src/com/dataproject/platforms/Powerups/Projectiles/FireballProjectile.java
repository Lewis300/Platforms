package com.dataproject.platforms.Powerups.Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Utilities.MiscTools;
import com.dataproject.platforms.Utilities.WorldContactListener;


public class FireballProjectile extends Actor
{
    public static final Texture FIREBALL_TEX = new Texture("Powerups/FireBallTexture.png");
    private Sprite fireballSprite;

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

    private static int fireballProjectileCount = 0;
    public int fireballProjectileId = 0;

    public FireballProjectile(World world, Vector2 position)
    {
        fireballProjectileCount++;
        fireballProjectileId = fireballProjectileCount;
        gameWorld = world;
        this.position.set(position);

        init();
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        float rotation = (float)Math.toDegrees(fireBody.getAngle());

        if(fireBody.isActive())
        {
            fireballSprite.setPosition(fireBody.getPosition().x - size.x, fireBody.getPosition().y - size.y);
            fireballSprite.setRotation(rotation);
            fireballSprite.draw(batch, 1);
        }

        super.draw(batch, parentAlpha);
    }

    private void initBox2d(){
        //Making the body def for the fireball
        fireBodyDef = new BodyDef();
        fireBodyDef.gravityScale = 15;
        fireBodyDef.type = BodyDef.BodyType.DynamicBody;
        fireBodyDef.position.set(position);

        //Setting the texture, and getting its width and height to set the size
        fireballWidth = FIREBALL_TEX.getWidth();
        fireballHeight = FIREBALL_TEX.getHeight();
        size = new Vector2(fireballWidth,fireballHeight);
        fireballSprite = MiscTools.createScaledSprite(FIREBALL_TEX, (int)size.x*2, (int)size.y*2);

        //Making the shape according to size
        fireShape = new PolygonShape();
        fireShape.setAsBox(size.x, size.y);

        //Setting the fixture def properties
        fireFixDef.shape = fireShape;
        fireFixDef.density = 999;

        //Creating the body in the world
        fireBody = gameWorld.createBody(fireBodyDef);

        fireBody.createFixture(fireFixDef).setUserData("fireball_"+fireballProjectileId);

    }

    private void init()
    {

       // fireballSprite.setRegionWidth((int)size.x);
       // fireballSprite.setRegionHeight((int)size.y);
        initBox2d();
    }

    public void destroy()
    {
        System.out.println("Bucktee");
        fireBody.setActive(false);
        WorldContactListener.bodiesToDestroy.add(fireBody);

    }
}