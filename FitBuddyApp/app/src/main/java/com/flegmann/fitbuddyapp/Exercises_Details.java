package com.flegmann.fitbuddyapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Timer;
import java.util.TimerTask;


public class Exercises_Details extends Activity {
    String objname;
    String videolink;
    String img_name;
    int count = 1;
    int steps;
    int karma;
    protected TextView watchVid;
    protected TextView description;
    protected TextView title;
    protected ImageView image;
    public void imageClick(View view) {
        switch (view.getId()) {
            case R.id.video_button:
                //get the user inputs
                Intent govideo = new Intent(Exercises_Details.this, FullscreenDemoActivity.class );
                govideo.putExtra("vidlink", videolink);
                startActivity(govideo);
                break;

            default:
                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises__details);
        Intent intent = getIntent();
        objname = intent.getStringExtra("name");
        karma = intent.getIntExtra("karma", 0);


        //declarations
        watchVid = (TextView) findViewById(R.id.video_button);
        description = (TextView) findViewById(R.id.details_description);
        title = (TextView) findViewById(R.id.details_title);
        image = (ImageView) findViewById(R.id.details_image);

        //get the info
        title.setText(objname);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Exercises");
        query.fromLocalDatastore();
        query.whereEqualTo("name", objname);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    steps = parseObject.getInt("steps");
                    videolink = parseObject.getString("Video");
                    description.setText(parseObject.getString("description"));
                    img_name = objname;
                    img_name = img_name.replaceAll(" ", "_");
                    img_name = img_name.toLowerCase();
                    System.out.println(img_name);
                    int id1 = getResources().getIdentifier(img_name + count, "drawable", getPackageName());
                    System.out.println(id1);
                    if (id1 == 0){
                        img_name = "image";
                        steps = 1;

                    }
                    int id = getResources().getIdentifier(img_name + count, "drawable", getPackageName());
                    image.setImageResource(id);
                } else {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Exercises");
                    query.whereEqualTo("name", objname);
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, ParseException e) {
                            if (e == null) {
                                steps = parseObject.getInt("steps");
                                videolink = parseObject.getString("Video");
                                description.setText(parseObject.getString("description"));
                                img_name = objname;
                                img_name.replaceAll(" ", "_");
                                img_name.toLowerCase();
                                int id1 = getResources().getIdentifier(img_name + count, "drawable", getPackageName());
                                if (id1 == 0){
                                    img_name = "image";
                                    steps = 1;
                                }
                                int id = getResources().getIdentifier(img_name + count, "drawable", getPackageName());
                                image.setImageResource(id);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Exercises_Details.this);
                                builder.setMessage(e.getMessage());
                                builder.setTitle("Sorry!");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        //close the dialog when clicked
                                        dialogInterface.dismiss();
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });
        int delay = 2000; // delay for 5 sec.
        int period = 2000; // repeat every sec.
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //stuff that updates ui
                        int id = getResources().getIdentifier(img_name + count, "drawable", getPackageName());
                        image.setImageResource(id);

                    }
                });
                count++;
                if (count > steps) {
                    count = 1;
                }
            }
        }, delay, period);



    }



}
