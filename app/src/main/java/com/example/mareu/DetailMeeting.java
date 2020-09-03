package com.example.mareu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mareu.model.Meeting;

import androidx.appcompat.app.AppCompatActivity;

public class DetailMeeting extends AppCompatActivity {

    private TextView topic;
    private TextView participants;
    private TextView date;
    private TextView room;
    private ImageButton bReturn;
    private Meeting mMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_meeting);

        findview();
        getMeeting();
        meetingInformations();
        setbReturn();
    }

    private void findview(){
        topic = findViewById(R.id.topic_text);
        participants = findViewById(R.id.mail_text_2);
        date = findViewById(R.id.date_text_2);
        room = findViewById(R.id.room_detail_text);
        bReturn = findViewById(R.id.back_btn);
    }

    private void getMeeting(){
        mMeeting = getIntent().getParcelableExtra("Meeting");
    }

    private void meetingInformations(){
        topic.setText(mMeeting.getTopic());
        participants.setText(mMeeting.getMail());
        date.setText(mMeeting.getDate() + " " + mMeeting.getStartTime() + "-" + mMeeting.getEndTime());
        room.setText(mMeeting.getRoom());
    }

    private void setbReturn(){
        bReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
