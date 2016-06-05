package com.flegmann.fitbuddyapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class RegisterActivity extends Activity {

    //declare fields
    protected EditText mUsername;
    protected EditText mUserEmail;
    protected EditText mUserPassword;
    protected ImageView mRegisterButton;
    protected EditText mBirth;
    protected EditText mWeight;
    String sex = "male";
    String peso = "kg";
    int cardio = 0;
    int wl = 0;
    int bb = 0;
    int strength = 0;
    int sports = 0;
    int group = 0;
    ImageView malecb;
    ImageView fecb;
    ImageView kgscb;
    ImageView lbscb;
    ImageView wlimage;
    ImageView stimage;
    ImageView cardioimage;
    ImageView gtimage;
    ImageView sportsimage;
    ImageView bbimage;


    public void imageClick(View view) {
        //Implement image click function
        switch(view.getId()) {
            case R.id.radio_male:
                sex = "male";
                malecb.setImageResource(R.drawable.rd);
                fecb.setImageResource(R.drawable.rd_of);
                //Toast.makeText(MainActivity.this, "make", Toast.LENGTH_LONG).show();
                break;
            case R.id.radio_female:
                sex = "female";
                malecb.setImageResource(R.drawable.rd_of);
                fecb.setImageResource(R.drawable.rd);
                //Toast.makeText(MainActivity.this, "female", Toast.LENGTH_LONG).show();
                break;
            case R.id.maletv:
                sex = "male";
                malecb.setImageResource(R.drawable.rd);
                fecb.setImageResource(R.drawable.rd_of);
                //Toast.makeText(MainActivity.this, "make", Toast.LENGTH_LONG).show();
                break;
            case R.id.femaletv:
                sex = "female";
                malecb.setImageResource(R.drawable.rd_of);
                fecb.setImageResource(R.drawable.rd);
                //Toast.makeText(MainActivity.this, "female", Toast.LENGTH_LONG).show();
                break;
        }
        switch(view.getId()) {
            case R.id.radio_kg:
                peso = "kg";
                lbscb.setImageResource(R.drawable.rd_of);
                kgscb.setImageResource(R.drawable.rd);
                break;

            case R.id.radio_lbs:
                peso = "lbs";
                kgscb.setImageResource(R.drawable.rd_of);
                lbscb.setImageResource(R.drawable.rd);
                break;
            case R.id.kgstv:
                peso = "kg";
                lbscb.setImageResource(R.drawable.rd_of);
                kgscb.setImageResource(R.drawable.rd);
                break;

            case R.id.lbstv:
                peso = "lbs";
                kgscb.setImageResource(R.drawable.rd_of);
                lbscb.setImageResource(R.drawable.rd);
                break;
            // interests
            case R.id.wlim:
                if (wl == 0){
                    wl = 1;
                    wlimage.setImageResource(R.drawable.tick);
                }
                else{
                    wl = 0;
                    wlimage.setImageResource(R.drawable.chk_un);
                }
                break;
            case R.id.wltv:
                if (wl == 0){
                    wl = 1;
                    wlimage.setImageResource(R.drawable.tick);
                }
                else{
                    wl = 0;
                    wlimage.setImageResource(R.drawable.chk_un);
                }
                break;
            case R.id.cardioim:
                if (cardio == 0){
                    cardio = 1;
                    cardioimage.setImageResource(R.drawable.tick);
                }
                else{
                    cardio = 0;
                    cardioimage.setImageResource(R.drawable.chk_un);
                }
                break;
            case R.id.cardiotv:
                if (cardio == 0){
                    cardio = 1;
                    cardioimage.setImageResource(R.drawable.tick);
                }
                else{
                    cardio = 0;
                    cardioimage.setImageResource(R.drawable.chk_un);
                }
                break;
            case R.id.gtim:
                if (group == 0){
                    group = 1;
                    gtimage.setImageResource(R.drawable.tick);
                }
                else{
                    group = 0;
                    gtimage.setImageResource(R.drawable.chk_un);
                }
                break;
            case R.id.gttv:
                if (group == 0){
                    group = 1;
                    gtimage.setImageResource(R.drawable.tick);
                }
                else{
                    group = 0;
                    gtimage.setImageResource(R.drawable.chk_un);
                }
                break;
            case R.id.sportsim:
                if (sports == 0){
                    sports = 1;
                    sportsimage.setImageResource(R.drawable.tick);
                }
                else{
                    sports = 0;
                    sportsimage.setImageResource(R.drawable.chk_un);
                }
                break;
            case R.id.sportstv:
                if (sports == 0){
                    sports = 1;
                    sportsimage.setImageResource(R.drawable.tick);
                }
                else{
                    sports = 0;
                    sportsimage.setImageResource(R.drawable.chk_un);
                }
                break;
            case R.id.bbim:
                if (bb == 0){
                    bb = 1;
                    bbimage.setImageResource(R.drawable.tick);
                }
                else{
                    bb = 0;
                    bbimage.setImageResource(R.drawable.chk_un);
                }
                break;
            case R.id.bbtv:
                if (bb == 0){
                    bb = 1;
                    bbimage.setImageResource(R.drawable.tick);
                }
                else{
                    bb = 0;
                    bbimage.setImageResource(R.drawable.chk_un);
                }
                break;
            case R.id.stim:
                if (strength == 0){
                    strength = 1;
                    stimage.setImageResource(R.drawable.tick);
                }
                else{
                    strength = 0;
                    stimage.setImageResource(R.drawable.chk_un);
                }
                break;
            case R.id.sttv:
                if (strength == 0){
                    strength = 1;
                    stimage.setImageResource(R.drawable.tick);
                }
                else{
                    strength = 0;
                    stimage.setImageResource(R.drawable.chk_un);
                }
                break;
            //register button
            case R.id.registerButton:
                // get info from fields
                final String username = mUsername.getText().toString().trim();
                String password = mUserPassword.getText().toString().trim();
                String email = mUserEmail.getText().toString().trim();
                String birth = mBirth.getText().toString().trim();
                String weight = mWeight.getText().toString().trim();
                //send it to parse
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.put("peso", peso);
                user.put("sex", sex);
                user.put("birthday", birth);
                user.put("weight", weight);
                user.put("wl", wl);
                user.put("sports", sports);
                user.put("cardio", cardio);
                user.put("strength", strength);
                user.put("group", group);
                user.put("bb", bb);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // user signed up seccessfuly
                            //add karma
                            Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_LONG).show();
                            ParseObject karma = new ParseObject("yakkarma");
                            karma.put ("user", username);
                            karma.put("karma", 20);
                            karma.put ("wko_completed", 0);
                            karma.put ("prog_completed", 0);
                            karma.pinInBackground();
                            karma.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        //Toast.makeText(Exersise_Display.this, "Workout Saved", Toast.LENGTH_LONG).show();;
                                    } else {
                                        // Toast.makeText(Exersise_Display.this, "Saved on Phone not on network", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            //take user to homepage
                            Intent takeUserHome = new Intent(RegisterActivity.this, MainActivity.class);
                            takeUserHome.putExtra("login",3);
                            startActivity(takeUserHome);
                        } else {
                            // there was an error
                            // he fucked up son
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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

                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);






        mUsername = (EditText)findViewById(R.id.usernameRegisterEditText);
        mUserEmail = (EditText)findViewById(R.id.emailRegisterEditText);
        mUserPassword = (EditText)findViewById(R.id.passwordRegisterEditText);
        mRegisterButton = (ImageView)findViewById(R.id.registerButton);
        mBirth = (EditText)findViewById(R.id.Birth);
        mWeight = (EditText)findViewById(R.id.Weight);
        malecb= (ImageView) findViewById(R.id.radio_male);
        fecb= (ImageView) findViewById(R.id.radio_female);
        kgscb= (ImageView) findViewById(R.id.radio_kg);
        lbscb= (ImageView) findViewById(R.id.radio_lbs);
        wlimage= (ImageView) findViewById(R.id.wlim);
        stimage= (ImageView) findViewById(R.id.stim);
        cardioimage= (ImageView) findViewById(R.id.cardioim);
        gtimage= (ImageView) findViewById(R.id.gtim);
        sportsimage= (ImageView) findViewById(R.id.sportsim);
        bbimage= (ImageView) findViewById(R.id.bbim);



    }

}
