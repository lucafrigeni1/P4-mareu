package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListMeetingActivity extends AppCompatActivity {

    private ImageButton filterBtn;
    private FloatingActionButton addMeetingFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        findview();
        fragmentContainer();
        setAddMeetingFab();
        setFilterBtn();
    }

    private void findview(){
        filterBtn = findViewById(R.id.sort_button);
        addMeetingFab = findViewById(R.id.add_metting_button);
    }

    private void fragmentContainer(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new MeetingFragment());
        fragmentTransaction.commit();
    }

    private void setAddMeetingFab(){
        addMeetingFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListMeetingActivity.this,AddMeetingActivity.class));
            }
        });
    }

    private void setFilterBtn(){
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}