package com.example.mareu.Controler.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mareu.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MailRecyclerViewAdapter extends RecyclerView.Adapter<MailRecyclerViewAdapter.ViewHolder> {

    private final List<String> mMail;

    public MailRecyclerViewAdapter(List<String> items) {
        mMail = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_mail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MailRecyclerViewAdapter.ViewHolder holder, final int position) {
        final String mail = mMail.get(position);

        holder.mailTv.setText(mail);
        holder.deleteMailSpinnerFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMail.remove(mail);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mailTv;
        public ImageButton deleteMailSpinnerFragment;

        public ViewHolder(View view) {
            super(view);
            mailTv = view.findViewById(R.id.mail_text_fragment);
            deleteMailSpinnerFragment = view.findViewById(R.id.delete_fragment_button);
        }
    }
}
