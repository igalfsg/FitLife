package com.flegmann.fitbuddyapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class Types_Display extends ListActivity {
    public int thing = 0;
    int login = 0;
    int karma;
    protected TextView title;
    protected List<ParseObject> exercise_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        karma = intent.getIntExtra("karma", 0);
        thing = intent.getIntExtra("thing" , login);
        if (thing < 4){
            setContentView(R.layout.wko_class);
            title = (TextView)findViewById(R.id.header_text_view);
            getActionBar().hide();
        }
        else if (thing == 5) {//my_programs
            setContentView(R.layout.myprogslay);
            title = (TextView)findViewById(R.id.myprogstitle);
            getActionBar().hide();
        }
        else if (thing == 4){//my_wko
            setContentView(R.layout.mywkolay);
            title = (TextView)findViewById(R.id.myprogstitle);
            getActionBar().hide();
        }
        //header_layout.setVisibility(View.GONE);

        if (thing == 1) {//exercises
            setTitle("Select Exercise Type");
            title.setText("Select Exercise Type");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Wk_Types");
            query.fromLocalDatastore();
            query.whereEqualTo("type", 1);
            query.orderByAscending("name");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        //success
                        exercise_name = list;
                        //Toast.makeText(Types_Display.this, "Success", Toast.LENGTH_LONG).show();
                        //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                        MyWKOAdapter adapter = new MyWKOAdapter(getListView().getContext(), exercise_name);
                        setListAdapter(adapter);
                    } else {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Wk_Types");
                        query.whereEqualTo("type", 1);
                        query.orderByAscending("name");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                if (e == null) {
                                    //success
                                    exercise_name = list;
                                    //Toast.makeText(Types_Display.this, "Success", Toast.LENGTH_LONG).show();
                                    //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                                    MyWKOAdapter adapter = new MyWKOAdapter(getListView().getContext(), exercise_name);
                                    setListAdapter(adapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Types_Display.this);
                                    builder.setMessage(e.getMessage());
                                    builder.setTitle("Sorry!");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
        else if (thing == 2) {//pre_programs
            setTitle("Select Your Goal");
            title.setText("Select Your Goal");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Wk_Types");
            query.fromLocalDatastore();
            query.whereEqualTo("type", 2);
            query.orderByAscending("name");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        //success
                        exercise_name = list;
                        //Toast.makeText(Types_Display.this, "Success", Toast.LENGTH_LONG).show();
                        //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                        MyWKOAdapter adapter = new MyWKOAdapter(getListView().getContext(), exercise_name);
                        setListAdapter(adapter);
                    } else {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Wk_Types");
                        query.whereEqualTo("type", 2);
                        query.orderByAscending("name");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                if (e == null) {
                                    //success
                                    exercise_name = list;
                                    //Toast.makeText(Types_Display.this, "Success", Toast.LENGTH_LONG).show();
                                    //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                                    MyWKOAdapter adapter = new MyWKOAdapter(getListView().getContext(), exercise_name);
                                    setListAdapter(adapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Types_Display.this);
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
        else if (thing == 3) {//pre_workouts
            setTitle("Select Workout Type");
            title.setText("Select Workout Type");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Wk_Types");
            query.fromLocalDatastore();
            query.whereEqualTo("type", 3);
            query.orderByAscending("name");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        //success
                        exercise_name = list;
                        //Toast.makeText(Types_Display.this, "Success", Toast.LENGTH_LONG).show();
                        //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                        MyWKOAdapter adapter = new MyWKOAdapter(getListView().getContext(), exercise_name);
                        setListAdapter(adapter);
                    } else {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Wk_Types");
                        query.whereEqualTo("type", 3);
                        query.orderByAscending("name");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                if (e == null) {
                                    //success
                                    exercise_name = list;
                                    //Toast.makeText(Types_Display.this, "Success", Toast.LENGTH_LONG).show();
                                    //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                                    MyWKOAdapter adapter = new MyWKOAdapter(getListView().getContext(), exercise_name);
                                    setListAdapter(adapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Types_Display.this);
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
        else if (thing == 4) {//my_wko
            setTitle("My Workouts");
            title.setText("My Workouts");
            ParseUser currentUser = ParseUser.getCurrentUser();
            final String userName = currentUser.getUsername();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("my_wko");
            query.fromLocalDatastore();
            query.whereEqualTo("user", userName);
            query.orderByAscending("name");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        //success
                        exercise_name = list;
                        //Toast.makeText(Types_Display.this, "Success", Toast.LENGTH_LONG).show();
                        Log.i("su", "exercise name result: " + exercise_name);
                        //header_layout.setVisibility(View.VISIBLE);
                        //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                        ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                        setListAdapter(adapter);
                    } else {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("my_wko");
                        query.whereEqualTo("user", userName);
                        query.orderByAscending("name");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                if (e == null) {
                                    //success
                                    exercise_name = list;
                                    //Toast.makeText(Types_Display.this, "Success", Toast.LENGTH_LONG).show();
                                    //header_layout.setVisibility(View.VISIBLE);
                                    //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                                    ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                                    setListAdapter(adapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Types_Display.this);
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
        else if (thing == 5) {//my_prog
            setTitle("My Programs");
            title.setText("My Programs");
            ParseUser currentUser = ParseUser.getCurrentUser();
            final String userName = currentUser.getUsername();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("my_prog");
            query.fromLocalDatastore();
            query.whereEqualTo("user", userName);
            query.orderByAscending("name");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        //success
                        exercise_name = list;
                        //Toast.makeText(Types_Display.this, "Success", Toast.LENGTH_LONG).show();
                        //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                        ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                        setListAdapter(adapter);
                    } else {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("my_prog");
                        query.whereEqualTo("user", userName);
                        query.orderByAscending("name");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                if (e == null) {
                                    //success
                                    exercise_name = list;
                                    //Toast.makeText(Types_Display.this, "Success", Toast.LENGTH_LONG).show();
                                    //Excercise_Adapter adapter = new Excercise_Adapter(getListView().getContext(), exercise_name);
                                    ExcersesAdapter2 adapter = new ExcersesAdapter2(getListView().getContext(), exercise_name);
                                    setListAdapter(adapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Types_Display.this);
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

    }
    public void imageClick(View view) {
        switch (view.getId()) {
            case R.id.addmoreprogs://get programs
                Intent goexcercises =  new Intent(Types_Display.this, Exersise_Display.class);
                goexcercises.putExtra("thing",thing);
                goexcercises.putExtra("karma",karma);
                goexcercises.putExtra("name", "Select Program");
                startActivity(goexcercises);
                finish();
                break;

            case R.id.btn_add_newwko://add existing wko
                Intent goexcercisesw =  new Intent(Types_Display.this, Exersise_Display.class);
                goexcercisesw.putExtra("thing",thing);
                goexcercisesw.putExtra("karma",karma);
                goexcercisesw.putExtra("name", "Select Workout");
                startActivity(goexcercisesw);
                finish();
                break;

            case R.id.create_wko_button://create a new wko
                Intent goexcercisesn =  new Intent(Types_Display.this, Exersise_Display.class);
                goexcercisesn.putExtra("thing",6);
                goexcercisesn.putExtra("karma",karma);
                goexcercisesn.putExtra("name", "Select up to 10 Exercises");
                startActivity(goexcercisesn);
                finish();
            default:
                break;
        }

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ParseObject newobject = exercise_name.get(position);
        String name = newobject.getString("name");
        if (thing < 4){//go to the next list
            Intent goexcercises =  new Intent(Types_Display.this, Exersise_Display.class);
            goexcercises.putExtra("thing", thing);
            goexcercises.putExtra("name", name);
            startActivity(goexcercises);
        }
        else if (thing == 4){//go to workout page
            Intent goworkout =  new Intent(Types_Display.this, Real_WKO_Dispay.class);
            goworkout.putExtra("name", name);
            startActivity(goworkout);
        }
        else if (thing == 5){//my programs go to lisf of wko of program
            Intent goworkout =  new Intent(Types_Display.this, Wko_Display.class);
            goworkout.putExtra("name", name);
            startActivity(goworkout);
        }

    }


/*
    //click on menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_to_my_stuff_btn) {// get programs
            Intent goexcercises =  new Intent(Types_Display.this, Exersise_Display.class);
            goexcercises.putExtra("thing",thing);
            goexcercises.putExtra("karma",karma);
            goexcercises.putExtra("name", "Select Program");
            startActivity(goexcercises);
            //return true;
        }
        else if (id == R.id.add_more_btn) {//get workout
            Intent goexcercises =  new Intent(Types_Display.this, Exersise_Display.class);
            goexcercises.putExtra("thing",thing);
            goexcercises.putExtra("karma",karma);
            goexcercises.putExtra("name", "Select Workout");
            startActivity(goexcercises);
            //return true;
        }
        else if (id == R.id.create_wko_btn) {
            Intent goexcercises =  new Intent(Types_Display.this, Exersise_Display.class);
            goexcercises.putExtra("thing",6);
            goexcercises.putExtra("karma",karma);
            goexcercises.putExtra("name", "Select up to 10 Exercises");
            startActivity(goexcercises);
            //return true;
        }


        return super.onOptionsItemSelected(item);
    }
*/
}
