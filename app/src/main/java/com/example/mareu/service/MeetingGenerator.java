package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class MeetingGenerator {

    public static List<String> mail = new ArrayList<String>(){
        {
            add("luca@lamezone.com");
        }
    };

    private static final Date startHours = new Date(2020,1,1,12,0,0);
    private static final Date endHours = new Date(2020,1,1,14,0,0);

    public static List<Meeting> MEETING = Arrays.asList(
            new Meeting("Réunion Demo",startHours,endHours,"2/10/2020","Salle 1",mail)
    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(MEETING);
    }
}
