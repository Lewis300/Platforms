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
    private static final int DROP_AMT = 100;

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
//        dropBodyDef = new BodyDef();
//        dropBodyDef.type = BodyDef.BodyType.DynamicBody;
//
        psys = sys;
        psys.setParticleRadius(2);

        waveDef = new ParticleGroupDef();

        PolygonShape shp = new PolygonShape();
        shp.setAsBox(100,15);

        waveDef.shape = shp;
        waveDef.angle= 5.5f;


        placeWave(new Vector2(-100, Platforms.SCREEN_HEIGHT));
    }

    @Override
    public void use(Player self, Player other)
    {
        other.setTopPlatDynamic();
        placeWave(new Vector2(Platforms.SCREEN_WIDTH+75, Platforms.SCREEN_HEIGHT-50));
    }



    private static void placeWave(Vector2 pos)
    {
        if(pos.x > Platforms.SCREEN_WIDTH/2) //check if spawning on right side of world
        {
            waveDef.linearVelocity.set(new Vector2(-100000, -10000)); //Move wave left
        }
        else
        {
            waveDef.linearVelocity.set(new Vector2(100000, -10000)); //Move wave right
        }

        waveDef.position.set(pos);
        for(int i = 0; i<DROP_AMT; i++)
        {

           psys.createParticleGroup(waveDef);
        }
    }
}
