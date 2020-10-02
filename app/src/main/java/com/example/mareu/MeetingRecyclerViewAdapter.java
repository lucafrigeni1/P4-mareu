package com.example.mareu;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.mareu.Event.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    public MeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Meeting meeting = mMeetings.get(position);

        holder.mStartingTime.setText(meeting.getConvertStartTime());
        holder.mEndingTime.setText(meeting.getConvertEndTime());
        holder.mDate.setText(meeting.getDate());
        holder.mRoom.setText(meeting.getRoom());
        holder.mTopic.setText(meeting.getTopic());
        holder.mMail.setText(meeting.getMail().toString().replace('[', ' ' ).replace(']', ' '));
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
        holder.mMeetingFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailMeetingActivity.class);
                intent.putExtra("Meeting", (Parcelable) meeting);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mStartingTime;
        public TextView mEndingTime;
        public TextView mDate;
        public TextView mRoom;
        public TextView mTopic;
        public TextView mMail;
        public ImageButton mDeleteButton;
        public ConstraintLayout mMeetingFragment;

        public ViewHolder(View view) {
            super(view);
            mStartingTime = view.findViewById(R.id.start_text);
            mEndingTime = view.findViewById(R.id.end_text);
            mDate = view.findViewById(R.id.date_text);
            mRoom = view.findViewById(R.id.room_text);
            mTopic = view.findViewById(R.id.topic_text);
            mMail = view.findViewById(R.id.user_mail_text);
            mDeleteButton = view.findViewById(R.id.delete_button);
            mMeetingFragment = view.findViewById(R.id.fragment_layout);
        }
    }
}
