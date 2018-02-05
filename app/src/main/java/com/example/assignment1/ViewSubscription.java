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

    private int delete;
    private Context context = this;
    private Intent editedSub;
    private Subscription sub;
    private int position;
    TextView subName;
    TextView subDate;
    TextView subCharge;
    TextView subComment;

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

        position = getIntent().getIntExtra("position", 0);

        /* Set data to TextView objects */
        subName = (TextView) findViewById(R.id.viewSubName);
        String name = getIntent().getStringExtra("name");
        subName.setText(name);

        String date = getIntent().getStringExtra("date");
        subDate = (TextView) findViewById(R.id.viewSubDate);
        subDate.setText(date);

        String charge = getIntent().getStringExtra("charge");
        subCharge = (TextView) findViewById(R.id.viewSubCharge);
        subCharge.setText(charge);

        String comment = getIntent().getStringExtra("comment");
        subComment = (TextView) findViewById(R.id.viewSubComment);
        subComment.setText(comment);


        /* Called when the 'Edit' button is clicked */
        View.OnClickListener editSubListener = new View.OnClickListener() {
            public void onClick(View view) {
                Intent editSub = new Intent(context , EditSubscription.class);
                startActivityForResult(editSub, 6);
                onActivityResult(6, RESULT_OK, editSub);
                finish();
            }
        };
        Button editButton = (Button) findViewById(R.id.editSubButton);
        editButton.setOnClickListener(editSubListener);


        /* Called when the 'Delete' button is clicked */
        View.OnClickListener deleteSubListener = new View.OnClickListener() {
            public void onClick(View view) {
                delete = 1;
                finish();
            }
        };
        Button deleteButton = (Button) findViewById(R.id.deleteSubButton);
        deleteButton.setOnClickListener(deleteSubListener);
    }

    /**
     * Called when the ViewSubscription activity is ended.
     */
    @Override
    public void finish() {
        if (delete == 1){
            /* User is deleting the subscription */
            Intent returnData = new Intent();
            returnData.putExtra("delete", 1);
            returnData.putExtra("position", position);
            setResult(RESULT_OK, returnData);
            super.finish();
        }
        else{
            /* User edited the subscription */
            Intent returnData = new Intent();
            returnData.putExtra("delete", 0);
            returnData.putExtra("position", position);
            returnData.putExtra("editedSub", sub);
            setResult(RESULT_OK, returnData);
            super.finish();
        }
    }

    /**
     * Dictates what is done with the result of the EditSubscription sub-activity.
     *
     * @param resultCode -1 if the activity exited without errors and without being cancelled.
     * @param requestCode An integer which identifies the sub-activity being terminated.
     * @param data The sub-activity's return data.
     */
    public void onActivityResult(int resultCode, int requestCode, Intent data){
        if ((requestCode == 6) && (resultCode == RESULT_OK)){
            if (data.getExtras() != null) {
                sub = (Subscription) data.getExtras().get("editSub");
                delete = 0;
            }
        }
        else {}
    }
}
