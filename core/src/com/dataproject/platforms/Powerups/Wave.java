package com.dataproject.platforms.Powerups;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.Range;

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

    public static void init(World gameWorld)
    {
//        dropBodyDef = new BodyDef();
//        dropBodyDef.type = BodyDef.BodyType.DynamicBody;
//
//        dropShape = new CircleShape();
//        dropShape.setRadius(radius);
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
    }

    @Override
    public void use(Player self, Player other)
    {
    }
}
