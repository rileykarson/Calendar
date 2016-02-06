package com.youthdiversion.calendar;

import java.sql.Date;
import java.sql.Time;
import java.util.GregorianCalendar;

/**
 * Created by Christopher on 2/6/2016.
 */
public class Availability {
    private int avail_id, member_id;
    private String startTime;
    private String endTime;
    private String date;

    public Availability(int id,int member_id, String startTime, String endTime, String date) {
        this.avail_id = id;
        this.member_id = member_id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public int getId() {
        return avail_id;
    }

    public void setId(int id) {
        this.avail_id = id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
