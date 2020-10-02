package com.example.mareu.service;

import android.util.Log;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingApiService implements ApiService{

    private List<Meeting> meetings = MeetingGenerator.generateMeeting();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void meetingAvailability(Meeting meeting) {
        for (int i = 0; i < getMeetings().size(); i++) {

            Date newStart = meeting.getStartTime();
            Date newEnd = meeting.getEndTime();
            Date existingStart = getMeetings().get(i).getStartTime();
            Date existingEnd = getMeetings().get(i).getEndTime();

            if (meeting.getRoom().equals(getMeetings().get(i).getRoom())
                    && meeting.getDate().equals(getMeetings().get(i).getDate())) {
                if (newStart.after(existingStart)  && newStart.before(existingEnd)
                        || newEnd.after(existingStart) && newEnd.before(existingEnd)
                        || newStart.before(existingStart) && newEnd.after(existingEnd)
                        || newStart.equals(existingStart)
                        || newEnd.equals(existingEnd)
                        ) {
                    meeting.setAvailable(false);
                }
            }
        }
    }

    private String[] rooms = RoomGenerator.generateRoomList();

    public String[]getRooms(){return rooms;}

    @Override
    public List<Meeting> getMeetingsFilteredByDate(String date) {
        List<Meeting> mMeeting = new ArrayList<>();
        for (Meeting meeting : meetings){
            if (meeting.getDate().equals(date)){
                mMeeting.add(meeting);
            }
        }
        return mMeeting;
    }

    @Override
    public List<Meeting> getMeetingsFilteredByRoom(String room) {
        List<Meeting> mMeeting = new ArrayList<>();
       for (Meeting meeting : meetings){
           if (meeting.getRoom().equals(room)){
               mMeeting.add(meeting);
           }
       }
       return mMeeting;
    }
}
