package com.dataproject.platforms;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Powerups.Laser;
import com.dataproject.platforms.Powerups.Powerup;
import com.dataproject.platforms.Powerups.Wave;
import com.dataproject.platforms.Utilities.ProababilityTools;

import java.util.ArrayList;

public class Player
{
    private int PLATFORMS_LEFT = 15;
    public boolean onRightSide = true; //whichever player is created first will have this variable as false
    private ArrayList<Platform> plats;
    private ArrayList<Powerup> powerups;
    private World gameWorld;

    public Player(World world, ArrayList<Platform> plats)
    {
        onRightSide = !onRightSide;
        gameWorld = world;
        powerups = new ArrayList<Powerup>();
        this.plats = plats;
        powerups.add(new Wave(1,0,gameWorld));
    }

    public ArrayList<Powerup> getAvailiblePowerups(){return powerups;}

    public void attack(Player other, Powerup rolled)
    {
        rolled.use(this, other, false);
    }

    public boolean usePowerup(Powerup rolled)
    {
        // determine answer

        boolean answer = true;
        return true;
    }

    public void setTopPlatDynamic()
    {
        plats.get(plats.size()-1).setDynamic();
    }

    // This method chooses a powerup from the list of powerups currently availible to this player
    public Powerup roll()
    {
        return  ProababilityTools.roll(this);
    }
}
