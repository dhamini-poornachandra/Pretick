package com.project.msrit.pretick.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.project.msrit.pretick.R;
import com.project.msrit.pretick.data.network.model.GlobalVariable;
import com.project.msrit.pretick.presentation.adapter.FacultyPendingTicketsAdapter;

public class FacultyPendingTicketsListActivity extends AppCompatActivity implements FacultyPendingTicketsAdapter.ItemClickListener {
    FacultyPendingTicketsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FacultyPendingTicketsAdapter(this, GlobalVariable.getInstance().getFacultyPendingTicketStatus());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, FacultyTicketDetailsActivity.class);
        intent.putExtra("row", String.valueOf(position));
        intent.putExtra("viewOnly", false);
        startActivity(intent);
//        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
