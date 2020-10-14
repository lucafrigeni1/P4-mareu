package com.example.mareu;

import com.example.mareu.Controler.DI.DI;
import com.example.mareu.Model.Meeting;
import com.example.mareu.Controler.service.ApiService;
import com.example.mareu.Controler.service.MeetingGenerator;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private ApiService service;

    public static List<String> mail = new ArrayList<String>(){
        {
            add("luca@lamezone.com");
        }
    };

    private static final Date startHours = new Date(2020,1,1,12,0,0);
    private static final Date endHours = new Date(2020,1,1,14,0,0);

    Meeting meeting = new Meeting("Réunion Test",startHours,endHours,"1/1/2020","Salle 1",mail);

    @Before
    public void setup(){service = DI.getNewInstanceApiService();}

    @Test
    public void getMeetingsWithSuccess(){
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = MeetingGenerator.MEETING;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void addMeetingWithSuccess(){
        service.addMeeting(meeting);
        assertTrue(service.getMeetings().contains(meeting));
    }

    @Test
    public void deleteMeetingWithSuccess(){
        service.addMeeting(meeting);
        service.deleteMeeting(meeting);
        assertFalse(service.getMeetings().contains(meeting));
    }

    @Test
    public void meetingIsFilterByDateWithSuccess(){
        service.addMeeting(meeting);
        assertTrue(service.getMeetingsFilteredByDate("1/1/2020").contains(meeting));
        assertFalse(service.getMeetingsFilteredByDate("2/1/2020").contains(meeting));
    }

    @Test
    public void meetingIsFilterByRoomWithSuccess(){
        service.addMeeting(meeting);
        assertTrue(service.getMeetingsFilteredByRoom("Salle 1").contains(meeting));
        assertFalse(service.getMeetingsFilteredByRoom("Salle 2").contains(meeting));
    }

    @Test
    public void availabilityTest(){
        service.meetingAvailability(meeting);
        assertFalse(meeting.isAvailable());
    }
}