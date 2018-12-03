package com.dataproject.platforms.Utilities;

import com.dataproject.platforms.Player;
import com.dataproject.platforms.Powerups.Powerup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProababilityTools
{
    public static Powerup roll(Player p)
    {
        double roll = Math.random();
        for(Powerup pup: p.getAvailiblePowerups())
        {
            boolean isPupRolled = pup.getRange().isValueInRange(roll);
            if(isPupRolled){return pup;}
        }
        return null;
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

        long[] probAsFraction = asFraction((long)(probability*Math.pow(10, accuracy)), (long)Math.pow(10, accuracy));;

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
