package com.project.msrit.pretick.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.project.msrit.pretick.R;
import com.project.msrit.pretick.presentation.adapter.TicketStatusListAdapter;

import java.util.ArrayList;

public class TicketStatusListActivity extends AppCompatActivity implements TicketStatusListAdapter.ItemClickListener {

    TicketStatusListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);

        // data to populate the RecyclerView with
        ArrayList<String> tickets = new ArrayList<>();
        tickets.add("Ticket No: 1524R42");
        tickets.add("Ticket No: 616F62Y");
        tickets.add("Ticket No: 95FD5RH");
        tickets.add("Ticket No: MT56YH3");
        tickets.add("Ticket No: P5G76JK");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TicketStatusListAdapter(this, tickets);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}