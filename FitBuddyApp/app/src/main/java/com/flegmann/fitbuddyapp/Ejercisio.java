package com.flegmann.fitbuddyapp;

/**
 * Created by Igal on 7/9/2015.
 */
public class Ejercisio {
    private String name;
    private int iconID;
    private String sets;
    private String reps;
    private String Weight;

    public Ejercisio(int iconID, String name, String reps, String sets, String Weight) {
        this.iconID = iconID;
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.Weight = Weight;

    }

    public int getIconID() {
        return iconID;
    }

    public String getWeight() {
        return Weight;
    }

    public String getName() {
        return name;
    }

    public String getReps() {
        return reps;
    }

    public String getSets() {
        return sets;
    }
}
