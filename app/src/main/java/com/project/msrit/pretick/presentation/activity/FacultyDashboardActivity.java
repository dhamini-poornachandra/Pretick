package com.project.msrit.pretick.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.project.msrit.pretick.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dhamini-poorna-chandra on 9/1/2018.
 */

public class FacultyDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashboard);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.view_pending_requests)
    public void viewRequests() {
        startActivity(new Intent(this, PendingTicketListActivity.class));
    }

}
