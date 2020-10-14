package com.example.mareu.Controler.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.Controler.DI.DI;
import com.example.mareu.Controler.Event.DeleteMeetingEvent;
import com.example.mareu.R;
import com.example.mareu.Model.Meeting;
import com.example.mareu.Controler.service.ApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MeetingFragment extends Fragment {

    private ApiService mApiService;
    private List<Meeting> mMeeting;
    private RecyclerView mRecyclerView;

    public static MeetingFragment newInstance(){
        MeetingFragment fragment = new MeetingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_list,container,false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    private void initList() {
        mMeeting = mApiService.getMeetings();
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeeting, this.getContext()));
    }

    public void setFilteredList(List<Meeting> filteredList){
        mMeeting = filteredList;
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeeting, this.getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.meeting);
        initList();
    }
}
