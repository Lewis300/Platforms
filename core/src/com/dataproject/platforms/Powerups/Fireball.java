package com.dataproject.platforms.Powerups;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.dataproject.platforms.Platforms;
import com.dataproject.platforms.Powerups.Projectiles.FireballProjectile;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.Range;

import java.util.ArrayList;

public class Fireball implements Powerup
{
    private static World gameworld;
    private double rarity = 0.15;
    private double chanceToHarmUser =  0.5;
    private ArrayList<FireballProjectile> fireballs;

    @Override
    public double getRarity() {
        return rarity;
    }

    @Override
    public double getChanceToHarmUser() {
        return chanceToHarmUser;
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

    public static void init(World world)
    {
        gameworld = world;
    }

    public void use(Player affected)
    {
        boolean harmsSelf;

        //number to determine whether to harm user
        double numHarmSelf = Math.random();

        //checks whether number rolled is less that or equal to the set chance
        //to harm user
        if(numHarmSelf <= chanceToHarmUser)
        {
            //if less than 0.15, harm self
            harmsSelf = true;
        }

        else
        {
            //otherwise, harm enemy
            harmsSelf = false;
        }

        if(true)
        {
            affected.setTopPlatDynamic(3, false);
            for(int fireballCounter = 1; fireballCounter <= 3; fireballCounter++)
            {


                Vector2 currentTopPlatPos = affected.getTopPlatPos();
                Vector2 currentFireballSpawnPoint = currentTopPlatPos;
                currentFireballSpawnPoint.x = (float) (currentTopPlatPos.x - (Platforms.SCREEN_WIDTH/7f / 2) + Math.random() * Platforms.SCREEN_WIDTH/7f);
                currentFireballSpawnPoint.y = currentTopPlatPos.y + 150;

                fireballs = new ArrayList<FireballProjectile>();
                fireballs.add(new FireballProjectile(gameworld, currentFireballSpawnPoint));
            }
        }
    }
}