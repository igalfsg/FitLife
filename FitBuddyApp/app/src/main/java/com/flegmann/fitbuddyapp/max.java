package com.flegmann.fitbuddyapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class max extends Activity {

    protected EditText rmrep;
    protected EditText rmWeight;
    protected TextView una;
    protected TextView seis;
    protected TextView ocho;
    protected TextView diez;
    protected TextView doce;
    protected TextView quince;
    double BMI_tot = 0;
    double weightbmi = 0;
    double heightbmi = 0;
    double fakeresult = 0;
    int karma;



    public void imageClick(View view) {
        switch (view.getId()) {
            case R.id.calculate_max:
                try{
                    heightbmi = Double.parseDouble(rmrep.getText().toString());
                    weightbmi = Double.parseDouble(rmWeight.getText().toString());
                }
                catch (NumberFormatException e) {
                    // p did not contain a valid double
                    Toast.makeText(max.this, "Error please try agian", Toast.LENGTH_LONG).show();
                }

                BMI_tot = weightbmi / (1.0278 - (0.0278 * heightbmi));
                DecimalFormat myFormatter = new DecimalFormat("00.00");
                String result = myFormatter.format(BMI_tot);
                una.setText(result);
                fakeresult = BMI_tot * .83;
                result = myFormatter.format(fakeresult);
                seis.setText(result);
                fakeresult = BMI_tot * .78;
                result = myFormatter.format(fakeresult);
                ocho.setText(result);
                fakeresult = BMI_tot * .75;
                result = myFormatter.format(fakeresult);
                diez.setText(result);
                fakeresult = BMI_tot * .70;
                result = myFormatter.format(fakeresult);
                doce.setText(result);
                fakeresult = BMI_tot * .67;
                result = myFormatter.format(fakeresult);
                quince.setText(result);
                break;

            case R.id.menu_hr:
                Intent takeUsertoschool = new Intent(max.this, hr.class);
                takeUsertoschool.putExtra("thing",1);
                takeUsertoschool.putExtra("karma", karma);
                startActivity(takeUsertoschool);
                finish();
                break;
            case R.id.menu_BMI:
                Intent takemax = new Intent(max.this, fitcalc.class);
                takemax.putExtra("thing",1);
                takemax.putExtra("karma", karma);
                startActivity(takemax);
                finish();
                break;
            case R.id.menu_maxrep:
                break;


            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max);
        Intent intent = getIntent();
        karma = intent.getIntExtra("karma", 0);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //initialaizations

        rmrep = (EditText)findViewById(R.id.rmreps);
        rmWeight = (EditText)findViewById(R.id.rmweightt);

        una = (TextView)findViewById(R.id.onerep);
        seis = (TextView)findViewById(R.id.sixreps);
        ocho = (TextView)findViewById(R.id.eightreps);
        diez = (TextView)findViewById(R.id.tenreps);
        doce = (TextView)findViewById(R.id.twelvereps);
        quince = (TextView)findViewById(R.id.fifteenreps);
        //BMI Calculation
/*
        bmicalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    weightbmi = weightfactor * Double.parseDouble(fcweight.getText().toString());

                    heightbmi = heightfactor * Double.parseDouble(fcheight.getText().toString());
                }
                catch (NumberFormatException e) {
                    // p did not contain a valid double
                    Toast.makeText(fitcalc.this, "Error please try agian", Toast.LENGTH_LONG).show();
                }
                BMI_tot = weightbmi / ((heightbmi / 100) * (heightbmi / 100));
                DecimalFormat myFormatter = new DecimalFormat("00.00");
                String result = myFormatter.format(BMI_tot);
                if(BMI_tot < 18.5){
                    result = result + " Underweight";
                }
                else if (BMI_tot < 25){
                    result = result + " Normal weight";
                }
                else if (BMI_tot < 30){
                    result = result + " Overweight";
                }
                else{
                    result = result + " Obese";
                }
                bmires.setText(result);

            }
        });

        rmmaxcalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    heightbmi = Double.parseDouble(rmrep.getText().toString());
                    weightbmi = Double.parseDouble(rmWeight.getText().toString());
                }
                catch (NumberFormatException e) {
                    // p did not contain a valid double
                    Toast.makeText(fitcalc.this, "Error please try agian", Toast.LENGTH_LONG).show();
                }

                BMI_tot = weightbmi / (1.0278 - (0.0278 * heightbmi));
                DecimalFormat myFormatter = new DecimalFormat("00.00");
                String result = myFormatter.format(BMI_tot);
                una.setText(result);
                fakeresult = BMI_tot * .83;
                result = myFormatter.format(fakeresult);
                seis.setText(result);
                fakeresult = BMI_tot * .78;
                result = myFormatter.format(fakeresult);
                ocho.setText(result);
                fakeresult = BMI_tot * .75;
                result = myFormatter.format(fakeresult);
                diez.setText(result);
                fakeresult = BMI_tot * .70;
                result = myFormatter.format(fakeresult);
                doce.setText(result);
                fakeresult = BMI_tot * .67;
                result = myFormatter.format(fakeresult);
                quince.setText(result);

            }
        });
*/
    }


}
