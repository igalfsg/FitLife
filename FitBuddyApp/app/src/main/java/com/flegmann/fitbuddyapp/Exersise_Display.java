package com.flegmann.fitbuddyapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class Exersise_Display extends ListActivity
{

    String objname;
    protected List<ParseObject> exercise_name;
    EditText inputSearch;
    int thing = 0;
    int login = 0;
    int karma = 0;
    int index = 0;
    String [] exercises = new String[10];
    protected TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        thing = intent.getIntExtra("thing", login);
        karma = intent.getIntExtra("karma", login);
        objname = intent.getStringExtra("name");
        if (thing < 6 ){
            //setContentView(R.layout.activity_exersise_display);
            setContentView(R.layout.excersize_display);
            title = (TextView)findViewById(R.id.realTitle);
        }
        else{
            setContentView(R.layout.exercise_disp_with_btn);
            title = (TextView) findViewById(R.id.title_ex_disp_btn);
        }
        //setTitle(objname);
        getActionBar().hide();
        title.setText(objname);
        inputSearch = (EditText) this.findViewById(R.id.inputSearch);
        //Toast.makeText(Exersise_Display.this, "thing " + thing + objname, Toast.LENGTH_LONG).show();
        if(thing == 1) {//exercises
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Exercises");
            query.fromLocalDatastore();
            if(!(objname.equals("All Exercises"))){
                query.whereEqualTo("Type", objname);
            }
            else{
                query.whereExists("img_name");
                query.whereNotEqualTo("img_name","");
                query.whereNotEqualTo("Type", "Prog");
            }
            query.setLimit(1000);
            query.orderByAscending("name");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        //success
                        exercise_name = list;
                        //Toast.makeText(Exersise_Display.this, "exercises", Toast.LENGTH_LONG).show();
                        //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                        ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                        setListAdapter(adapter);
                    } else {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Exercises");
                        if(!(objname.equals("All Exercises"))){
                            query.whereEqualTo("Type", objname);}
                        query.setLimit(1000);
                        query.orderByAscending("name");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                if (e == null) {
                                    //success
                                    exercise_name = list;
                                    //Toast.makeText(Exersise_Display.this, "exercises", Toast.LENGTH_LONG).show();
                                    //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                                    ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                                    setListAdapter(adapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Exersise_Display.this);
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
            //edit yakarma
            ParseUser currentUser = ParseUser.getCurrentUser();
            final String userName = currentUser.getUsername();
            ParseQuery<ParseObject> querykarma = ParseQuery.getQuery("yakkarma");
            querykarma.fromLocalDatastore();
            querykarma.whereEqualTo("user", userName);
            querykarma.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(final ParseObject parseObject, ParseException e) {
                    if (e == null) {
                        karma = parseObject.getInt("karma");
                        //Toast.makeText(MainActivity.this, "thing " + karma, Toast.LENGTH_LONG).show();
                        karma += 2;
                        parseObject.increment("karma", 2);
                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                            parseObject.pinInBackground();
                            }
                        });
                    }
                }
            });
        }

        else if (thing == 2){//preprograms
            if (objname.equals("At Home")){
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Programs");
                query.fromLocalDatastore();
                query.whereEqualTo("extra", objname);
                query.orderByAscending("name");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null) {
                            //success
                            exercise_name = list;
                            //Toast.makeText(Exersise_Display.this, "Success", Toast.LENGTH_LONG).show();
                            ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                            setListAdapter(adapter);
                        } else {
                            ParseQuery<ParseObject> query = ParseQuery.getQuery("Programs");
                            query.whereEqualTo("extra", objname);
                            query.orderByAscending("name");
                            query.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> list, ParseException e) {
                                    if (e == null) {
                                        //success
                                        exercise_name = list;
                                        //Toast.makeText(Exersise_Display.this, "Success", Toast.LENGTH_LONG).show();
                                        ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                                        setListAdapter(adapter);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Exersise_Display.this);
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
                ParseQuery<ParseObject> querykarma = ParseQuery.getQuery("yakkarma");
                querykarma.fromLocalDatastore();
                querykarma.whereEqualTo("user", userName);
                querykarma.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(final ParseObject parseObject, ParseException e) {
                        if (e == null) {
                            karma = parseObject.getInt("karma");
                            //Toast.makeText(MainActivity.this, "thing " + karma, Toast.LENGTH_LONG).show();
                            karma += 2;
                            parseObject.increment("karma", 2);
                            parseObject.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    parseObject.pinInBackground();
                                }
                            });
                        }
                    }
                });
            }
            else {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Programs");
                query.fromLocalDatastore();
                query.whereEqualTo("type", objname);
                query.orderByAscending("name");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null) {
                            //success
                            exercise_name = list;
                            //Toast.makeText(Exersise_Display.this, "Success", Toast.LENGTH_LONG).show();
                            ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                            setListAdapter(adapter);
                        } else {
                            ParseQuery<ParseObject> query = ParseQuery.getQuery("Programs");
                            query.whereEqualTo("type", objname);
                            query.orderByAscending("name");
                            query.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> list, ParseException e) {
                                    if (e == null) {
                                        //success
                                        exercise_name = list;
                                        //Toast.makeText(Exersise_Display.this, "Success", Toast.LENGTH_LONG).show();
                                        ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                                        setListAdapter(adapter);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Exersise_Display.this);
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
            }
            ParseUser currentUser = ParseUser.getCurrentUser();
            final String userName = currentUser.getUsername();
            ParseQuery<ParseObject> querykarma = ParseQuery.getQuery("yakkarma");
            querykarma.fromLocalDatastore();
            querykarma.whereEqualTo("user", userName);
            querykarma.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(final ParseObject parseObject, ParseException e) {
                    if (e == null) {
                        karma = parseObject.getInt("karma");
                        //Toast.makeText(MainActivity.this, "thing " + karma, Toast.LENGTH_LONG).show();
                        karma += 2;
                        parseObject.increment("karma", 2);
                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                parseObject.pinInBackground();
                            }
                        });
                    }
                }
            });
        }
        else if (thing == 3){//pre_made_workout
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Workouts");
                query.fromLocalDatastore();
                query.whereEqualTo("type", objname);
                query.orderByAscending("name");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null) {
                            //success
                            exercise_name = list;
                            //Toast.makeText(Exersise_Display.this, "workout", Toast.LENGTH_LONG).show();
                            ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                            setListAdapter(adapter);
                        } else {
                            ParseQuery<ParseObject> query = ParseQuery.getQuery("Workouts");
                            query.whereEqualTo("type", objname);
                            query.orderByAscending("name");
                            query.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> list, ParseException e) {
                                    if (e == null) {
                                        //success
                                        exercise_name = list;
                                        //Toast.makeText(Exersise_Display.this, "workout", Toast.LENGTH_LONG).show();
                                        ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                                        setListAdapter(adapter);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Exersise_Display.this);
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
           // }
            ParseUser currentUser = ParseUser.getCurrentUser();
            final String userName = currentUser.getUsername();
            ParseQuery<ParseObject> querykarma = ParseQuery.getQuery("yakkarma");
            querykarma.fromLocalDatastore();
            querykarma.whereEqualTo("user", userName);
            querykarma.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(final ParseObject parseObject, ParseException e) {
                    if (e == null) {
                        karma = parseObject.getInt("karma");
                        //Toast.makeText(MainActivity.this, "thing " + karma, Toast.LENGTH_LONG).show();
                        karma += 2;
                        parseObject.increment("karma", 2);
                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                parseObject.pinInBackground();
                            }
                        });
                    }
                }
            });
        }
        else if (thing == 4){// my wko add
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Workouts");
            query.fromLocalDatastore();
            query.whereNotEqualTo("type", "Community");
            query.whereNotEqualTo("Premium", true);
            query.orderByAscending("name");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        //success
                        exercise_name = list;
                        ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                        setListAdapter(adapter);
                    } else {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Workouts");
                        query.whereNotEqualTo("type", "Community");
                        query.orderByAscending("name");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                if (e == null) {
                                    //success
                                    exercise_name = list;
                                    ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                                    setListAdapter(adapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Exersise_Display.this);
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
            ParseQuery<ParseObject> querykarma = ParseQuery.getQuery("yakkarma");
            querykarma.fromLocalDatastore();
            querykarma.whereEqualTo("user", userName);
            querykarma.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(final ParseObject parseObject, ParseException e) {
                    if (e == null) {
                        karma = parseObject.getInt("karma");
                        //Toast.makeText(MainActivity.this, "thing " + karma, Toast.LENGTH_LONG).show();
                        karma += 2;
                        parseObject.increment("karma", 2);
                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                parseObject.pinInBackground();
                            }
                        });
                    }
                }
            });
        }
        else if (thing == 5){// my programs add
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Programs");
            query.fromLocalDatastore();
            query.orderByAscending("name");
            query.whereNotEqualTo("Premium", true);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        //success
                        exercise_name = list;
                        ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                        setListAdapter(adapter);
                    } else {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Programs");
                        query.orderByAscending("name");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                if (e == null) {
                                    //success
                                    exercise_name = list;
                                    ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                                    setListAdapter(adapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Exersise_Display.this);
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
            ParseQuery<ParseObject> querykarma = ParseQuery.getQuery("yakkarma");
            querykarma.fromLocalDatastore();
            querykarma.whereEqualTo("user", userName);
            querykarma.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(final ParseObject parseObject, ParseException e) {
                    if (e == null) {
                        karma = parseObject.getInt("karma");
                        //Toast.makeText(MainActivity.this, "thing " + karma, Toast.LENGTH_LONG).show();
                        karma += 2;
                        parseObject.increment("karma", 2);
                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                parseObject.pinInBackground();
                            }
                        });
                    }
                }
            });
        }
        else if (thing == 6){//create Wko
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Exercises");
            query.fromLocalDatastore();
            query.setLimit(1000);
            query.whereExists("img_name");
            query.orderByAscending("name");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        //success
                        exercise_name = list;
                        ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                        setListAdapter(adapter);
                    } else {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Exercises");
                        query.setLimit(1000);
                        query.orderByAscending("name");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                if (e == null) {
                                    //success
                                    exercise_name = list;
                                    ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                                    setListAdapter(adapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Exersise_Display.this);
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
            ParseQuery<ParseObject> querykarma = ParseQuery.getQuery("yakkarma");
            querykarma.fromLocalDatastore();
            querykarma.whereEqualTo("user", userName);
            querykarma.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(final ParseObject parseObject, ParseException e) {
                    if (e == null) {
                        karma = parseObject.getInt("karma");
                        //Toast.makeText(MainActivity.this, "thing " + karma, Toast.LENGTH_LONG).show();
                        karma += 2;
                        parseObject.increment("karma", 2);
                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                parseObject.pinInBackground();
                            }
                        });
                    }
                }
            });
        }

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                applySearchKeywords(""+charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //written by diganta das
    List<ParseObject> tempExerciseList;
    int search_applied=0;  //0=not applied, 1 = applied
    private void applySearchKeywords(String searchKeyword)
    {
        if(!TextUtils.isEmpty(searchKeyword)) {
            int completeListSize = exercise_name.size();
            tempExerciseList = new ArrayList<ParseObject>();
            //commented out by diganta das
            //List<ParseObject> tempExerciseList = new ArrayList<ParseObject>();
            for (int i = 0; i < completeListSize; i++) {
                ParseObject singleExercise = exercise_name.get(i);
                if (singleExercise.getString("name").toLowerCase().contains(searchKeyword.toLowerCase())) {
                    tempExerciseList.add(singleExercise);
                }
            }
            //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), tempExerciseList);
            ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), tempExerciseList);
            setListAdapter(adapter);
            //written by diganta das
            search_applied=1;
        }
        else
        {
            //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
            ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
            setListAdapter(adapter);
            //written by diganta das
            search_applied=0;
        }
    }

    public void imageClick(View view) {
        switch (view.getId()) {
            case R.id.btn_done_dispwithbtn:
                if (index != 0) {
                    Intent goexcercises = new Intent(Exersise_Display.this, Create_WKO.class);
                    goexcercises.putExtra("index", index);
                    goexcercises.putExtra("karma", karma);
                    goexcercises.putExtra("exercises", exercises);
                    startActivity(goexcercises);
                }
                else{
                    Toast.makeText(Exersise_Display.this, "workout is empty", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.dlete_exercise:
                if(index != 0){
                    index--;
                    title.setText("Selected " + index +" out of 10");
                }
                else{
                    Toast.makeText(Exersise_Display.this, "workout is empty", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }

    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ParseObject newobject=null;
        //written by diganta das
        if(search_applied==1){
            newobject=tempExerciseList.get(position);
        }
        else{
            newobject = exercise_name.get(position);
        }
        //commented out by diganta das
        //ParseObject newobject = exercise_name.get(position);
        final String name = newobject.getString("name");

        if (thing == 1){//go to exercise page
            Intent goexcercises =  new Intent(Exersise_Display.this, Exercises_Details.class);
            goexcercises.putExtra("name", name);
            startActivity(goexcercises);
        }
        else if (thing == 2){//go to list of workouts of the program
            boolean premium= newobject.getBoolean("Premium");
            if (premium){
                ParseUser currentUser = ParseUser.getCurrentUser();
                final String userName = currentUser.getUsername();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("my_prog");
                query.fromLocalDatastore();
                query.whereEqualTo("name", name);
                query.whereEqualTo("user",userName);
                query.orderByAscending("name");
                query.getFirstInBackground(new GetCallback<ParseObject>()  {
                    @Override
                    public void done(ParseObject exercise_object, com.parse.ParseException e) {
                        if (e == null) {
                            //already exists
                            Intent gowkos = new Intent(Exersise_Display.this, Wko_Display.class);
                            gowkos.putExtra("name", name);
                            startActivity(gowkos);
                        } else {
                            ParseQuery<ParseObject> query = ParseQuery.getQuery("my_prog");
                            query.whereEqualTo("name", name);
                            query.whereEqualTo("user", userName);
                            query.getFirstInBackground(new GetCallback<ParseObject>()  {
                                @Override
                                public void done(ParseObject exercise_object, com.parse.ParseException e) {
                                    if (e == null) {
                                        //already exists
                                        Intent gowkos = new Intent(Exersise_Display.this, Wko_Display.class);
                                        gowkos.putExtra("name", name);
                                        gowkos.putExtra("karma", karma);
                                        startActivity(gowkos);
                                    } else {
                                        Intent gowkos = new Intent(Exersise_Display.this, buyplan.class);
                                        gowkos.putExtra("name", name);
                                        gowkos.putExtra("karma", karma);
                                        startActivity(gowkos);
                                    }
                                }
                            });
                        }
                    }
                });
            }
            else {
                Intent gowkos = new Intent(Exersise_Display.this, Wko_Display.class);
                gowkos.putExtra("name", name);
                startActivity(gowkos);
            }
        }
        else if (thing == 3){// go to wokrout page
            boolean premium= newobject.getBoolean("Premium");
            if (premium) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                final String userName = currentUser.getUsername();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("my_wko");
                query.fromLocalDatastore();
                query.whereEqualTo("name", name);
                query.whereEqualTo("user", userName);
                query.orderByAscending("name");
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject exercise_object, com.parse.ParseException e) {
                        if (e == null) {
                            //already exists
                            Intent gowkos = new Intent(Exersise_Display.this, Real_WKO_Dispay.class);
                            gowkos.putExtra("name", name);
                            startActivity(gowkos);
                        } else {
                            ParseQuery<ParseObject> query = ParseQuery.getQuery("my_wko");
                            query.whereEqualTo("name", name);
                            query.whereEqualTo("user", userName);
                            query.getFirstInBackground(new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject exercise_object, com.parse.ParseException e) {
                                    if (e == null) {
                                        //already exists
                                        Intent gowkos = new Intent(Exersise_Display.this, Real_WKO_Dispay.class);
                                        gowkos.putExtra("name", name);
                                        gowkos.putExtra("karma", karma);
                                        startActivity(gowkos);
                                    } else {
                                        Intent gowkos = new Intent(Exersise_Display.this, buyplan.class);
                                        gowkos.putExtra("name", name);
                                        gowkos.putExtra("karma", karma);
                                        startActivity(gowkos);
                                    }
                                }
                            });
                        }
                    }
                });
            }
            else {
                Intent gowkos = new Intent(Exersise_Display.this, Real_WKO_Dispay.class);
                gowkos.putExtra("name", name);
                gowkos.putExtra("karma", karma);
                startActivity(gowkos);
            }
        }
        else if (thing == 4){// add workout to thing
            ParseUser currentUser = ParseUser.getCurrentUser();
            final String userName = currentUser.getUsername();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("my_wko");
            query.fromLocalDatastore();
            query.whereEqualTo("name", name);
            query.whereEqualTo("user",userName);
            query.orderByAscending("name");
            query.getFirstInBackground(new GetCallback<ParseObject>()  {
                @Override
                public void done(ParseObject exercise_object, com.parse.ParseException e) {
                    if (e == null) {
                        //already exists
                        AlertDialog.Builder builder = new AlertDialog.Builder(Exersise_Display.this);
                        builder.setMessage("This workout has already been added to your workout list");
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
                    } else {
                        ParseObject new_wko = new ParseObject("my_wko");
                        new_wko.put ("user", userName);
                        new_wko.put("name", name);
                        for (int i = 0; i < 10; i++ ) {
                            new_wko.put("weight" + i, 0);
                        }
                        new_wko.pinInBackground();
                        new_wko.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null){
                                    Toast.makeText(Exersise_Display.this, "Workout Saved", Toast.LENGTH_LONG).show();;
                                }
                                else {
                                    Toast.makeText(Exersise_Display.this, "Saved on Phone not on network", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            });
        }
        else if (thing == 5){// add program to thing
            ParseUser currentUser = ParseUser.getCurrentUser();
            final String userName = currentUser.getUsername();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("my_prog");
            query.fromLocalDatastore();
            query.whereEqualTo("name", name);
            query.whereEqualTo("user",userName);
            query.orderByAscending("name");
            query.getFirstInBackground(new GetCallback<ParseObject>()  {
                @Override
                public void done(ParseObject exercise_object, com.parse.ParseException e) {
                    if (e == null) {
                        //already exists
                        AlertDialog.Builder builder = new AlertDialog.Builder(Exersise_Display.this);
                        builder.setMessage("This Program has already been added to your workout list");
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
                    } else {
                        ParseObject new_wko = new ParseObject("my_prog");
                        new_wko.put ("user", userName);
                        new_wko.put("name", name);
                        new_wko.pinInBackground();
                        new_wko.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(Exersise_Display.this, "Program Saved", Toast.LENGTH_LONG).show();
                                    //add workouts to my workouts
                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Programs");
                                    query.fromLocalDatastore();
                                    query.whereEqualTo("name", objname);
                                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                                        @Override
                                        public void done(ParseObject wkoobject, ParseException e) {
                                            if(e==null){
                                                String [] wkos = new String[14];
                                                for (int i = 0 ; i < 15; i++){
                                                    wkos[i] = wkoobject.getString("wk" + i);
                                                }
                                                for (int i = 0 ; i < 15; i++) {
                                                    ParseObject nw_wko = new ParseObject("my_wko");
                                                    nw_wko.put("user", userName);
                                                    nw_wko.put("name", wkos[i]);
                                                    for (int k = 0; k < 10; k++) {
                                                        nw_wko.put("weight" + k, 0);
                                                    }
                                                    nw_wko.pinInBackground();
                                                    nw_wko.saveInBackground(new SaveCallback() {
                                                        @Override
                                                        public void done(ParseException e) {
                                                            if (e == null) {
                                                                //Toast.makeText(Exersise_Display.this, "Workout Saved", Toast.LENGTH_LONG).show();;
                                                            } else {
                                                                // Toast.makeText(Exersise_Display.this, "Saved on Phone not on network", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(Exersise_Display.this, "Saved on Phone not on network", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            });
        }
        else if (thing == 6){// add workout to thing
            ParseUser currentUser = ParseUser.getCurrentUser();
            final String userName = currentUser.getUsername();
            if(index != 10) {
                exercises[index] = name;
                System.out.println("selected: " + exercises[index]);
                index++;
                //setTitle("Selected " + index +" out of 10");
                title.setText("Selected " + index +" out of 10");
            }
            else{
                Toast.makeText(Exersise_Display.this, "Workout is full", Toast.LENGTH_LONG).show();
            }
        }
    }

}
