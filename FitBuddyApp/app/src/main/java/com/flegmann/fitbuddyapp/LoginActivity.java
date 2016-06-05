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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends Activity {
    protected EditText mUsername;
    protected EditText mPassword;
    ImageView loginbtn;
    ImageView registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            Intent takeUserHome = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(takeUserHome);
            finish();
        } else {
            // show the signup or login screen
            setContentView(R.layout.activity_login);

            //initialize
            mUsername = (EditText) findViewById(R.id.usernameLoginEditText);
            mPassword = (EditText) findViewById(R.id.passwordLoginEditText);
            loginbtn = (ImageView) findViewById(R.id.btn_login);
            registerbtn = (ImageView) findViewById(R.id.btn_register);
        }

    }

    public void imageClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //get the user inputs
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                //log in the user using parse
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (e == null) {
                            //success user signed up
                            Toast.makeText(LoginActivity.this, "Welcome Back!", Toast.LENGTH_LONG).show();
                            //take me home
                            Intent takeUserHome = new Intent(LoginActivity.this, MainActivity.class);
                            takeUserHome.putExtra("login", 3);
                            startActivity(takeUserHome);
                            finish();
                        } else {
                            // he fucked up son
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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

            case R.id.btn_register:

                Intent takeUsertoschool = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(takeUsertoschool);
                break;

            default:
                break;
        }

    }
}
