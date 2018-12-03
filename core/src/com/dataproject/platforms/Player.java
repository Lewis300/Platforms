package com.dataproject.platforms;

import com.badlogic.gdx.math.Interpolation;
import com.dataproject.platforms.Powerups.Laser;
import com.dataproject.platforms.Powerups.Powerup;
import com.dataproject.platforms.Utilities.ProababilityTools;

import java.util.ArrayList;

public class Player
{
    private int PLATFORMS_LEFT = 15;
    private ArrayList<Powerup> powerups;

    public Player()
    {
        powerups = new ArrayList<Powerup>();
        powerups.add(new Laser(1,0));
    }

    public ArrayList<Powerup> getAvailiblePowerups(){return powerups;}

    // This method chooses a powerup from the list of powerups currently availible to this player
    public Powerup roll()
    {
        return  ProababilityTools.roll(this);
    }
}
