package com.flegmann.fitbuddyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;

public class Change_Weight extends ActionBarActivity {
    String id;
    int karma;
    int nexercises;
    //EditText[] miBox = new EditText[10];
    //TextView [] miName = new TextView[10];
    protected View changew;
    String objname;
    int textid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__weight);
        Intent intent = getIntent();
        karma = intent.getIntExtra("karma", 0);
        id = intent.getStringExtra("id");
        nexercises = intent.getIntExtra("number", 0);
        String [] names = intent.getStringArrayExtra("names");
        final int [] weights = intent.getIntArrayExtra("weight");
        objname = intent.getStringExtra("name");

        ArrayList<ChangeWeightAdapter.ChangeWeightItem> items = new ArrayList<>();

        for (int i = 0; i < nexercises; i++) {
            textid = getResources().getIdentifier("weight" + i, "id", getPackageName());
            //[i] =(EditText) findViewById(textid);
            textid = getResources().getIdentifier("exercise" + i, "id", getPackageName());
            //miName[i] = (TextView)findViewById(textid);
            //miBox[i].setText(""+weights[i]);
            //miName[i].setText(names[i]);

            items.add(new ChangeWeightAdapter.ChangeWeightItem(names[i], weights[i]));
        }
        for (int i = nexercises; i < 10; i++){
            textid = getResources().getIdentifier("weight" + i, "id", getPackageName());
            //miBox[i] =(EditText) findViewById(textid);
            //miBox[i].setVisibility(View.GONE);
        }

        final ChangeWeightAdapter adapter = new ChangeWeightAdapter(this, items);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);


        changew = findViewById(R.id.change_Weigths_cw);
        changew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("my_wko");
                // Retrieve the object by id
                query.fromLocalDatastore();
                query.getInBackground(id, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, ParseException e) {
                        //update stuff
                        if (e==null) {
                            int wweights;
                            for (int i = 0; i < nexercises; i++) {
                                //wweights = Integer.parseInt(miBox[i].getText().toString().trim());
                                wweights = adapter.getItem(i).weight;
                                Log.i("su", "Weight : " + wweights);
                                parseObject.put("weight" + i, wweights);
                            }
                            Intent goexcercises =  new Intent(Change_Weight.this, Real_WKO_Dispay.class);
                            goexcercises.putExtra("karma" ,karma);
                            goexcercises.putExtra("name", objname);
                            startActivity(goexcercises);
                            finish();
                                    //karma and objname
                        }
                        else{

                            ParseQuery<ParseObject> query = ParseQuery.getQuery("my_wko");
                            // Retrieve the object by id
                            query.getInBackground(id, new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject parseObject, ParseException e) {
                                    //update stuff
                                    int wweights;
                                    if (e == null) {
                                        //int wweights;
                                        for (int i = 0; i < nexercises; i++) {
                                            //wweights = Integer.parseInt(miBox[i].getText().toString().trim());
                                            wweights = adapter.getItem(i).weight;
                                            parseObject.put("weight" + i, wweights);
                                        }
                                        Intent goexcercises = new Intent(Change_Weight.this, Real_WKO_Dispay.class);
                                        goexcercises.putExtra("karma", karma);
                                        goexcercises.putExtra("name", objname);
                                        startActivity(goexcercises);
                                        finish();
                                        }
                                    else {
                                        ParseObject new_wko = new ParseObject("my_wko");
                                        ParseUser currentUser = ParseUser.getCurrentUser();
                                        final String userName = currentUser.getUsername();
                                        new_wko.put("user", userName);
                                        new_wko.put("name", objname);
                                        for (int i = 0; i < nexercises; i++ ) {
                                            wweights = adapter.getItem(i).weight;
                                            new_wko.put("weight" + i, wweights);
                                        }
                                        new_wko.pinInBackground();
                                        new_wko.saveInBackground(new SaveCallback() {
                                            @Override
                                            public void done(ParseException e) {
                                                if (e == null) {
                                                    Toast.makeText(Change_Weight.this, "Changed Weights", Toast.LENGTH_SHORT).show();
                                                    ;
                                                } else {
                                                    Toast.makeText(Change_Weight.this, "Saved on Phone not on network", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                        Intent goexcercises = new Intent(Change_Weight.this, Real_WKO_Dispay.class);
                                        goexcercises.putExtra("karma", karma);
                                        goexcercises.putExtra("name", objname);
                                        startActivity(goexcercises);
                                        finish();
                                    }
                                }
                            });
                        }
                    }
                });

            }
        });


    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change__weight, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
