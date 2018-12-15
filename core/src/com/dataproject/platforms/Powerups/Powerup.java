package com.dataproject.platforms.Powerups;

import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.Range;

public interface Powerup
{
    double getRarity(); //gets the rarity of rolling this powerup
    double getChanceToHarmUser(); //gets the probability of harming the player using the powerup

    void animate(float dt); //animates the sprite of the powerup
    void initTextures(); //loads textures/sprites of the powerup

    Range getRange();//dw about this for not

    void use(Player affected); //Uses the powerup
}
