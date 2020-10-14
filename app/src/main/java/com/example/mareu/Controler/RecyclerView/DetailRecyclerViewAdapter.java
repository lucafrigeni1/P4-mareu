package com.example.mareu.Controler.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mareu.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DetailRecyclerViewAdapter extends RecyclerView.Adapter<DetailRecyclerViewAdapter.ViewHolder> {

    private final List<String> detailMail;

    public DetailRecyclerViewAdapter(List<String> items) {
        detailMail = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_detail_mail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetailRecyclerViewAdapter.ViewHolder holder, int position) {
        final String mail = detailMail.get(position);

        Log.e( "onBindViewHolder: ", mail );
        holder.participantsTv.setText(mail);
    }

    @Override
    public int getItemCount() {
        return detailMail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView participantsTv;

        public ViewHolder(View view) {
            super(view);
            participantsTv = view.findViewById(R.id.participant);
        }
    }
}
