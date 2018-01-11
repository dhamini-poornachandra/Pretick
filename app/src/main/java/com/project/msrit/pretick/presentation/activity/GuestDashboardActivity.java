package com.project.msrit.pretick.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.project.msrit.pretick.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuestDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_dashboard);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.view_ticket_status)
    public void viewRequestStatus() {
        startActivity(new Intent(getApplicationContext(), TicketStatusListActivity.class));
    }

    @OnClick(R.id.raise_ticket)
    public void raiseRequest() {
        startActivity(new Intent(getApplicationContext(), RaiseTicketActivity.class));
    }
}
