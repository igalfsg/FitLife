package com.flegmann.fitbuddyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;

public class Create_WKO extends Activity
{
    int karma;
    int index;
    String[] exercises = new String[10];
    String name = "workout 1";
    int [] sets = new int [10];
    int [] reps = new int [10];
    //EditText[] miBox_reps = new EditText[10];
    //EditText[] miBox_sets = new EditText[10];
    //TextView [] miName = new TextView[10];
    protected EditText wkoname;
    private ListView listView;
    private ArrayList<CreateWKOItem> items;
    int textid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create__wko);
        //this.getWindow().setSoftInputMode(
          //      WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.create_wko_layout);
        items = new ArrayList<>();

        wkoname = (EditText)findViewById(R.id.wko_name_create);
        listView = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        karma = intent.getIntExtra("karma", 0);
        exercises = intent.getStringArrayExtra("exercises");
        setTitle("Workout Details");
        for (int i = 0; i < index; i++) {
            //System.out.println("exercises " + exercises[i]);
            //textid = getResources().getIdentifier("Create_sets" + i, "id", getPackageName());
            //miBox_sets[i] =(EditText) findViewById(textid);
            //textid = getResources().getIdentifier("Create_reps" + i, "id", getPackageName());
            //miBox_reps[i] =(EditText) findViewById(textid);
            //textid = getResources().getIdentifier("Create_exercise" + i, "id", getPackageName());
            //miName[i] = (TextView)findViewById(textid);
            //miName[i].setText(exercises[i]);
            items.add(new CreateWKOItem(exercises[i], 0, 0));
            //System.out.println("after " + exercises[i]);
        }
        for (int i = index; i < 10; i++){
            textid = getResources().getIdentifier("Create_sets" + i, "id", getPackageName());
            //miBox_sets[i] =(EditText) findViewById(textid);
            textid = getResources().getIdentifier("Create_reps" + i, "id", getPackageName());
            //miBox_reps[i] =(EditText) findViewById(textid);
            //miBox_sets[i].setVisibility(View.GONE);
            //miBox_reps[i].setVisibility(View.GONE);
        }

        listView.setAdapter(new CreateWKOAdapter(this, items, listView));

        View view = findViewById(R.id.imageView1);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveWorkout();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create__wko, menu);
        return true;
    }
    private void saveWorkout()
    {
        ParseUser currentUser = ParseUser.getCurrentUser();
        final String userName = currentUser.getUsername();
        name = wkoname.getText().toString().trim();
        if (name.matches("")) {
            Toast.makeText(this, "Please enter Workout name", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            name.trim();
            ParseObject save_wko = new ParseObject("Workouts");
            save_wko.put("name", name);
            save_wko.put("number_exercises", index);
            for (int i = 0; i < index; i++) {
                save_wko.put("exercise" + i, items.get(i).getName());
                save_wko.put("set" + i, items.get(i).getSets());
                save_wko.put("rep" + i, items.get(i).getReps());
            }
            save_wko.put("type", "Community");
            save_wko.put("Calories", 0);
            save_wko.pinInBackground();
            save_wko.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(Create_WKO.this, "workout saved", Toast.LENGTH_LONG).show();
                        ParseObject new_wko = new ParseObject("my_wko");
                        new_wko.put("user", userName);
                        new_wko.put("name", name);
                        new_wko.pinInBackground();
                        new_wko.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                            }
                        });
                    } else {
                        Toast.makeText(Create_WKO.this, "saved in device", Toast.LENGTH_LONG).show();
                        ParseObject new_wko = new ParseObject("my_wko");
                        new_wko.put("user", userName);
                        new_wko.put("name", name);
                        new_wko.pinInBackground();
                    }
                }
            });

            finish();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.savewko) {
            saveWorkout();
        }

        return super.onOptionsItemSelected(item);
    }

}
