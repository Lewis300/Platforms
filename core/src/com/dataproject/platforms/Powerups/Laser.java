package com.dataproject.platforms.Powerups;

public class Laser  implements Powerup
{
    private static double rarity; //Probability of rolling this powerup
    private static  double chanceToHarmUser;


    public Laser(double rarity, double chanceToHarmUser)
    {
        setRarity(rarity);
        setChanceToHarmUser(chanceToHarmUser);

        initTextures();
    }

    @Override
    public void setRarity(double p) {rarity = p;}

    @Override
    public void setChanceToHarmUser(double p) {chanceToHarmUser = p;}

    @Override
    public double getRarity() {
        return rarity;
    }

    @Override
    public double getChanceToHarmUser() {return chanceToHarmUser; }

    @Override
    public void animate(float dt)
    {

    }

    @Override
    public void initTextures()
    {

    }

}
