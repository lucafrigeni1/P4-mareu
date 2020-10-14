package com.example.mareu.Controler.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mareu.Controler.RecyclerView.DetailRecyclerViewAdapter;
import com.example.mareu.Model.Meeting;
import com.example.mareu.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class DetailMeetingActivity extends AppCompatActivity {

    private TextView topic;
    private RecyclerView participants;
    private TextView date;
    private TextView room;
    private ImageButton bReturn;
    private Meeting mMeeting;

    DetailRecyclerViewAdapter detailAdapter;
    List<String> detailMailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_meeting);

        findview();
        getMeeting();
        meetingInformations();
        setbReturn();
    }

    private void findview() {
        topic = findViewById(R.id.detail_topic_text);
        participants = findViewById(R.id.detail_mail_list);
        date = findViewById(R.id.date_text);
        room = findViewById(R.id.room_detail_text);
        bReturn = findViewById(R.id.back_btn);
    }

    private void getMeeting() {
        mMeeting = getIntent().getParcelableExtra("Meeting");
    }

    private void meetingInformations() {
        topic.setText(mMeeting.getTopic());
        date.setText(mMeeting.getDate() + " " + mMeeting.getConvertStartTime() + "-" + mMeeting.getConvertEndTime());
        room.setText(mMeeting.getRoom());
        setDetailAdapter();
    }

    private void setDetailAdapter() {
        detailMailList = mMeeting.getMail();
        detailAdapter = new DetailRecyclerViewAdapter(detailMailList);
        participants.setAdapter(detailAdapter);
    }

    private void setbReturn() {
        bReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
