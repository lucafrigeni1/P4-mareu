package com.example.mareu;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView addStartHour;
    TextView addEndHour;
    TextView addDate;
    ImageButton Validbtn;
    ImageButton addMailBtn;
    Spinner addRoom;
    EditText addMail;
    EditText addTopic;
    Calendar beginCalendar = Calendar.getInstance();
    Calendar endCalendar = Calendar.getInstance();
    Date time1;
    Date time2;
    String date;
    String room;

    private Meeting meeting;
    private ApiService mApiService;

    RecyclerView mailRW;
    MailRecyclerViewAdapter mailAdapter;
    List<String> mailList;
    String mailItem;
    boolean mailIsExisting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        mApiService = DI.getApiService();

        findview();
        setAddRoom();
        setAddMailBtn();
        setAddDate();
        setAddStartHour();
        setAddEndHour();
        initListMail();
        addMeeting();
    }

    private void findview() {
        addStartHour = findViewById(R.id.hour_start_btn);
        addEndHour = findViewById(R.id.hour_end_btn);
        addDate = findViewById(R.id.date_btn);
        addRoom = findViewById(R.id.room_spinner);
        addTopic = findViewById(R.id.topic_edit);
        addMail = findViewById(R.id.mail_edit);
        addMailBtn = findViewById(R.id.add_mail_btn);
        mailRW = findViewById(R.id.list_mail);
        Validbtn = findViewById(R.id.valid_button);
    }

    private void setMeeting() {
        meeting = new Meeting();
        meeting.setTopic(addTopic.getText().toString());
        meeting.setStartTime(time1);
        meeting.setEndTime(time2);
        meeting.setDate(date);
        meeting.setRoom(room);
        meeting.setMail(mailList);
    }

    private void addMeeting() {
        Validbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMeeting();
                if (meeting.getTopic().equals("")
                        || meeting.getRoom().equals("Séléctionner une salle")
                        || meeting.getMail().equals("")
                        || meeting.getStartTime() == null
                        || meeting.getEndTime() == null
                        || meeting.getDate() == null) {
                    Toast.makeText(AddMeetingActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    mApiService.meetingAvailability(meeting);
                    if (meeting.isAvailable()) {
                        mApiService.addMeeting(meeting);
                        finish();
                    } else {
                        Toast.makeText(AddMeetingActivity.this, "Salle déja occupée pour cet horaire", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void setAddStartHour() {
        addStartHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = getInstance();
                int mHour = calendar.get(HOUR_OF_DAY);
                int mMinute = calendar.get(MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        beginCalendar.set(HOUR_OF_DAY, hourOfDay);
                        beginCalendar.set(MINUTE, minute);
                        beginCalendar.set(SECOND, 0);
                        time1 = beginCalendar.getTime();
                        String convertedTime1 = (String) DateFormat.format("HH:mm", time1);
                        addStartHour.setText(convertedTime1);
                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });
    }

    private void setAddEndHour() {
        addEndHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = getInstance();
                int mHour = calendar.get(HOUR_OF_DAY);
                int mMinute = calendar.get(MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        endCalendar.set(HOUR_OF_DAY, hourOfDay);
                        endCalendar.set(MINUTE, minute);
                        endCalendar.set(SECOND, 0);
                        time2 = endCalendar.getTime();
                        String convertedTime2 = (String) DateFormat.format("HH:mm", time2);
                        addEndHour.setText(convertedTime2);
                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });
    }

    private void setAddDate() {
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = getInstance();
                int mDay = calendar.get(DAY_OF_MONTH);
                int mMonth = calendar.get(MONTH);
                int mYear = calendar.get(YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date = dayOfMonth + "/" + (month + 1) + "/" + year;
                        addDate.setText(date);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void setAddRoom() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mApiService.getRooms());
        addRoom.setAdapter(adapter);
        addRoom.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        room = (String) addRoom.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void setAddMailBtn() {
        addMailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailAllow();
                mailIsOK();
            }
        });
    }

    private void mailAllow() {
        for (int i = 0; i < mailList.size(); i++) {
            String newMail = addMail.getText().toString() + "@lamezone.com";
            String existingMail = mailList.get(i);

            if (newMail.equals(existingMail)) {
                mailIsExisting = true;
            }
        }
    }

    private void mailIsOK(){
        if (addMail.getText().toString().equals("")) {
            Toast.makeText(AddMeetingActivity.this, "Veuillez insérer un mail", Toast.LENGTH_SHORT).show();
        } else {
            if (mailIsExisting) {
                Toast.makeText(AddMeetingActivity.this, "Mail déja saisi", Toast.LENGTH_SHORT).show();
                addMail.getText().clear();
                mailIsExisting = false;
            } else {
                addMailView();
            }
        }
    }

    private void addMailView() {
        mailItem = addMail.getText().toString() + "@lamezone.com";
        mailList.add(mailItem);
        addMail.getText().clear();
        mailAdapter.notifyDataSetChanged();
    }

    private void initListMail() {
        mailList = new ArrayList<>();
        mailAdapter = new MailRecyclerViewAdapter(mailList);
        mailRW.setAdapter(mailAdapter);
    }
}

