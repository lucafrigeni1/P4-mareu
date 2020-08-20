package com.example.mareu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.mareu.Event.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
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
        holder.mContext.setText(meeting.getContext());
        holder.mTopic.setText(meeting.getTopic());
        holder.mMail.setText(meeting.getMail());
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mContext;
        public TextView mTopic;
        public TextView mMail;
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            mContext = view.findViewById(R.id.context_text);
            mTopic = view.findViewById(R.id.topic_text);
            mMail = view.findViewById(R.id.user_mail_text);
            mDeleteButton = view.findViewById(R.id.delete_button);
        }
    }
}
