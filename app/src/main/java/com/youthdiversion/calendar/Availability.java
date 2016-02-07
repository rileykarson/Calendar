package com.youthdiversion.calendar;


/**
 * Created by Christopher on 2/6/2016.
 */
public class Availability {
    private int avail_id, member_id;
    private String startTime;
    private String endTime;
    private String sent;

    public Availability(int id,int member_id, String startTime, String endTime, String sent) {
        this.avail_id = id;
        this.member_id = member_id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sent = sent;
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

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }
}
