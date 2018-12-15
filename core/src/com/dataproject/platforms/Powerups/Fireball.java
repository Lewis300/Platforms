package com.dataproject.platforms.Powerups;

import com.dataproject.platforms.Powerups.Projectiles.FireballProjectile;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.Range;

public class Fireball implements Powerup
{
    private double rarity = 0.15;
    private double chanceToHarmUser =  0.5;

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

    @Override
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

        if(harmsSelf)
        {
            for(int fireball = 1; fireball <= 3; fireball++)
            {
                //FireballProjectile currentFireball = new FireballProjectile();
                affected.setTopPlatDynamic();
            }
        }
    }
}
