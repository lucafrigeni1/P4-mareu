package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import java.util.List;

public interface ApiService {

    List<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

    void addMeeting(Meeting meeting);

    String[]getRooms();
}
