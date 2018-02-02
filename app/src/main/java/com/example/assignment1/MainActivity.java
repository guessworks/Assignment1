/*
 *
 * MainActivity
 *
 * January 22, 2018
 *
 * Copyright (c) 2018 Lauren H.-Leblanc, CMPUT 301,
 * University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions
  * of the Code of Student Behaviour at the University of Alberta.
 */
package com.example.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Date;

/**
 * Is the main activity from which the app is run.
 *
 * @author Lauren H.-L.
 * @see NewSubscription
 */
public class MainActivity extends AppCompatActivity {

    public SubList subList;
    Intent newSub = new Intent(this, NewSubscription.class);

    /**
     * On creation, display layout and create a new SubList.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_book);
        subList = new SubList();
        ListView listView;

        /* Creates a new SubListAdapter */
        SubListAdapter subListAdapter = new SubListAdapter(this, subList);
        listView = (ListView) findViewById(R.id.subListView);
        listView.setAdapter(subListAdapter);

        /* Captures buttons from layout */
        Button newSubButton = (Button) findViewById(R.id.button2);
        newSubButton.setOnClickListener(newSubListener);
    }


    /**
     * Called when "New Subscription" button is clicked.
     * Starts the NewSubscription subactivity.
     *
     * @param view
     */
    View.OnClickListener newSubListener = new View.OnClickListener() {
        public void onClick(View view) {
            startActivityForResult(newSub, 5);
            onActivityResult(5, RESULT_OK, newSub);
        }
    };

    /**
     * Processes the data sent by the completed NewSubscription activity.
     *
     * @param requestCode An int value which represents the subactivity.
     * @param resultCode An int value that depends on the termination status of
     *                   the subactivity.
     * @param data An intent object containing data passed from the subactivity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 5) {
            Subscription newSubscription = (Subscription) data.getExtras().get("newSub");
            subList.newSub(newSubscription);
            Toast toast = new Toast(getApplicationContext());
            toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT);
        }
    }

}
