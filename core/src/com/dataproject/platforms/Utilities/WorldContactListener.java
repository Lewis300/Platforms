package com.dataproject.platforms.Utilities;

import com.badlogic.gdx.physics.box2d.*;
import finnstr.libgdx.liquidfun.ParticleBodyContact;
import finnstr.libgdx.liquidfun.ParticleContact;
import finnstr.libgdx.liquidfun.ParticleSystem;

import java.util.ArrayList;

public class WorldContactListener implements ContactListener
{
    private Fixture object;
    private static ArrayList<Body> bodiesToDestroy;
    private static World gameWorld;

    public WorldContactListener(World gameWorld)
    {
        this.gameWorld = gameWorld;
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
        Fixture A, B;

        A = contact.getFixtureA();
        B = contact.getFixtureB();

        if(A.getUserData().equals("fireball") && B.getUserData().equals("platform"))
        {
            bodiesToDestroy.add(A.getBody());
            bodiesToDestroy.add(B.getBody());
        }
        else if(A.getUserData().equals("platform") && B.getUserData().equals("fireball"))
        {
            bodiesToDestroy.add(B.getBody());
            bodiesToDestroy.add(A.getBody());
        }
    }

    @Override
    public void beginParticleBodyContact(ParticleSystem particleSystem, ParticleBodyContact particleBodyContact)
    {

    }

    @Override
    public void endParticleBodyContact(Fixture fixture, ParticleSystem particleSystem, int i) {

    }

    @Override
    public void beginParticleContact(ParticleSystem particleSystem, ParticleContact particleContact) {

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


    }
}
