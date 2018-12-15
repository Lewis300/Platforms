package com.dataproject.platforms.Powerups;

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
    public void use(Player self, Player other) {



    }
}
