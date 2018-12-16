package com.dataproject.platforms.Utilities;

import com.dataproject.platforms.HudStuff.PowerupPrompt;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Powerups.Fireball;
import com.dataproject.platforms.Powerups.Lightning;
import com.dataproject.platforms.Powerups.Powerup;
import com.dataproject.platforms.Powerups.Wave;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
//;
public class ProababilityTools
{
    private static Wave pup_wave = new Wave();
    private static Fireball pup_fireball = new Fireball();
    private static Lightning pup_lightning = new Lightning();

    public static Player decider;
    public static Player affected;

    public static Powerup roll(Player p1, Player p2)
    {
        //TODO implement way of deciding who gets affected
        decider = p1;
        affected = p2;

        double roll = Math.random();
        double harmRoll = Math.random();

        //return pup_lightning;

        //Roll wave
        if(roll > 0.33 && roll <0.66)
        {
            if(harmRoll <= Wave.CHANCE_TO_HARM_USER)
            {
                affected = p1;
            }
            return pup_wave;
        }

        //Roll fireball
        else if(roll>=0.66)
        {
            if(harmRoll <= Fireball.CHANCE_TO_HARM_USER)
            {
                affected = p1;
            }
            return pup_fireball;
        }

        //Roll lightning
        else
        {
            if(harmRoll <= Lightning.CHANCE_TO_HARM_USER)
            {
                affected = p1;
            }
            return pup_lightning;
        }
    }

    // Returns random integer between min and max
    public static int randInt(int min, int max)
    {
        return min + (int)((max-min + 1)*Math.random());
    }

    // Returns a random float between min and max
    public static float randFloat(float min, float max)
    {
        return min + (float)((max-min + 1)*Math.random());
    }

    // Returns the odds in favour as a string in the form of "X:Y" given the probability in favour as a decimal
    public static String oddsInFavour(double probability)
    {
        int accuracy = Double.toString(probability).length() - 2; //How many decimal places

        long[] probAsFraction = asFraction((long)(probability*Math.pow(10, accuracy)), (long)Math.pow(10, accuracy));

        long[] oddsInFavour = {probAsFraction[0], probAsFraction[1]-probAsFraction[0]};

        return oddsInFavour[0]+":"+oddsInFavour[1];
    }

    // Returns the odds against as a string in the form of "X:Y" given the probability in favour as a decimal
    public static String oddsAgainst(double probability)
    {
        int accuracy = Double.toString(probability).length() - 2; //How many decimal places

        long[] probAsFraction = asFraction((long)(probability*Math.pow(10, accuracy)), (long)Math.pow(10, accuracy));

        return (probAsFraction[1]-probAsFraction[0])+":"+probAsFraction[0];
    }


    // [0] = aerator, [1] = denominator
    private static long[] asFraction(long a, long b)
    {
        long gcd = greatestCommonDenominator(a, b);

        long[] arr = {(long)(a/gcd), (long)(b/gcd)};
        return arr;
    }

    // Returns the greatest common denominator of two longs
    private static long greatestCommonDenominator(long a, long b)
    {
        if (b==0) return a;
        return greatestCommonDenominator(b,a%b);
    }


}
