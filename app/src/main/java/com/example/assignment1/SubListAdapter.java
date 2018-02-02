
/*
 *
 * SubListAdapter
 *
 * January 30, 2018
 *
 * Copyright (c) 2018 Lauren H.-Leblanc, CMPUT 301,
 * University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions
  * of the Code of Student Behaviour at the University of Alberta.
 */

package com.example.assignment1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Represents an ArrayAdapter that deals with a SubList object.
 *
 * @author Lauren HL
 * @see SubList
 * @see Subscription
 * @see NewSubscription
 * @see SubBook
 */
public class SubListAdapter extends ArrayAdapter {
    /*
     * Some excerpts of code in this Class adapted from
     * Andy O'Sullivan, April 12 2017
     * https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc
     */
    private Activity context;
    private SubList subList;

    /**
     * Constructs a SubListAdapter object.
     *
     * @param context The activity using SubListAdapter.
     * @param subList The list of subscriptions.
     */
    public SubListAdapter(Activity context, SubList subList){

        super(context,R.layout.listview_row , (List) subList);
        this.subList = subList;

    }

    /**
     * Sets values for each row of the ListView in MainActivity.
     *
     * @param position Index of the subscriptions in SubList.
     * @param view Reference to the TextView objects.
     * @param parent
     * @return A rowView object (data to populate one row).
     */
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        /* Finds the TextView objects in the ListView row. */
        TextView SubName = (TextView) rowView.findViewById(R.id.subListTitle);
        TextView SubDate = (TextView) rowView.findViewById(R.id.subListDate);
        TextView SubCharge = (TextView) rowView.findViewById(R.id.subListCharge);
        TextView SubComment = (TextView) rowView.findViewById(R.id.SubListComment);

        /* Sets the TextView objects' values to those in the subscription. */
        SubName.setText(subList.getSubName(position));
        SubDate.setText(subList.getSubDate(position).toString());
        SubCharge.setText(subList.getSubDate(position).toString());
        SubComment.setText(subList.getSubComment(position));

        return rowView;
    }
}

