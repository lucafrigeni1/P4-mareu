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

import java.util.Calendar;
import java.util.Date;

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
    Spinner addMail;
    EditText addTopic;
    Calendar beginCalendar = Calendar.getInstance();
    Calendar endCalendar = Calendar.getInstance();
    Date time1;
    Date time2;
    String date;
    String room;
    String mail;

    private Meeting meeting;
    private ApiService mApiService;

    RecyclerView mailRW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        mApiService = DI.getApiService();

        findview();
        setAddRoom();
        setAddMail();
        setAddMailBtn();
        setAddDate();
        setAddStartHour();
        setAddEndHour();
        addMeeting();
    }

    private void findview() {
        addStartHour = findViewById(R.id.hour_start_btn);
        addEndHour = findViewById(R.id.hour_end_btn);
        addDate = findViewById(R.id.date_btn);
        addRoom = findViewById(R.id.room_spinner);
        addTopic = findViewById(R.id.topic_edit);
        addMail = findViewById(R.id.mail_spinner);
        addMailBtn = findViewById(R.id.add_mail_btn);
        Validbtn = findViewById(R.id.valid_button);
    }

    private void setMeeting() {
        meeting = new Meeting();
        meeting.setTopic(addTopic.getText().toString());
        meeting.setStartTime(time1);
        meeting.setEndTime(time2);
        meeting.setDate(date);
        meeting.setRoom(room);
        meeting.setMail(mail);
    }

    private void addMeeting() {
        Validbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMeeting();
                if (meeting.getTopic().equals("")
                        || meeting.getRoom().equals("Séléctionner une salle")
                        || meeting.getMail().equals("Séléctionner une adresse mail")
                        || meeting.getStartTime() == null
                        || meeting.getEndTime() == null
                        || meeting.getDate() == null)
                    Toast.makeText(AddMeetingActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                else {
                    availability();
                    if (meeting.isAvailable())
                        mApiService.addMeeting(meeting);
                    finish();
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
                        addStartHour.setText(DateFormat.format("HH:mm", time1));
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
                        addEndHour.setText(DateFormat.format("HH:mm", time2));
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
                        date = dayOfMonth + "/" + month + "/" + year;
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

    private void setAddMail() {
        addMail.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == addRoom.getId()) {
            room = (String) addRoom.getSelectedItem();
        } else {
            mail = (String) addMail.getSelectedItem();
        }
        Toast.makeText(this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void setAddMailBtn() {
        addMailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMailView();
            }
        });
    }

    private void addMailView() {
    }

    public void availability() {
        for (int i = 0; i < mApiService.getMeetings().size(); i++) {
            long newStart = meeting.getStartTime().getTime();
            long newEnd = meeting.getEndTime().getTime();
            long existingStart = mApiService.getMeetings().get(i).getStartTime().getTime();
            long existingEnd = mApiService.getMeetings().get(i).getEndTime().getTime();

            if (room.equals(mApiService.getMeetings().get(i).getRoom()) && date.equals(mApiService.getMeetings().get(i).getDate())) {
                if (newStart >= existingStart && newStart <= existingEnd || newEnd >= existingStart && newEnd <= existingEnd) {
                    meeting.setAvailable(false);
                    Toast.makeText(AddMeetingActivity.this, "Salle déja occupée pour cet horaire", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}