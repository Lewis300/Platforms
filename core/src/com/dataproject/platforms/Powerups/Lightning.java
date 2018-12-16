package com.dataproject.platforms.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
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

    }
}
