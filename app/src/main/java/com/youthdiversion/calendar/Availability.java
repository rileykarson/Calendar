package com.youthdiversion.calendar;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Christopher on 2/6/2016.
 */
public class Availability {
    private int avail_id, member_id;
    private Time startTime;
    private Time endTime;
    private Date date;

    public Availability(int id,int member_id, Time startTime, Time endTime, Date date) {
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
