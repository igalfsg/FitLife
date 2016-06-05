package com.flegmann.fitbuddyapp;

/**
 * Created by songnob on 17/08/2015.
 */
public class CreateWKOItem
{
    private String name;
    private int sets;
    private int reps;
    private boolean shouldShowCheckBox;
    public CreateWKOItem(String name,int sets, int reps)
    {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getSets()
    {
        return sets;
    }

    public void setSets(int sets)
    {
        this.sets = sets;
    }

    public int getReps()
    {
        return reps;
    }

    public void setReps(int reps)
    {
        this.reps = reps;
    }

    public boolean isShouldShowCheckBox()
    {
        boolean result = false;
        if(reps != 0 && sets != 0)
        {
            result = true;
        }
        return result;
    }

    private void setShouldShowCheckBox(boolean shouldShowCheckBox)
    {
        this.shouldShowCheckBox = shouldShowCheckBox;
    }
}
