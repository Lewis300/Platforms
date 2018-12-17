package com.dataproject.platforms.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dataproject.platforms.Platforms;

public class MiscTools
{
    public static BitmapFont font16;
    public static BitmapFont font12;

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
        params.size = 16;
        params.characters = "!QWERTYUIOPASDFGHJKLZXCVBNM1234567890-=qwertyuiop[]asdfghjkl;'zxcvbnm, /:";
        // set params

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.absolute(Platforms.ROOT+"\\gamefont.ttf"));
        font16 = gen.generateFont(params);
        font16.setColor(Color.BLACK);

        params.size = 12;
        font12 = gen.generateFont(params);
        font12.setColor(Color.BLACK);
        gen.dispose();
    }
}
