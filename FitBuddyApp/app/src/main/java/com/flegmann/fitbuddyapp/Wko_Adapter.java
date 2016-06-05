package com.flegmann.fitbuddyapp;

/**
 * Created by Igal on 7/7/2015.
 */
public class Wko_Adapter {
    private String name;
    private int iconID;
    private String day;


    public Wko_Adapter(String name, int iconID, String day) {
        super();
        this.name = name;
        this.day = day;
        this.iconID = iconID;
    }

    public String getName() {
        return name;
    }

    public int getIconID() {
        return iconID;
    }

    public String getDay() {
        return day;
    }
}
