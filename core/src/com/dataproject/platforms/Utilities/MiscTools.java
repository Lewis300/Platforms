package com.dataproject.platforms.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MiscTools
{
    public static BitmapFont font20;

    public static Sprite createScaledSprite(Texture texture, int width, int height)
    {
        Sprite sprite = new Sprite(texture);
        sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite.setSize(width, height);
        return sprite;
    }

    public static Texture createScaledTexture(Texture texture, int width, int height)
    {
        Sprite sprite = new Sprite(texture);
        sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite.setSize(width, height);
        return sprite.getTexture();
    }

    public static void loadFonts()
    {
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 20;
        params.color.set(Color.BLACK);
        params.characters = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890-=qwertyuiop[]asdfghjkl;'zxcvbnm, /";
        // set params

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("gamefont.ttf"));
        font20 = gen.generateFont(params);
        gen.dispose();
    }
}
