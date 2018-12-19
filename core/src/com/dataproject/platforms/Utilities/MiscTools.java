package com.dataproject.platforms.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dataproject.platforms.Platforms;

import static com.dataproject.platforms.Platforms.ROOT;

public class MiscTools {
    public static BitmapFont font16;
    public static BitmapFont font12;
    public static BitmapFont font24;

    public static Sprite createScaledSprite(Texture texture, int width, int height) {
        Sprite sprite = new Sprite(texture);
        sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite.setSize(width, height);
        return sprite;
    }

    public static Texture createScaledTexture(Texture texture, int width, int height) {
        Sprite sprite = new Sprite(texture);
        sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite.setSize(width, height);
        return sprite.getTexture();
    }

    public static void loadFonts()
    {
//        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        params.size = 16;
//        params.characters = "!QWERTYUIOPASDFGHJKLZXCVBNM1234567890-=qwertyuiop[]asdfghjkl;'zxcvbnm, /:";
//        // set params
//
//        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("Font/gamefont.ttf"));
////        font16 = gen.generateFont(params);
////        font16.setColor(Color.BLACK);
////
////        params.size = 12;
////        font12 = gen.generateFont(params);
////        font12.setColor(Color.BLACK);
//
//        params.size = 24;
//        font24 = gen.generateFont(params);
//        font24.setColor(Color.BLACK);
       // gen.dispose();

        try
        {
            SmartFontGenerator sfg = new SmartFontGenerator();

            font24 = sfg.createFont(Gdx.files.local("generated-fonts\\24_24font.fnt"), "24font", 24);
            font16 = sfg.createFont(Gdx.files.local("generated-fonts\\16_16font.fnt"), "16font", 16);
            font12 = sfg.createFont(Gdx.files.local("generated-fonts\\12_12font.fnt"), "12font", 12);

//            font16 = new BitmapFont(sfg.getFontFile("16font.fnt", 16));
//            font12 = new BitmapFont(sfg.getFontFile("12font.fnt", 12));
            //font24 = sfg.createFont(Gdx.files.local("Font/gamefont.ttf"), "24Font", 24);

        }
        catch (Exception e)
        {
            e.printStackTrace();
           // loadFonts();
        }
    }
//
//    private static void initFonts()
//    {
//        try
//        {
//            font44 = sfg.createFont(Gdx.files.local("Fonts/BPtypewrite.ttf"), "endfont", 44);
//
//            font44.getData().setScale(0.2857142857142857f);
//
//            font440 = new BitmapFont(sfg.getFontFile("levelfont.fnt", 440));
//            font440.getData().setScale(0.2857142857142857f);
//
//            markerFont = new BitmapFont(sfg.getFontFile("levelfont.fnt", 440));
//
//            prefs.putBoolean("createdFont", true);
//            prefs.flush();
//        }
//        catch (Exception e)
//        {
//            initFonts();
//        }
    //}
//
}
