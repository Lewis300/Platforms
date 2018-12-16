package com.dataproject.platforms.Utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MiscTools
{
    public static Sprite createScaledSprite(Texture texture, int width, int height)
    {
        Sprite sprite = new Sprite(texture);
        sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite.setSize(width, height);
        return sprite;
    }


}
