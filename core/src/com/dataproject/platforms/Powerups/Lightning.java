package com.dataproject.platforms.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Platforms;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.MiscTools;
import com.dataproject.platforms.Utilities.Range;

public class Lightning implements Powerup
{
    private static final Sprite emblem = MiscTools.createScaledSprite(new Texture("Powerups/ThunderPowerupImageScaledUp.png"), Powerup.EMBLEM_WIDTH, Powerup.EMBLEM_HEIGHT);
    public static final String NAME = "Lightning";


    public static final String BOLT_IMG_PATH = "Powerups/ThunderBoltTexture.png";
    public static final Texture BOLT = new Texture(BOLT_IMG_PATH);
    public static final double CHANCE_TO_HARM_USER = 0.1;

    private static Vector2 drawPos;


    public static void init()
    {
        drawPos = new Vector2();
    }

    private static float timePassed = 0.16f;

    public static void update(float dt)
    {
        timePassed+=dt;
    }


    public static void render(Batch batch, float dt)
    {
        if(timePassed<=0.15f)
        {
            batch.draw(BOLT, drawPos.x, drawPos.y, 20, Platforms.SCREEN_HEIGHT-drawPos.y+20);
        }
    }

    @Override
    public double getRarity() {
        return 0;
    }

    @Override
    public double getChanceToHarmUser() {
        return CHANCE_TO_HARM_USER;
    }

    @Override
    public void animate(float dt) {

    }

    @Override
    public void initTextures() {

    }

    @Override
    public Range getRange() {
        return null;
    }

    @Override
    public void use(Player affected)
    {
        timePassed = 0f;
        drawPos.x = affected.getTopPlatPos().x + Platform.PLATFORM_WIDTH/2 - BOLT.getWidth()/2;
        drawPos.y = affected.getTopPlatPos().y;

        affected.instantlyDestroyPlat(1);
    }

    @Override
    public Sprite getEmblem() {
        return emblem;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getEnemyDetriment()
    {
        return "1";
    }

    @Override
    public String getSelfDetriment() {
        return "2";
    }
}
