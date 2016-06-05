package com.flegmann.fitbuddyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


public class MainActivity extends Activity {

    protected ImageView fitcalcmain;
    protected ImageView excersise;
    protected ImageView pre_programs;
    protected ImageView pre_work;
    protected ImageView my_work;
    protected ImageView my_prog;

    int login = 0;
    int test = 0;
    int karma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        test = intent.getIntExtra("login" , login);
        //get karma
        ParseUser currentUser = ParseUser.getCurrentUser();
        final String userName = currentUser.getUsername();
        //Toast.makeText(MainActivity.this, userName, Toast.LENGTH_LONG).show();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("yakkarma");
        query.fromLocalDatastore();
        query.whereEqualTo("user", userName);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    karma = parseObject.getInt("karma");
                    //Toast.makeText(MainActivity.this, "thing " + karma, Toast.LENGTH_LONG).show();
                    karma += 1;
                    parseObject.increment("karma", 1);
                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {

                        }
                    });
                }
                else{
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("yakkarma");
                    query.whereEqualTo("user", userName);
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, ParseException e) {
                            if (e == null) {
                                karma = parseObject.getInt("karma");
                                //Toast.makeText(MainActivity.this, "thing " + karma, Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Can't retrieve points at this time ", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        fitcalcmain = (ImageView) findViewById(R.id.image_fitcalc);
        pre_programs = (ImageView) findViewById(R.id.image_PreProg);
        pre_work = (ImageView) findViewById(R.id.image_pre_work);
        my_work = (ImageView) findViewById(R.id.image_My_work);
        my_prog = (ImageView) findViewById(R.id.image_MY_PROB);
        excersise = (ImageView) findViewById(R.id.image_exercise);

        fitcalcmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gomeasure = new Intent(MainActivity.this, fitcalc.class);
                gomeasure.putExtra("karma", karma);
                startActivity(gomeasure);
            }
        });
        excersise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goexcercises =  new Intent(MainActivity.this, Types_Display.class);
                goexcercises.putExtra("thing",1);
                goexcercises.putExtra("karma", karma);
                startActivity(goexcercises);
            }
        });
        pre_programs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goexcercises =  new Intent(MainActivity.this, Types_Display.class);
                goexcercises.putExtra("thing",2);
                goexcercises.putExtra("karma", karma);
                startActivity(goexcercises);
            }
        });
        pre_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goexcercises =  new Intent(MainActivity.this, Types_Display.class);
                goexcercises.putExtra("thing",3);
                goexcercises.putExtra("karma", karma);
                startActivity(goexcercises);
            }
        });
        my_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goexcercises =  new Intent(MainActivity.this, Types_Display.class);
                goexcercises.putExtra("thing",4);
                goexcercises.putExtra("karma", karma);
                startActivity(goexcercises);
            }
        });
        my_prog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goexcercises =  new Intent(MainActivity.this, Types_Display.class);
                goexcercises.putExtra("thing",5);
                goexcercises.putExtra("karma", karma);
                startActivity(goexcercises);
            }
        });

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {
            // Update information
            updateparse();
        }
        else{
            ParseQuery<ParseObject> queryp = ParseQuery.getQuery("Workouts");
            queryp.whereEqualTo("type", "Legs");
            queryp.fromLocalDatastore();
            queryp.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject parseObject, ParseException e) {
                    if (e == null) {
                        //thre is already stuff saved
                    } else {
                        updateparse();
                    }
                }
            });
        }


    }


    private void updateparse() {
        //get user
        ParseUser currentUser = ParseUser.getCurrentUser();
        final String userName = currentUser.getUsername();
        //pin stuff
        //workouts
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Workouts");
        query.setLimit(1000);
        query.orderByAscending("name");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    //success
                    ParseObject.pinAllInBackground(list);
                } else {
                    Toast.makeText(MainActivity.this, "error updating Workouts", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Wk_Types
        ParseQuery<ParseObject> query_wk_types = ParseQuery.getQuery("Wk_Types");
        query_wk_types.setLimit(1000);
        query_wk_types.orderByAscending("name");
        query_wk_types.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    //success
                    ParseObject.pinAllInBackground(list);
                } else {
                    Toast.makeText(MainActivity.this, "error updating Categories", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Programs
        ParseQuery<ParseObject> query_programs = ParseQuery.getQuery("Programs");
        query_programs.setLimit(1000);
        query_programs.orderByAscending("name");
        query_programs.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    //success
                    ParseObject.pinAllInBackground(list);
                } else {
                    Toast.makeText(MainActivity.this, "error updating Categories", Toast.LENGTH_LONG).show();
                }
            }
        });
        //exercises
        ParseQuery<ParseObject> query_exercises = ParseQuery.getQuery("Exercises");
        query_exercises.setLimit(1000);
        query_exercises.orderByAscending("name");
        query_exercises.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    //success
                    ParseObject.pinAllInBackground(list);
                } else {
                    Toast.makeText(MainActivity.this, "error updating Exercises", Toast.LENGTH_LONG).show();
                }
            }
        });
        //my_programs
        ParseQuery<ParseObject> query_my_prog = ParseQuery.getQuery("my_prog");
        query_my_prog.setLimit(1000);
        query_my_prog.whereEqualTo("user", userName);
        query_my_prog.orderByAscending("name");
        query_my_prog.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    //success
                    ParseObject.pinAllInBackground(list);
                } else {
                    Toast.makeText(MainActivity.this, "error updating My Programs", Toast.LENGTH_LONG).show();
                }
            }
        });
        //yakkarma
        ParseQuery<ParseObject> query_yak = ParseQuery.getQuery("yakkarma");
        query_yak.whereEqualTo("user", userName);
        query_yak.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    //success
                    ParseObject.pinAllInBackground(list);
                } else {
                    Toast.makeText(MainActivity.this, "error updating Points", Toast.LENGTH_LONG).show();
                }
            }
        });
        //my_workouts
        ParseQuery<ParseObject> query_my_wko = ParseQuery.getQuery("my_wko");
        query_my_wko.setLimit(1000);
        query_my_wko.whereEqualTo("user", userName);
        query_my_wko.orderByAscending("name");
        query_my_wko.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    //success
                    ParseObject.pinAllInBackground(list);
                } else {
                    Toast.makeText(MainActivity.this, "error updating My Workouts", Toast.LENGTH_LONG).show();
                }
            }
        });
        //wko_user
        ParseQuery<ParseObject> query_wko_user = ParseQuery.getQuery("Workouts_user");
        query_wko_user.whereEqualTo("user", userName);
        query_wko_user.orderByAscending("name");
        query_wko_user.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    //success
                    ParseObject.pinAllInBackground(list);
                } else {
                    Toast.makeText(MainActivity.this, "error updating weights information", Toast.LENGTH_LONG).show();
                }
            }
        });
    }




}
