package com.example.mareu.service;

import com.example.mareu.model.Meeting;
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

    private String[] rooms = RoomGenerator.generateRoomList();

    public String[]getRooms(){return rooms;}
}
