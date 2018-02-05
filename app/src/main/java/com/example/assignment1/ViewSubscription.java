/*
 *
 * ViewSubscription
 *
 * February 03, 2018
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
import android.widget.TextView;

/**
 * A sub-activity which allows the user to view the details of a subscription,
 * proceed to the editing activity, or delete the subscription.
 *
 * @author Lauren H.-L.
 * @see SubBook
 * @see EditSubscription
 * @see SubList
 */
public class ViewSubscription extends AppCompatActivity {

    private boolean delete = false;
    private Context context = this;
    /**
     * Called upon creation of the activity. Displays the subscription data
     * passed to it.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subscription);

        /* Set data to TextView objects */
        String name = getIntent().getStringExtra("name");
        TextView subName = (TextView) findViewById(R.id.viewSubName);
        subName.setText(name);

        String date = getIntent().getStringExtra("date");
        TextView subDate = (TextView) findViewById(R.id.viewSubDate);
        subDate.setText(date);

        String charge = getIntent().getStringExtra("charge");
        TextView subCharge = (TextView) findViewById(R.id.viewSubCharge);
        subCharge.setText(charge);

        String comment = getIntent().getStringExtra("comment");
        TextView subComment = (TextView) findViewById(R.id.viewSubComment);
        subCharge.setText(comment);


        /* Called when the 'Edit' button is clicked */
        /*
        View.OnClickListener editSubListener = new View.OnClickListener() {
            public void onClick(View view) {
                Intent editSub = new Intent(context , EditSubscription.class);
                startActivityForResult(editSub, 6);
                onActivityResult(6, RESULT_OK, editSub);
            }
        };
        Button editButton = (Button) findViewById(R.id.editSubButton);
        editButton.setOnClickListener(editSubListener);
        */

        /* Called when the 'Delete' button is clicked */
        View.OnClickListener deleteSubListener = new View.OnClickListener() {
            public void onClick(View view) {
                delete = true;
                finish();
            }
        };
        Button deleteButton = (Button) findViewById(R.id.deleteSubButton);
        deleteButton.setOnClickListener(deleteSubListener);
    }

    @Override
    public void finish() {
        setResult(RESULT_OK);
        if (!delete){
            //implement editing exit
            super.finish();
        }
        else {
            /* User is deleting the subscription */
            Intent returnData = new Intent();
            returnData.putExtra("delete", true);
            super.finish();
        }
    }
}
