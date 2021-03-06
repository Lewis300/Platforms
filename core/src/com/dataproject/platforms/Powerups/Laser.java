package com.dataproject.platforms.Powerups;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.Range;

public class Laser implements Powerup
{
    private double rarity; //Probability of rolling this powerup
    private double chanceToHarmUser;
    private Range rangeInRoll; // Between what two decimal values wil this powerup be rolled. Think of it like the position of the wedge on a spinner


    public Laser(double rarity, double chanceToHarmUser)
    {
        initTextures();
    }

    @Override
    public void use(Player affected)
    {

    }

    @Override
    public double getRarity() {
        return rarity;
    }

    @Override
    public double getChanceToHarmUser() {return chanceToHarmUser; }

    @Override
    public Range getRange() {
        return rangeInRoll;
    }

    @Override
    public void animate(float dt)
    {

    }

    @Override
    public void initTextures()
    {

    }


    @Override
    public Sprite getEmblem() {
        return null;
    }

    @Override
    public String getName() {
        return "";
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
