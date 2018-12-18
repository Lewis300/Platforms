package com.dataproject.platforms.Utilities;

import box2dLight.Light;
import com.badlogic.gdx.physics.box2d.*;
import com.dataproject.platforms.GameScreen;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Powerups.Air;
import com.dataproject.platforms.Powerups.Fireball;
import com.dataproject.platforms.Powerups.Projectiles.AirProjectile;
import com.dataproject.platforms.Powerups.Projectiles.FireballProjectile;
import finnstr.libgdx.liquidfun.ParticleBodyContact;
import finnstr.libgdx.liquidfun.ParticleContact;
import finnstr.libgdx.liquidfun.ParticleSystem;

import java.util.ArrayList;

public class WorldContactListener implements ContactListener
{
    private Fixture object;
    public static ArrayList<Body> bodiesToDestroy;
    public static ArrayList<Light> lightsToDestroy;
    private static World gameWorld;

    private Player p1, p2;
//
    public WorldContactListener(World gameWorld, Player p1, Player p2)
    {
        this.gameWorld = gameWorld;
        this.p1 = p1;
        this.p2 = p2;
        bodiesToDestroy = new ArrayList<Body>();
        lightsToDestroy = new ArrayList<Light>();
    }

    public static float lightTime = 0;
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

        for(int i = 0; i<lightsToDestroy.size() && lightTime >= 0.05f; i++)
        {
            Light l = lightsToDestroy.get(i);
            {
                if(l!=null)
                {
                    l.remove();
                    l.dispose();
                }
            }

        }
        if(lightTime >= 0.05f){lightsToDestroy.removeAll(lightsToDestroy); lightTime = 0f;}
        if(lightsToDestroy.size()>0){lightTime+=dt;}


    }

    @Override
    public void beginContact(Contact contact)
    {
       try {
           Fixture A, B;

           A = contact.getFixtureA();
           B = contact.getFixtureB();

           String dataA = A.getUserData().toString();
           String dataB = B.getUserData().toString();

           if (dataA.contains("player") && dataB.contains("platform"))
           {
               if (B.getBody().getType().equals(BodyDef.BodyType.StaticBody))
               {
                   A.getBody().setType(BodyDef.BodyType.KinematicBody);
               }
           }
           else if (dataB.contains("player") && dataA.contains("platform"))
           {
               if (A.getBody().getType().equals(BodyDef.BodyType.StaticBody))
               {
                   B.getBody().setType(BodyDef.BodyType.KinematicBody);
               }
           }
           else if((dataA.contains("player") && dataB.contains("ground")) || (dataB.contains("player") && dataA.contains("ground")))
           {
               System.exit(0);
           }
       }
       catch (NullPointerException e){e.printStackTrace();}
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

            //Checking for collision with platform and fireball
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

            //Checking for collision with platform and airprojectile
            else if(A.getUserData().toString().contains("airprojectile") && B.getUserData().toString().contains("platform"))
            {
//                bodiesToDestroy.add(A.getBody());
//                bodiesToDestroy.add(B.getBody());


                getPlat(B.getUserData().toString()).destroy();
                getAirProjectile(A.getUserData().toString()).destroy();

            }
            else if(A.getUserData().toString().contains("platform") && B.getUserData().toString().contains("airprojectile"))
            {
                //bodiesToDestroy.add(B.getBody());
                //bodiesToDestroy.add(A.getBody());

                getPlat(A.getUserData().toString()).destroy();
                getAirProjectile(B.getUserData().toString()).destroy();
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

    private AirProjectile getAirProjectile(String userdata)
    {
        int id = Integer.parseInt(userdata.substring(14));

        for(AirProjectile a: Air.airprojectiles)
        {
            if(a.airProjectileId == id){return a;}
        }

        return null;
    }
}
