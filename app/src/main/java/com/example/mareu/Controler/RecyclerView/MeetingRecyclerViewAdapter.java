package com.example.mareu.Controler.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mareu.Controler.Activity.DetailMeetingActivity;
import com.example.mareu.Controler.Event.DeleteMeetingEvent;
import com.example.mareu.R;
import com.example.mareu.Model.Meeting;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    private final Context mContext;
    public MeetingRecyclerViewAdapter(List<Meeting> items, Context context) {
        mMeetings = items;
        this.mContext = context;
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

        if (meeting.getRoom().equals(mContext.getString(R.string.salle_1))){
            holder.mColor.setImageResource(R.drawable.ic_baseline_brightness_blue_1_24);
        } else if (meeting.getRoom().equals(mContext.getString(R.string.salle_2))) {
            holder.mColor.setImageResource(R.drawable.ic_baseline_brightness_red_1_24);
        } else if (meeting.getRoom().equals(mContext.getString(R.string.salle_3))) {
            holder.mColor.setImageResource(R.drawable.ic_baseline_brightness_green_1_24);
        } else if (meeting.getRoom().equals(mContext.getString(R.string.salle_4))) {
            holder.mColor.setImageResource(R.drawable.ic_baseline_brightness_yellow_1_24);
        } else if (meeting.getRoom().equals(mContext.getString(R.string.salle_5))) {
            holder.mColor.setImageResource(R.drawable.ic_baseline_brightness_purple_1_24);
        } else if (meeting.getRoom().equals(mContext.getString(R.string.salle_6))) {
            holder.mColor.setImageResource(R.drawable.ic_baseline_brightness_pink_1_24);
        } else if (meeting.getRoom().equals(mContext.getString(R.string.salle_7))) {
            holder.mColor.setImageResource(R.drawable.ic_baseline_brightness_orange_1_24);
        } else if (meeting.getRoom().equals(mContext.getString(R.string.salle_8))) {
            holder.mColor.setImageResource(R.drawable.ic_baseline_brightness_brown_1_24);
        } else if (meeting.getRoom().equals(mContext.getString(R.string.salle_9))) {
            holder.mColor.setImageResource(R.drawable.ic_baseline_brightness_black_1_24);
        } else {
            holder.mColor.setImageResource(R.drawable.ic_baseline_brightness_grey_1_24);
        }
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mColor;
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
            mColor = view.findViewById(R.id.circle);
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
