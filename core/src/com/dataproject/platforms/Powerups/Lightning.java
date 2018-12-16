package com.dataproject.platforms.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.Range;

public class Lightning implements Powerup
{
    public static final String BOLT_IMG_PATH = "Powerups/ThunderBoltTexture.png";
    public static final Texture BOLT = new Texture(BOLT_IMG_PATH);
    private static Vector2 drawPos;


    public static void init()
    {
        drawPos = new Vector2();
    }

    private static float timePassed = 0f;

    public static void update(float dt)
    {
        timePassed+=dt;
    }


    public static void render(Batch batch, float dt)
    {
        if(timePassed<=0.15f){batch.draw(BOLT, drawPos.x, drawPos.y, 20,150);}
    }

    @Override
    public double getRarity() {
        return 0;
    }

    @Override
    public double getChanceToHarmUser() {
        return 0;
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
}