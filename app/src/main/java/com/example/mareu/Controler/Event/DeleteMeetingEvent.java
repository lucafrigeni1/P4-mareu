package com.example.mareu.Controler.Event;

import com.example.mareu.Model.Meeting;

public class DeleteMeetingEvent {

    public Meeting meeting;

    public DeleteMeetingEvent(Meeting meeting){this.meeting = meeting;}
}
