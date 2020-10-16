package com.example.mareu.Controler.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.example.mareu.Controler.DI.DI;
import com.example.mareu.Controler.RecyclerView.MeetingFragment;
import com.example.mareu.R;
import com.example.mareu.Model.Meeting;
import com.example.mareu.Controler.service.ApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

public class ListMeetingActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private FloatingActionButton addMeetingFab;
    String dateToFilter;
    MeetingFragment meetingFragment = new MeetingFragment();

    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
        mApiService = DI.getNewInstanceApiService();

        findview();
        setUpToolbar();
        fragmentContainer();
        setAddMeetingFab();
    }

    private void findview() {
        addMeetingFab = findViewById(R.id.add_metting_button);
        mToolbar = findViewById(R.id.toolbar);
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.ic_baseline_filter_list_24));
    }

    private void fragmentContainer() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, meetingFragment);
        fragmentTransaction.commit();
    }

    private void setAddMeetingFab() {
        addMeetingFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListMeetingActivity.this, AddMeetingActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.date_filter){
            getDate();
        } else if(item.getTitle().toString().contains("Salle")){
            String roomToFilter = item.getTitle().toString();
            List<Meeting> filteredRoomList = mApiService.getMeetingsFilteredByRoom(roomToFilter);
            meetingFragment.setFilteredList(filteredRoomList);
        } else if (item.getItemId() == R.id.initial_filter){
            meetingFragment.setFilteredList(mApiService.getMeetings());
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDate() {
        final Calendar calendar = getInstance();
        int mDay = calendar.get(DAY_OF_MONTH);
        int mMonth = calendar.get(MONTH);
        int mYear = calendar.get(YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ListMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateToFilter = dayOfMonth + "/" + (month + 1) + "/" + year;
                List<Meeting> filteredDateList = mApiService.getMeetingsFilteredByDate(dateToFilter);
                meetingFragment.setFilteredList(filteredDateList);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}