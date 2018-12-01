package com.dataproject.platforms.Utilities;

public class ProababilityTools
{
    public static int randInt(int min, int max)
    {
        return min + (int)((max-min + 1)*Math.random());
    }

    public static float randFloat(float min, float max)
    {
        return min + (float)((max-min + 1)*Math.random());
    }

    public static String oddsInFavour(double probability)
    {
        int accuracy = Double.toString(probability).length() - 2; //How many decimal places
        //long gcd = greatestCommonDenominator(probability*10*accuracy, 10*accuracy);

        int[] probAsFraction = asFraction((int)(probability*accuracy*10), accuracy*10);

        return probAsFraction[0]+":"+(probAsFraction[1]-probAsFraction[0]);
    }

    public static int[] asFraction(double a, double b)
    {
        int accuaracyA = Double.toString(a).length() - 2;
        int accuracyB = Double.toString(b).length() - 2;

        return asFraction((long)(a*accuaracyA*10), (long)(b*accuracyB*10));
    }

    private static int[] asFraction(long a, long b) {
        long gcd = greatestCommonDenominator(a, b);
        //return (a / gcm) + "/" + (b / gcm);

        int[] arr = {(int)(a/gcd), (int)(b/gcd)};
        return arr;
    }

    private static long greatestCommonDenominator(long a, long b) {
        return b == 0 ? a : greatestCommonDenominator(b, a % b);
    }
}
