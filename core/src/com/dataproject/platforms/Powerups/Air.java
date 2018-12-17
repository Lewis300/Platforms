package com.dataproject.platforms.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.dataproject.platforms.Platforms;
import com.dataproject.platforms.Powerups.Projectiles.AirProjectile;
import com.dataproject.platforms.Powerups.Projectiles.FireballProjectile;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.MiscTools;
import com.dataproject.platforms.Utilities.Range;

import java.util.ArrayList;

public class Air implements Powerup
{
    private static final Sprite emblem = MiscTools.createScaledSprite(new Texture("Powerups/WindPowerupImageScaledUp.png"), Powerup.EMBLEM_WIDTH, Powerup.EMBLEM_HEIGHT);

    private static World gameworld;
    private double rarity = 0.15;
    public static final double CHANCE_TO_HARM_USER =  0.90;
    public static ArrayList<AirProjectile> airprojectiles;
    public static AirProjectile airProjectile;
    Vector2 airInitalVelocity;

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
        if(airProjectile != null)
        {
            airProjectile.draw(batch, 1);
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
    }

    public void use(Player affected)
    {

        Vector2 currentTopPlatPos = affected.getTopPlatPos();
        Vector2 currentAirSpawnPoint = currentTopPlatPos;

        if(affected.onRightSide)
        {
            airInitalVelocity = new Vector2(200, 0);
            currentAirSpawnPoint.x = (float) (350);
        }
        else
        {
            airInitalVelocity = new Vector2(-200, 0);
            currentAirSpawnPoint.x = (float) (450);
        }

        currentAirSpawnPoint.y = currentTopPlatPos.y;
        airProjectile = new AirProjectile(gameworld, currentAirSpawnPoint, airInitalVelocity);
     }

    @Override
    public Sprite getEmblem() {
        return emblem;
    }
}