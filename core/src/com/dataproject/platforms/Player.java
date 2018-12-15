package com.dataproject.platforms;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.physics.box2d.World;
import com.dataproject.platforms.Powerups.Laser;
import com.dataproject.platforms.Powerups.Powerup;
import com.dataproject.platforms.Powerups.Wave;
import com.dataproject.platforms.Utilities.ProababilityTools;

import java.util.ArrayList;

public class Player
{
    private int PLATFORMS_LEFT = 15;
    private ArrayList<Powerup> powerups;
    private World gameWorld;

    public Player(World world)
    {
        gameWorld = world;
        powerups = new ArrayList<Powerup>();
        powerups.add(new Wave(1,0,gameWorld));
    }

    public ArrayList<Powerup> getAvailiblePowerups(){return powerups;}

    // This method chooses a powerup from the list of powerups currently availible to this player
    public Powerup roll()
    {
        return  ProababilityTools.roll(this);
    }
}
