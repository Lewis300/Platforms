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
    private World gameWorld;
//
    public Player(World world, ArrayList<Platform> plats)
    {
        gameWorld = world;
        this.plats = plats;
    }

    public Vector2 getTopPlatPos(){return plats.get(plats.size()-1).getPosition();}


    public void setTopPlatDynamic(int amount, boolean selfDestruct)
    {
        ArrayList<Platform> destroyed = new ArrayList<Platform>();
        for(Platform p: plats)
        {
            if(p.selfDestruct){destroyed.add(p);}
        }

        plats.removeAll(destroyed);

        for(int i = 0; i<amount; i++)
        {
            plats.get(plats.size()-1-i).setDynamic(selfDestruct);
        }
    }

    public void instantlyDestroyPlat(int amount)
    {
        for(int i = 0; i<amount; i++)
        {
            plats.get(plats.size()-1).destroy();
            plats.remove(plats.size()-1);
        }
    }

    // This method chooses a powerup from the list of powerups currently availible to this player
    public Powerup roll()
    {
        return  ProababilityTools.roll(this);
    }
}
