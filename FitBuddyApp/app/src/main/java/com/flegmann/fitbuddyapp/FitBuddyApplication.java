package com.flegmann.fitbuddyapp;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Administrator on 7/13/15.
 */
public class FitBuddyApplication extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();
        // Add your initialization code here
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "BEFmOu6ru7ulUKCaFaNP8JdGU73RBc4wFfvOjfWp", "dV460EGCxMwhzvRhHQDne2zlYoeOQu2aDypfuTTW");
    }
}
