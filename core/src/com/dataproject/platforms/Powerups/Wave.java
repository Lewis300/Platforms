package com.dataproject.platforms.Powerups;

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
    private static final int DROP_AMT = 10000;
    //private static World gameWorld;

    private static Body[] drops;
    private static BodyDef dropBodyDef;
    private static FixtureDef dropFixDef = new FixtureDef();
    private static CircleShape dropShape;
    private static final int radius = 1;
    //private final Vector2 position = new Vector2();

    //Liquidfun
    private static ParticleGroup wave;
    private static ParticleGroupDef waveDef;
    private static ParticleGroupDef.ParticleGroupType waveType;
    private static ParticleSystem psys;

    public Wave(double rarity, double chanceToHarmUser, World world)
    {
        this.rarity = rarity;
        this.chanceToHarmUser = chanceToHarmUser;
      //  this.gameWorld = world;

    }

    @Override
    public void setRarity(double p)
    {

    }

    @Override
    public void setChanceToHarmUser(double p) {

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
        dropShape = new CircleShape();
        dropShape.setRadius(radius);
//
//        dropFixDef = new FixtureDef();
//        dropFixDef.shape = dropShape;
//        dropFixDef.friction = 0;
//
//        // Create every drop
//        drops = new Body[DROP_AMT];
//        for(int i = 0; i<DROP_AMT; i++)
//        {
//            drops[i] = gameWorld.createBody(dropBodyDef);
//            drops[i].createFixture(dropFixDef);
//        }
        waveDef = new ParticleGroupDef();
        //waveDef.particleCount = 100;
        waveDef.strength = 1;
        waveDef.position.set(new Vector2(0,0));
        //waveDef.color.set(0,0,1,1);
        //waveDef.linearVelocity.set(new Vector2(100,0));


        PolygonShape shp = new PolygonShape();
        shp.setAsBox(10,5);
        waveDef.shape = shp;
        //waveDef.angle= 5.5f;
        //waveDef.angularVelocity=0f;
        //waveDef.linearVelocity.set(1000, 10);

        createWave(new Vector2(50, 50));

    }

    @Override
    public void use(Player self, Player other, boolean harmSelf)
    {
        if(harmSelf)
        {

        }
        else
        {
            if(other.onRightSide)
            {
                createWave(new Vector2(Platforms.SCREEN_WIDTH+75, Platforms.SCREEN_HEIGHT-50));
            }
        }
    }

    private static void createWave(Vector2 pos)
    {
        for (int i = 0; i < 150; i++)
        {
            waveDef.lifetime = 10;

            waveDef.position.set(pos);
            waveDef.groupFlags.add(ParticleGroupDef.ParticleGroupType.b2_particleGroupCanBeEmpty);
            waveDef.color.set(0,0,1,1);
            wave = psys.createParticleGroup(waveDef);
        }
    }
}
