package com.dataproject.platforms.Powerups;

import com.dataproject.platforms.Player;
import com.dataproject.platforms.Utilities.Range;

public interface Powerup
{
    void setRarity(double p);
    void setChanceToHarmUser(double p);

    double getRarity();
    double getChanceToHarmUser();

    void animate(float dt);
    void initTextures();

    Range getRange();

    void use(Player self, Player other, boolean harmSelf);
}
