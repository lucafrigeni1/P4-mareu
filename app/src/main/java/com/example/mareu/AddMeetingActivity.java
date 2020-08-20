package com.example.mareu;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mareu.DI.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.ApiService;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView addHour;
    TextView addDate;
    ImageButton Validbtn;
    ImageButton addMailBtn;
    Spinner addRoom;
    Spinner addMail;
    EditText addTopic;
    String time;
    String date;
    String room;
    String mail;

    private int mDay, mMonth, mYear, mHour, mMinute;
    private Meeting meeting;
    private ApiService mApiService;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        mApiService = DI.getApiService();

        findview();
        setAddRoom();
        setAddMail();
        setAddDate();
        setAddHour();
        addMeeting();
    }

    private void findview(){
        addHour = findViewById(R.id.hour_btn);
        addDate = findViewById(R.id.date_btn);
        addRoom = findViewById(R.id.room_spinner);
        addTopic = findViewById(R.id.topic_edit);
        addMail = findViewById(R.id.mail_spinner);
        addMailBtn = findViewById(R.id.add_mail_btn);
        Validbtn = findViewById(R.id.valid_button);
    }

    private void addMeeting(){
        Validbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMeeting();
                //if (meeting.getContext() == mApiService.getMeetings()){Toast.makeText(this,"Salle déja occupé pour cet horaire",Toast.LENGTH_LONG).show();} else {}
                mApiService.addMeeting(meeting);
                finish();
            }
        });
    }

    private void setAddRoom(){
        addRoom.setOnItemSelectedListener(this);
    }

    private void setAddMail(){
        addMail.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == addRoom.getId()){
            room = (String) addRoom.getSelectedItem();
        } else {
            mail = (String) addMail.getSelectedItem();
        }
        Toast.makeText(this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void setAddHour(){
        addHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                mHour = calendar.get(Calendar.HOUR);
                mMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time = hourOfDay + ":" + minute;
                        addHour.setText(time);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
    }

    private void setAddDate(){
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date = dayOfMonth + "/" + month + "/" + year;
                        addDate.setText(date);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void setMeeting(){
        meeting = new Meeting();
        meeting.setTopic(addTopic.getText().toString());
        meeting.setContext(time + " - " + date + " - " + room);
        meeting.setMail(mail);
    }
}
