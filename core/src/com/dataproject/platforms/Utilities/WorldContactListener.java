package com.dataproject.platforms.Utilities;

import com.badlogic.gdx.physics.box2d.*;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Powerups.Fireball;
import com.dataproject.platforms.Powerups.Projectiles.FireballProjectile;
import finnstr.libgdx.liquidfun.ParticleBodyContact;
import finnstr.libgdx.liquidfun.ParticleContact;
import finnstr.libgdx.liquidfun.ParticleSystem;

import java.util.ArrayList;

public class WorldContactListener implements ContactListener
{
    private Fixture object;
    public static ArrayList<Body> bodiesToDestroy;
    private static World gameWorld;

    private Player p1, p2;
//
    public WorldContactListener(World gameWorld, Player p1, Player p2)
    {
        this.gameWorld = gameWorld;
        this.p1 = p1;
        this.p2 = p2;
        bodiesToDestroy = new ArrayList<Body>();
    }

    public static void update(float dt)
    {
        for(int i = 0; i<bodiesToDestroy.size(); i++)
        {
            Body b = bodiesToDestroy.get(i);
            if(b!=null)
            {
                gameWorld.destroyBody(b);
            }
        }
        bodiesToDestroy.removeAll(bodiesToDestroy);

    }

    @Override
    public void beginContact(Contact contact)
    {


    }

    @Override
    public void endContact(Contact contact)
    {

    }

    @Override
    public void beginParticleBodyContact(ParticleSystem particleSystem, ParticleBodyContact particleBodyContact)
    {

    }

    @Override
    public void endParticleBodyContact(Fixture fixture, ParticleSystem particleSystem, int i) {

    }

    @Override
    public void beginParticleContact(ParticleSystem particleSystem, ParticleContact particleContact)
    {
    }

    @Override
    public void endParticleContact(ParticleSystem particleSystem, int i, int i1) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold)
    {


    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse)
    {
        try
        {
            Fixture A, B;

            A = contact.getFixtureA();
            B = contact.getFixtureB();

            if(A.getUserData().toString().contains("fireball") && B.getUserData().toString().contains("platform"))
            {
//                bodiesToDestroy.add(A.getBody());
//                bodiesToDestroy.add(B.getBody());


                getPlat(B.getUserData().toString()).destroy();
                getFireballProjectile(A.getUserData().toString()).destroy();

            }
            else if(A.getUserData().toString().contains("platform") && B.getUserData().toString().contains("fireball"))
            {
                //bodiesToDestroy.add(B.getBody());
                //bodiesToDestroy.add(A.getBody());

                getPlat(A.getUserData().toString()).destroy();
                getFireballProjectile(B.getUserData().toString()).destroy();
            }
        }
        catch (NullPointerException e){e.printStackTrace();}
    }

    private Platform getPlat(String userdata)
    {
        int id = Integer.parseInt(userdata.substring(9));

        for(Platform p: p1.plats)
        {
            if(id == p.platId){return p;}
        }
        for(Platform p: p2.plats)
        {
            if(id == p.platId){return p;}
        }

        return null;
    }

    private FireballProjectile getFireballProjectile(String userdata)
    {
        int id = Integer.parseInt(userdata.substring(9));

        for(FireballProjectile f: Fireball.fireballs)
        {
            if(f.fireballProjectileId == id){return f;}
        }

        return null;
    }
}
