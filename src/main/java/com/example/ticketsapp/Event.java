package com.example.ticketsapp;

import java.util.Date;

public class Event {
    private int eventID;
    private String title;
    private String description;
    private Date date = new Date();
    private float price;
    private int maxNoTickets;

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMaxNoTickets() {
        return maxNoTickets;
    }

    public void setMaxNoTickets(int maxNoTickets) {
        this.maxNoTickets = maxNoTickets;
    }
}
