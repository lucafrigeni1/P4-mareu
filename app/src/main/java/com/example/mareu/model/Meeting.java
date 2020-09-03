package com.example.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Meeting implements Parcelable {

    private String topic;
    private Date startTime;
    private Date endTime;
    private String date;
    private String room;
    private String mail;
    private Boolean Available = true;

    public Meeting() {
    }

    public Meeting(String topic, Date startTime, Date endTime, String date, String room, String mail, Boolean available) {
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.room = room;
        this.mail = mail;
        Available = available;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean isAvailable() {
        return Available;
    }

    public void setAvailable(Boolean available) {
        Available = available;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(topic);
        parcel.writeString(date);
        parcel.writeString(room);
        parcel.writeString(mail);
        parcel.writeString(String.valueOf(Available));
    }

    protected Meeting(Parcel in) {
        topic = in.readString();
        date = in.readString();
        room = in.readString();
        mail = in.readString();
        Available = Boolean.valueOf(in.readString());
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };
}
