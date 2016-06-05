package com.flegmann.fitbuddyapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class hr extends Activity {

    protected EditText age;
    protected EditText hr;
    protected TextView zone1;
    protected TextView zone2;
    protected TextView zone3;
    double ageval = 0;
    double resthr = 0;
    int thr = 0;
    int hrr = 0;
    int thrmax = 0;
    String result;
    int karma;



    public void imageClick(View view) {
        switch (view.getId()) {
            case R.id.calculate_hr:
                //put calculations here
                try{
                    ageval = Double.parseDouble(age.getText().toString());
                    resthr = Double.parseDouble(hr.getText().toString());
                }
                catch (NumberFormatException e) {
                    // p did not contain a valid double
                    Toast.makeText(hr.this, "Error please try agian", Toast.LENGTH_SHORT).show();
                }
                if (resthr != 0 && (ageval + resthr) < 220) {
                    hrr = (int)(220 - ageval - resthr);
                    thr = (int)(hrr * .59) + (int) resthr;
                    result = "up to: " + thr + " bpm";
                    zone1.setText(result);
                    thr = (int)(hrr * .60) +(int) resthr;
                    thrmax =(int) (hrr * .78) + (int) resthr;
                    result = "from:" + thr + " to " + thrmax + " bpm";
                    zone2.setText(result);
                    thr = (int)(hrr * .79) +(int) resthr;
                    thrmax =(int) (hrr * .90) + (int)resthr;
                    result = "from:" + thr + " to " + thrmax + " bpm";
                    zone3.setText(result);
                }
                else{
                    Toast.makeText(hr.this, "invalid values entered", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.menu_BMI:
                Intent takeUsertoschool = new Intent(hr.this, fitcalc.class);
                takeUsertoschool.putExtra("thing",1);
                takeUsertoschool.putExtra("karma", karma);
                startActivity(takeUsertoschool);
                finish();
                break;
            case R.id.menu_maxrep:
                Intent takemax = new Intent(hr.this, max.class);
                takemax.putExtra("thing",1);
                takemax.putExtra("karma", karma);
                startActivity(takemax);
                finish();
                break;
            case R.id.menu_hr:
                break;

            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr);
        Intent intent = getIntent();
        karma = intent.getIntExtra("karma", 0);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //initialaizations
        age = (EditText)findViewById(R.id.hrage);
        hr = (EditText)findViewById(R.id.hrrhr);

        zone1 = (TextView)findViewById(R.id.hr1);
        zone2 = (TextView)findViewById(R.id.hr2);
        zone3 = (TextView)findViewById(R.id.hr3);

    }


}
