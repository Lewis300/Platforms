package com.dataproject.platforms.Powerups.Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dataproject.platforms.Utilities.MiscTools;
import com.dataproject.platforms.Utilities.WorldContactListener;

public class AirProjectile extends Actor {
    public static final Texture AIR_TEX1 = new Texture("Powerups/WindTextureAnimation1.png");
    public static final Texture AIR_TEX2 = new Texture("Powerups/WindTextureAnimation2.png");
    private Sprite airSprite;

    private int airWidth;
    private int airHeight;
    private World gameWorld;

    private Texture airTexture;
    private Body airBody;
    private BodyDef airBodyDef;
    private FixtureDef airFixDef = new FixtureDef();
    private PolygonShape airShape;
    private Vector2 size;
    private Vector2 AIR_INITIAL_VELOCITY;
    private final Vector2 position = new Vector2();

    private static int airProjectileCount = 0;
    public int airProjectileId = 0;

    public AirProjectile(World world, Vector2 position, Vector2 initalVelocity)
    {
        airProjectileCount++;
        airProjectileId = airProjectileCount;
        gameWorld = world;
        this.position.set(position);

        AIR_INITIAL_VELOCITY = initalVelocity;
        init();
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        float rotation = (float)Math.toDegrees(airBody.getAngle());

        if(airBody.isActive())
        {
            airSprite.setPosition(airBody.getPosition().x - size.x, airBody.getPosition().y - size.y);
            airSprite.setRotation(rotation);
            airSprite.draw(batch, 1);
        }

        super.draw(batch, parentAlpha);
    }

    private void initBox2d(){
        //Making the body def for the air
        airBodyDef = new BodyDef();
        airBodyDef.gravityScale = 0f;
        airBodyDef.type = BodyDef.BodyType.DynamicBody;
        airBodyDef.position.set(position);
        airBodyDef.linearVelocity.set(AIR_INITIAL_VELOCITY);

        //Setting the texture, and getting its width and height to set the size
        airWidth = AIR_TEX1.getWidth();
        airHeight = AIR_TEX1.getHeight();
        size = new Vector2(airWidth/2, airHeight/2);
        airSprite = MiscTools.createScaledSprite(AIR_TEX1, (int)size.x*2, (int)size.y*2);

        //Making the shape according to size
        airShape = new PolygonShape();
        airShape.setAsBox(size.x, size.y);

        //Setting the fixture def properties
        airFixDef.shape = airShape;

        //Creating the body in the world
        airBody = gameWorld.createBody(airBodyDef);

        airBody.createFixture(airFixDef).setUserData("airprojectile_"+ airProjectileId);

    }

    private void init()
    {

        // airSprite.setRegionWidth((int)size.x);
        // airSprite.setRegionHeight((int)size.y);
        initBox2d();
    }

    public void destroy()
    {
        System.out.println("yeet");
        airBody.setActive(false);
        WorldContactListener.bodiesToDestroy.add(airBody);

    }
}
