/*
 *
 * SubBook
 *
 * January 22, 2018
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Is the main activity from which the app is run. Manages the list of subscriptions
 * through instances of the SubList and SubListAdapter classes. Can begin
 * ViewSubscription and NewSubscription sub-activities.
 *
 * @author Lauren H.-L.
 * @see NewSubscription
 * @see SubList
 * @see SubListAdapter
 * @see ViewSubscription
 */
public class SubBook extends AppCompatActivity {

    public SubList subList = new SubList();
    private ListView listView;
    private SubListAdapter subListAdapter;
    private Context context = this;
    private static final String FILENAME = "saveData.sav";


    /**
     * On creation, display layout and create a new SubList.
     * Also pairs the ListView and SubListAdapter objects,
     * and creates a Button object for the New Subscription button.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_book);

        /* Sets the SubListAdapter to the ListView */
        listView = (ListView) findViewById(R.id.subListView);

        /* Called when the 'New Subscription' button is clicked */
        View.OnClickListener newSubListener = new View.OnClickListener() {
            public void onClick(View view) {
                Intent newSub = new Intent(context, NewSubscription.class);
                startActivityForResult(newSub, 5);
                onActivityResult(5, RESULT_OK, newSub);
            }
        };

        /*
         * Called when a row in the listView is clicked. Sends the data from the
         * subscription to the ViewSubscription sub-activity.
         * This excerpt of code adapted from Andy O'Sullivan, April 12 2017
         * https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent viewSub = new Intent(SubBook.this, ViewSubscription.class);
                int index = position;
                viewSub.putExtra("position", index);
                String name = subList.getSubName(position);
                viewSub.putExtra("name", name);
                String date = subList.getSubDate(position);
                viewSub.putExtra("date", date);
                String charge = subList.getSubCharge(position).toString();
                viewSub.putExtra("charge", charge);
                String comment = subList.getSubComment(position);
                viewSub.putExtra("comment", comment);
                startActivityForResult(viewSub, 4);
                onActivityResult(4, RESULT_OK, viewSub);
            }
        });

        /* Captures buttons from layout */
        Button newSubButton = (Button) findViewById(R.id.button2);
        newSubButton.setOnClickListener(newSubListener);
    }


    /**
     * Called when the activity is started. Sets the SubListAdapter
     * to the listView item, and loads data from a file.
     */
    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();

        /* Creates a new SubListAdapter and pairs it with the ListView */
        subListAdapter = new SubListAdapter(this, subList.getList(), subList);
        listView.setAdapter(subListAdapter);
    }


    /**
     * Writes data out to a saved file.
     * Code adapted from lonelyTwitter, Joshua Charles Campbell, Sept. 14 2015,
     * https://github.com/joshua2ua/lonelyTwitter (after lab 3)
     */
    private void saveInFile() {
        try {
            FileOutputStream fileOutput = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fileOutput));

            Gson gson = new Gson();
            gson.toJson(subList, output);

            output.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * Reads data in from a saved file.
     * Code adapted from lonelyTwitter, Joshua Charles Campbell, Sept. 14 2015,
     * https://github.com/joshua2ua/lonelyTwitter (after lab 3)
     */
    private void loadFromFile() {

        try {
            FileInputStream fileInput = openFileInput(FILENAME);
            BufferedReader input = new BufferedReader(new InputStreamReader(fileInput));

            Gson gson = new Gson();

            Type listType = new TypeToken<SubList>() {
            }.getType();

            subList = gson.fromJson(input, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            subList = new SubList();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /**
     * Processes the data sent by the completed NewSubscription activity.
     *
     * @param requestCode An int value which represents the sub-activity.
     * @param resultCode  An int value that depends on the termination status of
     *                    the sub-activity.
     * @param data        An intent object containing data passed from the sub-activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 5) {
            /* Result actions for the NewSubscription sub-activity */
            if (data.getExtras() != null) {
                /* Add the new subscription to the list, update the ListView, and save */
                Subscription newSubscription = (Subscription) data.getExtras().get("newSub");
                subList.newSub(newSubscription);
                subListAdapter.notifyDataSetChanged();
                saveInFile();
            }
        } else if (resultCode == RESULT_OK && requestCode == 4) {
            /* Result actions for the ViewSubscription sub-activity */
            if (data.getExtras() != null) {

                if (data.getExtras().getInt("delete") == 1) {
                    /* delete subscription */
                    int index = data.getExtras().getInt("position");
                    subList.deleteSub(index);
                    subListAdapter.notifyDataSetChanged();
                    saveInFile();

                } else if (data.getExtras().get("editedSub") != null) {
                    /* update subscription */
                    int index = data.getExtras().getInt("position");
                    Subscription editedSub = (Subscription) data.getExtras().get("editedSub");
                    subList.editSub(index, editedSub);
                    subListAdapter.notifyDataSetChanged();
                    saveInFile();
                    }
                }

        }
    }
}

