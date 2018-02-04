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
 * Is the main activity from which the app is run.
 *
 * @author Lauren H.-L.
 * @see NewSubscription
 */
public class SubBook extends AppCompatActivity {

    public SubList subList= new SubList();
    ListView listView;
    SubListAdapter subListAdapter;
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
                Intent newSub = new Intent(context , NewSubscription.class);
                startActivityForResult(newSub, 5);
                onActivityResult(5, RESULT_OK, newSub);
            }
        };

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

            Type listType = new TypeToken<SubList>(){}.getType();

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
     * @param requestCode An int value which represents the subactivity.
     * @param resultCode An int value that depends on the termination status of
     *                   the subactivity.
     * @param data An intent object containing data passed from the subactivity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 5) {
            if (data.getExtras().get("newSub") != null) {
                Subscription newSubscription = (Subscription) data.getExtras().get("newSub");
                subList.newSub(newSubscription);
                subListAdapter.notifyDataSetChanged();
                saveInFile();
            }
        }
    }
}
