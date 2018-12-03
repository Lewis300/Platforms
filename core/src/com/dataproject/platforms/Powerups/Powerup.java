package com.dataproject.platforms.Powerups;

public interface Powerup
{
    void setRarity(double p);
    void setChanceToHarmUser(double p);

    double getRarity();
    double getChanceToHarmUser();

    void animate(float dt);
    void initTextures();
}
