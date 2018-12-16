package com.dataproject.platforms.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.dataproject.platforms.Platforms;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.MiscTools;
import com.dataproject.platforms.Utilities.Range;
import finnstr.libgdx.liquidfun.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Wave implements Powerup
{
    private static final Sprite emblem = MiscTools.createScaledSprite(new Texture("Powerups/WavePowerupImageScaledUp.png"), Powerup.EMBLEM_WIDTH, Powerup.EMBLEM_HEIGHT);

    public static final double CHANCE_TO_HARM_USER = 0.5;

    private double rarity; //Probability of rolling this powerup
    private double chanceToHarmUser;
    public static final int DROP_AMT = 200;
    public static boolean WAVE_USED = false;
    public static int WAVEGROUP_SPAWN_COUNT = 0;
    public static Vector2 START_POS;

    private static World gameWorld;

    //Liquidfun
    private static ParticleGroup wave;
    private static ParticleGroupDef waveDef;
    private static ParticleGroupDef.ParticleGroupType waveType;
    public static ParticleSystem psys;


    public Wave()
    {
      //  this.gameWorld = world;

    }

    public static void update(float dt)
    {

        if(WAVE_USED && WAVEGROUP_SPAWN_COUNT < DROP_AMT)
        {
            placeWave(Wave.START_POS);
            WAVEGROUP_SPAWN_COUNT++;
            if(WAVEGROUP_SPAWN_COUNT == 100){WAVE_USED = false; WAVEGROUP_SPAWN_COUNT = 0;}
        }

        try
        {
            for(int i = 0; i<7; i++)
            {
                psys.destroyParticle(i);
            }
        }
        catch (Exception e){}
    }

    @Override
    public double getRarity() {
        return 0;
    }

    @Override
    public double getChanceToHarmUser() {
        return 0;
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
//
    public static void init(ParticleSystem sys, World wrld)
    {
        gameWorld = wrld;

        psys = sys;
        psys.setParticleRadius(2f);
        psys.setParticleGravityScale(10);

        waveDef = new ParticleGroupDef();

        PolygonShape shp = new PolygonShape();
        shp.setAsBox(2,30);

        waveDef.shape = shp;
        waveDef.angle= 5.5f;
        waveDef.flags.add(ParticleDef.ParticleType.b2_viscousParticle);

        waveDef.strength = 100000;
        //for(int i =0; i<100; i++)placeWave(new Vector2(-120, Platforms.SCREEN_HEIGHT-75));
    }

    private static int useCount = 0;
    @Override
    public void use(Player affected)
    {
        useCount++;

        if(affected.onRightSide){START_POS = new Vector2(Platforms.SCREEN_WIDTH+200, affected.getTopPlatPos().y);}

        else{START_POS = new Vector2(-200, affected.getTopPlatPos().y); System.out.println("FAG");}


        if(affected.plats.size()>=3)
        {
            affected.setTopPlatDynamic(3, true);
        }
        else
        {
            affected.setTopPlatDynamic(affected.plats.size(), true);
        }


        WAVE_USED = true;
    }



    public static void placeWave(Vector2 pos)
    {
        if(WAVEGROUP_SPAWN_COUNT == 0)
        {
            if(pos.x > Platforms.SCREEN_WIDTH/2) //check if spawning on right side of world
            {
                waveDef.angle = 0f;
                waveDef.linearVelocity.set(new Vector2(-1000000, 100000)); //Move wave left

            }
            else
            {
                waveDef.angle = 0f;
                waveDef.linearVelocity.set(new Vector2(1000000, 100000)); //Move wave right

            }
        }

//        if(wave!=null && WAVEGROUP_SPAWN_COUNT == 0)
//        {
//            wave.destroyParticlesInGroup();
//        }

        if(pos.x > Platforms.SCREEN_WIDTH/2){waveDef.position.set(new Vector2(pos.x-0*WAVEGROUP_SPAWN_COUNT, pos.y-10));}
        else {waveDef.position.set(new Vector2(pos.x-0.1f*WAVEGROUP_SPAWN_COUNT, pos.y-10));}


        //if(wave == null){wave = psys.createParticleGroup(waveDef);}
        wave = psys.createParticleGroup(waveDef);



    }

    @Override
    public Sprite getEmblem()
    {
        return emblem;
    }
}
