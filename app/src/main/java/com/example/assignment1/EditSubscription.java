/*
 *
 * EditSubscription
 *
 * February 4, 2018
 *
 * Copyright (c) 2018 Lauren H.-Leblanc, CMPUT 301,
 * University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions
  * of the Code of Student Behaviour at the University of Alberta.
 */

package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

/**
 * A sub-activity which allows the user to edit a subscription.
 *
 * @author Lauren H.-L.
 * @see Subscription
 * @see SubList
 * @see ViewSubscription
 */
public class EditSubscription extends AppCompatActivity {

    private String name;
    private String date;
    private Double charge;
    private String comment;
    private Subscription sub;
    private Context context= this;

    /**
     * On creation of the activity, it displays the layout. Also dictates what
     * actions will be performed once the "Edit" button is clicked.
     *
     * @param savedInstanceState The activity's previous saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subscription);

        View.OnClickListener editSubListener = new View.OnClickListener() {
            public void onClick(View view){

                getInput();
                try {
                    checkInput();
                    /* no errors, continue */
                    sub = new Subscription(charge, date, name, comment);
                    finish();
                }
                catch(NegativeChargeException ex){
                    Toast.makeText(getApplicationContext(), "Monthly Charge can't be negative", Toast.LENGTH_LONG).show();
                }
                catch(EmptyFieldException ex){
                    Toast.makeText(getApplicationContext(), "Name, Date, and Monthly charge cannot be left blank.", Toast.LENGTH_LONG).show();
                }
            }
        };
        Button editSubButton = (Button) findViewById(R.id.button);
        editSubButton.setOnClickListener(editSubListener);
    }


    /**
     * Gets the user entered text from the view objects and places it in variables.
     */
    public void getInput() {
        EditText editText = (EditText) findViewById(R.id.NameField);
        name = editText.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.DateField);
        date = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.MonthlyCharge);
        String temp = editText3.getText().toString();
        if (!(temp.isEmpty())) {
            charge = Double.parseDouble(temp);
        }
        EditText editText5 = (EditText) findViewById(R.id.CommentField);
        comment = editText5.getText().toString();
    }

    /**
     * Throws exception if user entered text is invalid.
     *
     * @throws NegativeChargeException Thrown if monthly charge is negative.
     * @throws EmptyFieldException Thrown if an essential field is empty.
     */
    public void checkInput() throws NegativeChargeException, EmptyFieldException {
        if ((charge != null) && (charge < 0)) {
            throw new NegativeChargeException();
        }
        if ((name == null) || (charge == null) || (date == null)){
            throw new EmptyFieldException();
        }
        if (comment == null){
            comment = " ";
        }
    }

    /**
     * When the activity finishes, it creates a new Subscription object
     * and places it in an intent to be returned to the ViewSubscription activity.
     */
    @Override
    public void finish() {
        Intent returnData = new Intent();
        returnData.putExtra("editSub", sub);
        setResult(RESULT_OK, returnData);
        super.finish();
    }
}
