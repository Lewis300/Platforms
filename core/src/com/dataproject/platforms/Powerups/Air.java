package com.dataproject.platforms.Powerups;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.dataproject.platforms.GameScreen;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Platforms;
import com.dataproject.platforms.Powerups.Projectiles.AirProjectile;
import com.dataproject.platforms.Powerups.Projectiles.FireballProjectile;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.MiscTools;
import com.dataproject.platforms.Utilities.Range;

import java.util.ArrayList;

public class Air implements Powerup
{
    private static final Sprite emblem = MiscTools.createScaledSprite(new Texture("Powerups\\WindPowerupImageScaledUp.png"), Powerup.EMBLEM_WIDTH, Powerup.EMBLEM_HEIGHT);
    public static final String NAME = "Wind";

    private static World gameworld;
    private double rarity = 0.15;
//    private static int platformSpace;
    public static final double CHANCE_TO_HARM_USER =  0.9;
    public static ArrayList<AirProjectile> airprojectiles;
    Vector2 airInitalVelocity;
    public static boolean HARM_SELF = false;

    @Override
    public double getRarity() {
        return rarity;
    }

    @Override
    public double getChanceToHarmUser() {
        return CHANCE_TO_HARM_USER;
    }

    public static void render(Batch batch, float dt)
    {
        if(airprojectiles != null)
        {
            for(AirProjectile a: airprojectiles)
            {
                a.draw(batch, 1);
            }
        }
    }

    @Override
    public void animate(float dt)
    {

    }

    @Override
    public void initTextures(){

    }

    @Override
    public Range getRange() {
        return null;
    }

    public static void init(World world)
    {
        gameworld = world;
//        platformSpace = platformSpacing;
    }

    public void use(Player affected)
    {
        int airAmount;
        if(HARM_SELF)
        {
            airAmount = 1;
        }

        else
        {
            airAmount = 5;
        }
        HARM_SELF = false;

        airprojectiles = new ArrayList<AirProjectile>();
        Vector2 currentTopPlatPos = affected.getTopPlatPos();
        Vector2 currentAirSpawnPoint = currentTopPlatPos;
        float highestYSpawnPoint = currentTopPlatPos.y;

        if(affected.plats.size() < airAmount)
        {
            airAmount = affected.plats.size();
        }

        if(affected.onRightSide)
        {
           for(int i = 0; i < airAmount; i++)
            {
                airInitalVelocity = new Vector2(200, 0);
                currentAirSpawnPoint.x = (float) (Platforms.SCREEN_WIDTH / 2 - i * 2);
                currentAirSpawnPoint.y = highestYSpawnPoint;
                airprojectiles.add(new AirProjectile(gameworld, currentAirSpawnPoint, airInitalVelocity));
                highestYSpawnPoint -= (float)(GameScreen.platformSpacing + Platform.PLATFORM_HEIGHT);
                GameScreen.PLATS_IN_WORLD--;
            }
        }
        else
        {
            for(int i = 0; i < airAmount; i++)
            {
                airInitalVelocity = new Vector2(-200, 0);
                currentAirSpawnPoint.x = (float) (Platforms.SCREEN_WIDTH / 2 - i * 2);
                currentAirSpawnPoint.y = highestYSpawnPoint;
                airprojectiles.add(new AirProjectile(gameworld, currentAirSpawnPoint, airInitalVelocity));
                highestYSpawnPoint -= (float)(GameScreen.platformSpacing + Platform.PLATFORM_HEIGHT);
                GameScreen.PLATS_IN_WORLD--;
            }
        }
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
        return "5";
    }

    @Override
    public String getSelfDetriment() {
        return "1";
    }
}