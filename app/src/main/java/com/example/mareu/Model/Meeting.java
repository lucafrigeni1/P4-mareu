package com.example.mareu.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Meeting implements Parcelable {

    private String topic;
    private Date startTime;
    private Date endTime;
    private String date;
    private String room;
    private List<String> mail;
    private Boolean Available = true;

    public Meeting() {
    }

    public Meeting(String topic, Date startTime,Date endTime, String date, String room, List<String> mail) {
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.room = room;
        this.mail = mail;
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

    public String getConvertStartTime() {
        return (String) DateFormat.format("HH:mm", startTime);
    }

    public String getConvertEndTime() {
      return (String) DateFormat.format("HH:mm", endTime);
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

    public List<String> getMail() {
        return mail;
    }

    public void setMail(List<String> mail) {
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
        parcel.writeSerializable(startTime);
        parcel.writeSerializable(endTime);
        parcel.writeString(date);
        parcel.writeString(room);
        parcel.writeStringList(mail);
    }

    protected Meeting(Parcel in) {
        topic = in.readString();
        startTime = (Date) in.readSerializable();
        endTime = (Date) in.readSerializable();
        date = in.readString();
        room = in.readString();
        mail = new ArrayList<>();
        in.readStringList(mail);

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
