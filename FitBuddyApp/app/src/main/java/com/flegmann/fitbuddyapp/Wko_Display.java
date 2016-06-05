package com.flegmann.fitbuddyapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class Wko_Display extends Activity {
    String objname;
    protected List<ParseObject> exercise_name;
    String [] exercice = new String[14];
    String wko_pic = "";
    TextView title;
    int karma;
    int []image_id = new int[14];
    String [] days = {"day 1", "day 2", "day 3", "day 4", "day 5", "day 6", "day 7", "day 8", "day 9", "day 10", "day 11", "day 12",
            "day 13", "day 14"};
    private List<Wko_Adapter> program_workouts = new ArrayList<Wko_Adapter>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.activity_wko__display);
        Intent intent = getIntent();
        objname = intent.getStringExtra("name");
        karma = intent.getIntExtra("karma", 0);
        title = (TextView)findViewById(R.id.program_title);
        title.setText(objname);
        // get workouts of program
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Programs");
        query.fromLocalDatastore();
        query.whereEqualTo("name", objname);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject exercise_object, com.parse.ParseException e) {
                if (e == null) {
                    exercice[0] = exercise_object.getString("wk0");
                    exercice[1] = exercise_object.getString("wk1");
                    exercice[2] = exercise_object.getString("wk2");
                    exercice[3] = exercise_object.getString("wk3");
                    exercice[4] = exercise_object.getString("wk4");
                    exercice[5] = exercise_object.getString("wk5");
                    exercice[6] = exercise_object.getString("wk6");
                    exercice[7] = exercise_object.getString("wk7");
                    exercice[8] = exercise_object.getString("wk8");
                    exercice[9] = exercise_object.getString("wk9");
                    exercice[10] = exercise_object.getString("wk10");
                    exercice[11] = exercise_object.getString("wk11");
                    exercice[12] = exercise_object.getString("wk12");
                    exercice[13] = exercise_object.getString("wk13");

                    // get image names for workouts

                    for (int i = 0; i < 14; i++) {
                        wko_pic = exercice[i];
                        //wko_pic.replaceAll(" ", "_");
                        //image_id[i] = getResources().getIdentifier(wko_pic , "drawable", getPackageName());
                    }
                    populatewkolist();
                    populatelistview();

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Wko_Display.this);
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

       // registerClickCallback ();

    }
/*
    private void registerClickCallback() {
        ListView list = (ListView) findViewById((R.id.wko_listview));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Wko_Adapter clickedexce = program_workouts.get(position);
                String selected = clickedexce.getName();
                Intent goworkout =  new Intent(Wko_Display.this, Real_WKO_Dispay.class);
                goworkout.putExtra("name", selected);
                startActivity(goworkout);
            }
        });
    }*/

    private void populatelistview() {
        ArrayAdapter<Wko_Adapter> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.wko_listview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Wko_Adapter clickedexce = program_workouts.get(position);
                String selected = clickedexce.getName();
                Intent goworkout =  new Intent(Wko_Display.this, Real_WKO_Dispay.class);
                goworkout.putExtra("name", selected);
                startActivity(goworkout);
            }
        });
    }
    private class MyListAdapter extends ArrayAdapter<Wko_Adapter>{
        public MyListAdapter (){
            super(Wko_Display.this, R.layout.da_workouts, program_workouts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.da_workouts, parent, false);
            }
            // find the current element
            Wko_Adapter current_wko = program_workouts.get(position);



            //fill the view
            /*
            ImageView imageView = (ImageView) itemView.findViewById(R.id.wkoimage);
            imageView.setImageResource(current_wko.getIconID());
            */
            TextView name_of_wko =  (TextView) itemView.findViewById(R.id.name_da_workout);
            name_of_wko.setText(current_wko.getName());
            ;

            TextView day_view = (TextView) itemView.findViewById(R.id.day_da_workout);
            day_view.setText(current_wko.getDay());
            return itemView;
        }
    }
    private void populatewkolist() {
        for (int i = 0; i < 14; i++) {
            program_workouts.add(new Wko_Adapter(exercice[i],image_id[i] ,days[i]));
        }
    }






}
