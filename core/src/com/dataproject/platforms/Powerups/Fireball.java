package com.dataproject.platforms.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Platforms;
import com.dataproject.platforms.Powerups.Projectiles.FireballProjectile;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.MiscTools;
import com.dataproject.platforms.Utilities.Range;

import java.util.ArrayList;

public class Fireball implements Powerup
{
    private static final Sprite emblem = MiscTools.createScaledSprite(new Texture("Powerups\\FireBallPowerupImageScaledUp.png"), Powerup.EMBLEM_WIDTH, Powerup.EMBLEM_HEIGHT);
    public static final String NAME = "Fireball";

    private static World gameworld;
    private double rarity = 0.15;
    public static final double CHANCE_TO_HARM_USER =  0.25;
    public static ArrayList<FireballProjectile> fireballs;

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
        if(fireballs != null)
        {
            for(FireballProjectile f: fireballs)
            {
                f.draw(batch, 1);
            }
        }
    }

    @Override
    public void animate(float dt)
    {

    }

    @Override
    public void initTextures() {

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
        int fireballAmount;
        if(HARM_SELF){fireballAmount = 6;}
        else{fireballAmount = 3;}

        HARM_SELF = false;

        if(affected.plats.size()>=fireballAmount)
        {
            affected.setTopPlatDynamic(fireballAmount, false);
        }
        else
        {
            fireballAmount = affected.plats.size();
            affected.setTopPlatDynamic(affected.plats.size(), false);
        }

        fireballs = new ArrayList<FireballProjectile>();
        for(int fireballCounter = 1; fireballCounter <= fireballAmount; fireballCounter++)
        {


            Vector2 currentTopPlatPos = affected.getTopPlatPos();
            Vector2 currentFireballSpawnPoint = currentTopPlatPos;
            currentFireballSpawnPoint.x = (float) (currentTopPlatPos.x -  (Platform.PLATFORM_WIDTH / 2) + (FireballProjectile.FIREBALL_TEX.getWidth() / 2f) + Math.random() * (Platform.PLATFORM_WIDTH - FireballProjectile.FIREBALL_TEX.getWidth()));
            currentFireballSpawnPoint.y = currentTopPlatPos.y + 150;


            fireballs.add(new FireballProjectile(gameworld, currentFireballSpawnPoint));
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
        return "3";
    }

    @Override
    public String getSelfDetriment() {
        return "6";
    }
}