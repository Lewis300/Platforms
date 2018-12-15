package com.dataproject.platforms.Powerups;

import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.Range;

public class Laser  implements Powerup
{
    private double rarity; //Probability of rolling this powerup
    private double chanceToHarmUser;
    private Range rangeInRoll; // Between what two decimal values wil this powerup be rolled. Think of it like the position of the wedge on a spinner


    public Laser(double rarity, double chanceToHarmUser)
    {
        initTextures();
    }

    @Override
    public void use(Player self, Player other)
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

}
