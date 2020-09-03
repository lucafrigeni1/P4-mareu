package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MeetingGenerator {

    public static List<Meeting> MEETING = Arrays.asList(
            //new Meeting("Réunion 1",null,null,"01/01/2020","salle 1", "Maxime@Lamezone.com",true)
    );
    static List<Meeting> generateMeeting() {
        return new ArrayList<>(MEETING);
    }
}
