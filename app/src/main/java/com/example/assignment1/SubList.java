/*
 *
 * SubList
 *
 * January 22, 2018
 *
 * Copyright (c) 2018 Lauren H.-Leblanc, CMPUT 301,
 * University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions
  * of the Code of Student Behaviour at the University of Alberta.
 */
package com.example.assignment1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Has an ArrayList object containing all Subscription objects.
 *
 * @author Lauren H.-L.
 * @see Subscription
 * @see SubListAdapter
 * @see NewSubscription
 * @see SubBook
 */
public class SubList {

    private ArrayList<Subscription> subListObj;

    /**
     * Constructs a SubList object and creates an ArrayList of Subscriptions.
     */
    public SubList(){
        subListObj = new ArrayList<Subscription>();
    }

    /**
     * Adds a new Subscription object to the list.
     *
     * @param newSubscription The Subscription object to be added.
     */
    public void newSub(Subscription newSubscription) {
        subListObj.add(newSubscription);
    }

    /*
    /**
     * Returns the index of a Subscription if it is contained in SubList.
     *
     * @param targetName The Subscription name of the Subscription to be found.
     * @return The subscription's index, or -1 if it is not found.
     *
    public int searchForSub( String targetName ) {
        int index;
        boolean found = false;

        for (index = 0; index < subListObj.size(); index++) {
            if (Objects.equals(subListObj.get(index).getName(), targetName)) {
                found = true;
                break;
            }
        }
        if (Objects.equals(found, true)) {
            return index;
        }
        else {
            return -1;
        }
    }

    */

    /**
     * Returns a subscription's name based on its index within the SubList.
     *
     * @param index The subscription's position within SubList.
     * @return The subscription's name.
     */
    public String getSubName(int index){
        return subListObj.get(index).getName();
    }

    /**
     * Returns a subscription's date based on its index within the SubList.
     *
     * @param index The subscription's position in SubList.
     * @return The subscription's start date.
     */
    public Date getSubDate(int index){
        return subListObj.get(index).getDate();
    }

    /**
     * Returns a subscription's monthly charge based on its index within the SubList.
     *
     * @param index The subscription's position in the SubList.
     * @return The subscription's monthly charge.
     */
    public Double getSubCharge(int index){
        return subListObj.get(index).getCharge();
    }

    /**
     * Returns a subscription's comment based on its index in SubList.
     *
     * @param index The subscription's position in SubList.
     * @return The subscription comment.
     */
    public String getSubComment(int index){
        return subListObj.get(index).getComment();
    }

    /**
     * Sets a subscription's attributes to new values.
     *
     * @param index The subscription's position in SubList.
     * @param charge The subscription's new monthly charge.
     * @param date The subscription's new start date.
     * @param name The subscription's new name.
     * @param comment The subscription's new comment.
     */
    public void editSub(int index, Double charge, Date date, String name, String comment){
        subListObj.get(index).setCharge(charge);
        subListObj.get(index).setDate(date);
        subListObj.get(index).setName(name);
        subListObj.get(index).setComment(comment);
    }

    /**
     * Removes a subscription from the SubList.
     *
     * @param index The subscription's position in SubList.
     */
    public void deleteSub(int index) {
        subListObj.remove(index);
    }

    /**
    * Returns the subList ArrayList object for use by SubListAdapter.
    */
    public ArrayList<Subscription> getList() {
        return subListObj;
    }

}
