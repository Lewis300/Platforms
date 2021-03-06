package com.dataproject.platforms;

import com.badlogic.gdx.graphics.g2d.Batch;
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
    public ArrayList<Platform> plats;
    private World gameWorld;

    private Powerup currentlyRolled;

    private PlayerCharacter character;
//
    public Player(World world, ArrayList<Platform> plats)
    {
        gameWorld = world;
        this.plats = plats;

        for(int i = 0; i<this.plats.size(); i++)
        {
            this.plats.get(i).setOwner(this);
        }

        //Get position from top platform
        Vector2 charPos = new Vector2(plats.get(plats.size()-1).getPosition().x , plats.get(plats.size()-1).getPosition().y + PlayerCharacter.CHARACTER_DIM + Platform.PLATFORM_HEIGHT+10);

        character = new PlayerCharacter(world, charPos);
    }

    public void render(Batch batch, float dt)
    {
        if(character.timePassed > 6){character.timePassed = 0f;}

        character.updatePosition(dt);
        character.draw(batch, 1);
    }

    public Vector2 getTopPlatPos(){return plats.get(plats.size()-1).getPosition();}


    public void setTopPlatDynamic(int amount, boolean selfDestruct)
    {
//        ArrayList<Platform> destroyed = new ArrayList<Platform>();
//        for(Platform p: plats)
//        {
//            if(p.selfDestruct){destroyed.add(p);}
//        }
//
//        plats.removeAll(destroyed);

        for(int i = 0; i<amount; i++)
        {
            plats.get(plats.size()-i-1).setDynamic(selfDestruct);
            GameScreen.PLATS_IN_WORLD--;
        }

    }

    public void setCurrentlyRolledPowerup(Powerup rolled){currentlyRolled = rolled;}

    public Powerup gettCurrentlyRolledPowerup(){return currentlyRolled;}

    public void instantlyDestroyPlat(int amount)
    {
        for(int i = 0; i<amount; i++)
        {
            plats.get(plats.size()-1).destroy();
            GameScreen.PLATS_IN_WORLD--;
        }
    }

    // This method chooses a powerup from the list of powerups currently availible to this player
//    public Powerup roll()
//    {
//        return  ProababilityTools.roll(this, othg);
//    }
}
