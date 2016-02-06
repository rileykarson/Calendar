package com.youthdiversion.calendar;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Christopher on 2/6/2016.
 */
public class Availability {
    private int id;
    private Time startTime;
    private Time endTime;
    private Date date;

    public Availability(int id, Time startTime, Time endTime, Date date) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
