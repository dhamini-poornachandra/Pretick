package com.project.msrit.pretick.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.msrit.pretick.R;
import com.project.msrit.pretick.data.network.model.Ticketstatus;

import java.util.Collections;
import java.util.List;

/**
 * Created by plank-dhamini on 11/01/18.
 */

public class PendingTicketListAdapter extends RecyclerView.Adapter<PendingTicketListAdapter.ViewHolder> {

    private List<Ticketstatus> mData = Collections.emptyList();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public PendingTicketListAdapter(Context context, List<Ticketstatus> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //set view data here
        String ticketNum = mData.get(position).getTicketno();
        holder.ticketNumber.setText("Ticket No: " + ticketNum);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ticketNumber;

        ViewHolder(View itemView) {
            super(itemView);
            ticketNumber = itemView.findViewById(R.id.ticket_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id).getTicketno();

    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

