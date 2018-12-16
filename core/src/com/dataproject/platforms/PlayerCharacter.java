package com.dataproject.platforms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dataproject.platforms.Utilities.MiscTools;
import com.dataproject.platforms.Utilities.WorldContactListener;


public class PlayerCharacter extends Actor
{
    public static final Texture PLAYERCHAR_TEX = new Texture("Powerups/FireBallTexture.png");
    private Sprite playercharSprite;

    private int playercharWidth;
    private int playercharHeight;
    private World gameWorld;

    private Texture playercharTexture;
    private Body playercharBody;
    private BodyDef playercharBodyDef;
    private FixtureDef playercharFixDef = new FixtureDef();
    private PolygonShape playercharShape;
    private Vector2 size;
    private final Vector2 position = new Vector2();

    private static int playerCount = 1;
    public final int id;

    public PlayerCharacter(World world, Vector2 position)
    {
        id = playerCount;
        playerCount++;

        gameWorld = world;
        this.position.set(position);

        init();
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        float rotation = (float)Math.toDegrees(playercharBody.getAngle());

        if(playercharBody.isActive())
        {
            playercharSprite.setPosition(playercharBody.getPosition().x - size.x, playercharBody.getPosition().y - size.y);
            playercharSprite.setRotation(rotation);
            playercharSprite.draw(batch, 1);
        }

        super.draw(batch, parentAlpha);
    }

    private void initBox2d(){
        //Making the body def for the fireball
        playercharBodyDef = new BodyDef();
        playercharBodyDef.gravityScale = 15;
        playercharBodyDef.type = BodyDef.BodyType.DynamicBody;
        playercharBodyDef.position.set(position);

        //Setting the texture, and getting its width and height to set the size
        playercharWidth = PLAYERCHAR_TEX.getWidth();
        playercharHeight = PLAYERCHAR_TEX.getHeight();
        size = new Vector2(playercharWidth, playercharHeight);
        playercharSprite = MiscTools.createScaledSprite(PLAYERCHAR_TEX, (int)size.x*2, (int)size.y*2);

        //Making the shape according to size
        playercharShape = new PolygonShape();
        playercharShape.setAsBox(size.x, size.y);

        //Setting the fixture def properties
        playercharFixDef.shape = playercharShape;

        //Creating the body in the world
        playercharBody = gameWorld.createBody(playercharBodyDef);

        playercharBody.createFixture(playercharFixDef).setUserData("player_"+id);

    }

    private void init()
    {

        // playercharSprite.setRegionWidth((int)size.x);
        // playercharSprite.setRegionHeight((int)size.y);
        initBox2d();
    }

    public void destroy()
    {
        System.out.println("Bucktee");
        playercharBody.setActive(false);
        WorldContactListener.bodiesToDestroy.add(playercharBody);

    }
}