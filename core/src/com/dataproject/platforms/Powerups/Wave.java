package com.dataproject.platforms.Powerups;

import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.dataproject.platforms.Platforms;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.Range;
import finnstr.libgdx.liquidfun.ParticleDef;
import finnstr.libgdx.liquidfun.ParticleGroup;
import finnstr.libgdx.liquidfun.ParticleGroupDef;
import finnstr.libgdx.liquidfun.ParticleSystem;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Wave implements Powerup
{
    private double rarity; //Probability of rolling this powerup
    private double chanceToHarmUser;
    public static final int DROP_AMT = 200;
    public static boolean WAVE_USED = false;
    public static int WAVEGROUP_SPAWN_COUNT = 0;
    public static Vector2 START_POS;

    private static int[] drops;
    private static ParticleDef dropDef;

    private static CircleShape dropShape;
    private static final int radius = 1;

    //Liquidfun
    private static ParticleGroup wave;
    private static ParticleGroupDef waveDef;
    private static ParticleGroupDef.ParticleGroupType waveType;
    private static ParticleSystem psys;


    public Wave()
    {
      //  this.gameWorld = world;

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

    public static void init(ParticleSystem sys)
    {
        psys = sys;
        psys.setParticleRadius(2);
        psys.setParticleGravityScale(10);

        waveDef = new ParticleGroupDef();

        PolygonShape shp = new PolygonShape();
        shp.setAsBox(50,10);

        waveDef.shape = shp;
        waveDef.angle= 5.5f;
        waveDef.flags.add(ParticleDef.ParticleType.b2_waterParticle);


//        for(int i = 0; i<DROP_AMT; i++)
//        {
//            if(i == 0){wave = psys.createParticleGroup(waveDef);}
//            else
//            {
//                psys.joinParticleGroups(wave, psys.createParticleGroup(waveDef));
//            }
//        }
//
//
       // psys.setPaused(true);

        waveDef.lifetime = 5;
        placeWave(new Vector2(-120, Platforms.SCREEN_HEIGHT+75));
    }

    @Override
    public void use(Player affected)
    {
        affected.setTopPlatDynamic();

        if(affected.onRightSide){START_POS = new Vector2(Platforms.SCREEN_WIDTH+75, affected.getTopPlatPos().y);}

        else{START_POS = new Vector2(-75, Platforms.SCREEN_HEIGHT+75);}

        WAVE_USED = true;
    }



    public static void placeWave(Vector2 pos)
    {
        if(pos.x > Platforms.SCREEN_WIDTH/2) //check if spawning on right side of world
        {
            waveDef.angle= -5.5f;
            waveDef.linearVelocity.set(new Vector2(-100000, 0)); //Move wave left

            if(wave!=null)
            {
                wave.applyLinearImpulse(new Vector2(10000000, 0));
            }
        }
        else
        {
            waveDef.angle= 5.5f;
            waveDef.linearVelocity.set(new Vector2(100000, -100000)); //Move wave right

            if(wave!=null)
            {
                wave.applyLinearImpulse(new Vector2(-10000000, 0));
            }
        }



        if(wave!=null && WAVEGROUP_SPAWN_COUNT == 0)
        {
            wave.destroyParticlesInGroup();
        }

        waveDef.position.set(pos);


        if(wave == null){wave = psys.createParticleGroup(waveDef);}
        psys.joinParticleGroups(wave, psys.createParticleGroup(waveDef));



    }
}
