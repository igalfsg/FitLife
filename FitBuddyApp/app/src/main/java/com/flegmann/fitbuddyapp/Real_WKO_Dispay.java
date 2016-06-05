package com.flegmann.fitbuddyapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class Real_WKO_Dispay extends Activity {
    String objname;
    protected List<ParseObject> exercise_name;
    String [] exercise = new String[10];
    String [] set = new String[10];
    String [] reps = new String[10];
    String [] weight = new String[10];
    int [] weights = new int[10];
    String exercise_pic = "";
    int numberofexercises = 0;
    int [] image_id = new int[10];
    int karma;
    String id;
    String exname;
    private List<Ejercisio> workout_exercises = new ArrayList<Ejercisio>();
    private TextView caloriastv;
    private TextView title_real;
    private View changeweight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real__wko__dispay);
        //getActionBar().hide();
        //setContentView(R.layout.activity_weight);
        Intent intent = getIntent();
        karma = intent.getIntExtra("karma", 0);
        objname = intent.getStringExtra("name");
        setTitle(objname);
        title_real = (TextView) findViewById(R.id.realTitle);
        title_real.setText(objname);
        //get workout details from Parse
        caloriastv = (TextView) findViewById(R.id.wko_calories);
        changeweight = findViewById(R.id.change_weight_real);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Workouts");
        query.fromLocalDatastore();
        query.whereEqualTo("name", objname);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject exercise_object, com.parse.ParseException e) {
                if (e == null) {
                    numberofexercises = exercise_object.getInt("number_exercises");
                    for (int i = 0; i < numberofexercises; i++) {
                        exname = exercise_object.getString("exercise" + i);
                        //fix this code later
                        //this is for the cardio stuff
                        if(exname.equals("Cycling") || exname.equals("Elliptical") || exname.equals("Jump Rope") ||
                                exname.equals("Running") || exname.equals("Rowing") || exname.equals("Swimming")){
                            set[i] = "intensity: " + (Integer.toString(exercise_object.getInt("set" + i))) + "%";
                            reps[i] = "length min: " + (Integer.toString(exercise_object.getInt("rep" + i)));
                            weight[i] = "no";
                        }
                        else if(exname.equals("Super set")){
                            set[i] = "Sets: " + (Integer.toString(exercise_object.getInt("set" + i)));
                            //reps[i] = "length min: " + (Integer.toString(exercise_object.getInt("rep" + i)));
                            reps[i] = "";
                            weight[i] = "no";
                        }
                        else {
                            int testint  = exercise_object.getInt("set" + i);
                            System.out.println("test " + testint);
                            if (testint == 93){
                                set[i] ="Super Set";
                                reps[i] = "Reps: " + (Integer.toString(exercise_object.getInt("rep" + i)));
                                weight[i] = "";
                            }
                            else {
                                set[i] = "Sets: " + (Integer.toString(exercise_object.getInt("set" + i)));
                                reps[i] = "Reps: " + (Integer.toString(exercise_object.getInt("rep" + i)));
                                weight[i] = "";
                            }
                            if (weight[i].equals("no")){
                                reps[i] = "";
                            }

                        }
                        exercise[i] = exercise_object.getString("exercise" + i);
                        exercise_pic = exercise[i];
                        exercise_pic = exercise_pic.toLowerCase().replaceAll(" ", "_");
                        System.out.println("Original: " + exercise_pic);
                        image_id[i] = getResources().getIdentifier(exercise_pic, "drawable", getPackageName());
                        if (image_id[i] == 0){
                            image_id[i] = getResources().getIdentifier("bg_count", "drawable", getPackageName());
                        }

                    }
                    int Calories = exercise_object.getInt("Calories");
                    caloriastv.setText("Calories: " + Calories);

                } else {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Workouts");
                    query.whereEqualTo("name", objname);
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject exercise_object, com.parse.ParseException e) {
                            if (e == null) {
                                numberofexercises = exercise_object.getInt("number_exercises");
                                for (int i = 0; i < numberofexercises; i++) {
                                    //temp = exercise_object.getInt("set"+i);
                                    set[i] = "Sets: " + (Integer.toString(exercise_object.getInt("set" + i)));
                                    reps[i] = "Reps: " + (Integer.toString(exercise_object.getInt("rep" + i)));
                                    exercise[i] = exercise_object.getString("exercise" + i);
                                    exercise_pic = exercise[i];
                                    exercise_pic.replaceAll(" ", "_");
                                    image_id[i] = getResources().getIdentifier(exercise_pic, "drawable", getPackageName());

                                }
                                int Calories = exercise_object.getInt("Calories");
                                caloriastv.setText("Calories: " + Calories);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Real_WKO_Dispay.this);
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
        ParseUser currentUser = ParseUser.getCurrentUser();
        final String userName = currentUser.getUsername();
        ParseQuery<ParseObject> weightq = ParseQuery.getQuery("my_wko");
        weightq.fromLocalDatastore();
        weightq.whereEqualTo("name", objname);
        weightq.whereEqualTo("user", userName);
        weightq.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject exercise_object, com.parse.ParseException e) {
                if (e == null) {
                    for (int i = 0; i < numberofexercises; i++) {
                        //temp = exercise_object.getInt("set"+i);
                        if(weight[i].equals("no") ) {
                            //weights[i] = exercise_object.getInt("weight" + i);
                            weight[i] = "" ;
                        }
                        else{
                            weights[i] = exercise_object.getInt("weight" + i);
                            weight[i] = "Weight: " + (Integer.toString(weights[i]));
                        }
                    }
                    id = exercise_object.getObjectId();
                    populatewkolist();
                    populatelistview();

                } else {
                    //create weight info
                    for (int i = 0; i < numberofexercises; i++) {
                        weight[i] = "Weight: 0";
                    }
                    populatewkolist();
                    populatelistview();
                }
            }
        });
        //registerClickCallback();
        changeweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goexcercises =  new Intent(Real_WKO_Dispay.this, Change_Weight.class);
                goexcercises.putExtra("id",id);
                goexcercises.putExtra("number", numberofexercises);
                goexcercises.putExtra("karma", karma);
                goexcercises.putExtra("weight", weights);
                goexcercises.putExtra("names", exercise);
                goexcercises.putExtra("name", objname);
                startActivity(goexcercises);
                finish();
            }
        });
    }

    private void populatelistview() {
        ArrayAdapter<Ejercisio> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.real_listview);
        list.setAdapter(adapter);
        //register click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ejercisio clickedwko = workout_exercises.get(position);

                String selected = clickedwko.getName();
                System.out.println("Clicked: " + selected);
                Intent goworkout = new Intent(Real_WKO_Dispay.this, Exercises_Details.class);
                goworkout.putExtra("name", selected);
                startActivity(goworkout);
            }
        });
    }

    private void populatewkolist() {
        //populate exercise list
        for (int i = 0; i < numberofexercises; i++){
            workout_exercises.add (new Ejercisio(image_id[i],exercise[i], reps[i], set[i], weight[i]));
        }
    }
    private class MyListAdapter extends ArrayAdapter<Ejercisio>{
        public MyListAdapter (){
          //  super(Real_WKO_Dispay.this, R.layout.da_real, workout_exercises);
            super(Real_WKO_Dispay.this, R.layout.weight_adapter_new, workout_exercises);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null){
                //itemView = getLayoutInflater().inflate(R.layout.da_real, parent, false);
                itemView = getLayoutInflater().inflate(R.layout.weight_adapter_new, parent, false);
            }
            // find the current element
            Ejercisio current_exercise_selected = workout_exercises.get(position);



            //fill the view

            ImageView imageView = (ImageView) itemView.findViewById(R.id.real_image);
            imageView.setImageResource(current_exercise_selected.getIconID());


           // System.out.println("Name " + current_exercise_selected.getName());
            TextView name_of_wko =  (TextView) itemView.findViewById(R.id.real_exercise_name);
            name_of_wko.setText(current_exercise_selected.getName());

            TextView weight_used =  (TextView) itemView.findViewById(R.id.real_weight);
            weight_used.setText(current_exercise_selected.getWeight());

            TextView sets = (TextView) itemView.findViewById(R.id.real_sets);
            sets.setText(current_exercise_selected.getSets());

            TextView reps = (TextView) itemView.findViewById(R.id.real_reps);
            reps.setText(current_exercise_selected.getReps());

            return itemView;
        }
    }
/*
    private void registerClickCallback() {
        ListView list = (ListView) findViewById((R.id.real_listview));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ejercisio clickedwko = workout_exercises.get(position);
                String selected = clickedwko.getName();
                Intent goworkout = new Intent(Real_WKO_Dispay.this, Exercises_Details.class);
                goworkout.putExtra("name", selected);
                startActivity(goworkout);
            }
        });
    }*/

}
