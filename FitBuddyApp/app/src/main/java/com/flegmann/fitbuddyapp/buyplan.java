package com.flegmann.fitbuddyapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class buyplan extends Activity {

    String objname;
    String img_name;
    int type;
    double price;
    int karma;
    protected TextView description;
    protected TextView title;
    protected ImageView image;
    public void imageClick(View view) {
        switch (view.getId()) {
            case R.id.video_button_compra:
                //pay stuff to paypal
                PayPalPayment payment = new PayPalPayment(new BigDecimal(price), "USD", objname,
                        PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(this, PaymentActivity.class);
                // send the same configuration for restart resiliency
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
                startActivityForResult(intent, 0);
                System.out.println("it works");
                break;

            default:
                break;
        }

    }
    private static PayPalConfiguration config = new PayPalConfiguration()

            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)

            .clientId("ATPQYOFgD2_Kms2GUUQuvOGGoJ6RfEzw2a2y-_q_CoRrgwGf5MRFeqjRrAajL6Q1XtmhbrhvXHF0cJmn");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyplan);
        Intent intent = getIntent();
        objname = intent.getStringExtra("name");
        karma = intent.getIntExtra("karma", 0);

        //initialize paypal service
        Intent intento = new Intent(this, PayPalService.class);
        intento.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intento);


        //declarations
        description = (TextView) findViewById(R.id.details_description_compra);
        title = (TextView) findViewById(R.id.details_title_compra);
        image = (ImageView) findViewById(R.id.details_image_compra);

        //get the info

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Premiumplans");
        query.whereEqualTo("name", objname);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    price = parseObject.getDouble("Price");
                    description.setText(parseObject.getString("description"));
                    type = parseObject.getInt("Type");
                    img_name = objname;
                    img_name.replaceAll(" ", "_");
                    img_name.toLowerCase();
                    int id = getResources().getIdentifier(img_name, "drawable", getPackageName());
                    image.setImageResource(id);
                    title.setText(objname + " $" + price + " USD");
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(buyplan.this);
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

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));
                    Toast.makeText(buyplan.this,"Success", Toast.LENGTH_LONG).show();
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    final String userName = currentUser.getUsername();
                    final String userEmail = currentUser.getEmail();
                    if(type == 2){//workouts

                        ParseObject new_wko = new ParseObject("my_wko");
                        new_wko.put ("user", userName);
                        new_wko.put("name", objname);
                        for (int i = 0; i < 10; i++ ) {
                            new_wko.put("weight" + i, 0);
                        }
                        new_wko.pinInBackground();
                        new_wko.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null){
                                    Toast.makeText(buyplan.this,"Success", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(buyplan.this, "Saved on Phone not on server", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    else if (type ==3) {//personal training
                        //put in parse that it went through
                        //send email to iggy
                        //notify user it takes a week
                        /*
                        Dear (userName < already declared) to get you your personalized plan at the highest
                         quality standard please allow our experts a full week to get your plan
                         you will receive an email letting you know your plan is ready to this
                         email: (userEmail < already declared) if this email is incorrect or you don't receive
                         the program within a week please email us to training@fitlifeapp.com
                         */
                        //^^ send this as email to user too

                    }
                    else{//plans
                        ParseObject new_wko = new ParseObject("my_prog");
                        new_wko.put ("user", userName);
                        new_wko.put("name", objname);
                        new_wko.pinInBackground();
                        new_wko.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(buyplan.this, "Program Saved", Toast.LENGTH_LONG).show();
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
                                    Toast.makeText(buyplan.this, "Saved on Phone not on network", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }


                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
            Toast.makeText(buyplan.this,"Order Canceled", Toast.LENGTH_LONG).show();

        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            Toast.makeText(buyplan.this,"Invalid Payment", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}
