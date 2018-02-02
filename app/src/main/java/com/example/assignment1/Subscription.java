/*
 *
 * Subscription
 *
 * January 22, 2018
 *
 * Copyright (c) 2018 Lauren H.-Leblanc, CMPUT 301,
 * University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions
  * of the Code of Student Behaviour at the University of Alberta.
 */

package com.example.assignment1;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a subscription.
 *
 * @author Lauren H.L.
 * @see SubList
 * @see SubListAdapter
 * @see NewSubscription
 */
public class Subscription implements Serializable {

    private Double monthlyCharge;
    private Date dateStarted;
    private String subName;
    private String comment;

    /**
     * Constructs a Subscription object.
     *
     * @param charge The subscription's monthly charge.
     * @param date The subscription's start date.
     * @param name The subscription's name.
     * @param comment An optional user comment.
     */
    public Subscription( Double charge, Date date, String name, String comment){
        this.monthlyCharge = charge;
        this.dateStarted = date;
        this.subName = name;
        this.comment = comment;
    }

    /**
     * Constructs a Subscription object without a comment.
     *
     * @param charge The subscription's monthly charge.
     * @param date The subscription's start date.
     * @param name The subscription's name.
     */
    public Subscription( Double charge, Date date, String name){
        //no comment has been supplied, so it is set to blank
        this.monthlyCharge = charge;
        this.dateStarted = date;
        this.subName = name;
    }

    /**
     * A default constructor for Subscription; should never be called.
     */
    private Subscription(){
        this( 0.0, new Date(), "Default", "");
    }

    /**
     * Returns the subscription charge.
     * @return Monthly charge.
     */
    public Double getCharge() {
        return this.monthlyCharge;
    }

    /**
     * Returns the subscription date.
     *
     * @return Date started.
     */
    public Date getDate() {
        return this.dateStarted;
    }

    /**
     * Returns the subscription name.
     *
     * @return Subscription name.
     */
    public String getName() {
        return this.subName;
    }

    /**
     * Returns the comment.
     *
     * @return Subscription comment.
     */
    public String getComment() {
        return this.comment;
    }


    /**
     * Sets the subscription monthly charge.
     *
     * @param charge Monthly charge.
     */
    public void setCharge( Double charge ) {
        this.monthlyCharge = charge;
    }

    /**
     * Sets the subscription start date.
     *
     * @param date Date started.
     */
    public void setDate( Date date ) {
        this.dateStarted = date;
    }

    /**
     * Sets the subscription name.
     *
     * @param name Subscription name.
     */
    public void setName( String name ){
        this.subName = name;
    }

    /**
     * Sets the subscription comment.
     *
     * @param comment Subscription comment.
     */
    public void setComment( String comment ){
        this.comment = comment;
    }
}
