package com.dataproject.platforms.Utilities;

public class Range
{
    private double min, max;

    public Range(double min, double max)
    {
        this.min = min;
        this.max = max;
    }

    public boolean isValueInRange(double val)
    {
        if(min<=val && val<max){return true;}

        return false;
    }
}
