package com.project.msrit.pretick.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.project.msrit.pretick.R;
import com.project.msrit.pretick.data.network.model.GlobalVariable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketDetailsActivity extends AppCompatActivity {

    @BindView(R.id.ticket_no)
    TextView ticketNo;

    @BindView(R.id.org_name)
    TextView orgName;

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.start_time)
    TextView startTime;

    @BindView(R.id.end_time)
    TextView endTime;

    @BindView(R.id.slot)
    TextView slot;

    @BindView(R.id.contactperson)
    TextView contactperson;

    @BindView(R.id.referenceapproval)
    TextView referenceApproval;

    @BindView(R.id.adminapproval)
    TextView adminApproval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        ButterKnife.bind(this);

        ticketNo.setText(GlobalVariable.getInstance().getPendingTicketStatus()
                .get(Integer.valueOf(getIntent().getStringExtra("row"))).getTicketno());

        orgName.setText(GlobalVariable.getInstance().getPendingTicketStatus()
                .get(Integer.valueOf(getIntent().getStringExtra("row"))).getOrganization().toUpperCase());

        date.setText(GlobalVariable.getInstance().getPendingTicketStatus()
                .get(Integer.valueOf(getIntent().getStringExtra("row"))).getDate());

        startTime.setText(GlobalVariable.getInstance().getPendingTicketStatus()
                .get(Integer.valueOf(getIntent().getStringExtra("row"))).getStarttime());

        endTime.setText(GlobalVariable.getInstance().getPendingTicketStatus()
                .get(Integer.valueOf(getIntent().getStringExtra("row"))).getEndtime());

        slot.setText(GlobalVariable.getInstance().getPendingTicketStatus()
                .get(Integer.valueOf(getIntent().getStringExtra("row"))).getSlot().toUpperCase());

        contactperson.setText(GlobalVariable.getInstance().getPendingTicketStatus()
                .get(Integer.valueOf(getIntent().getStringExtra("row"))).getContactperson().toUpperCase());

        referenceApproval.setText(GlobalVariable.getInstance().getPendingTicketStatus()
                .get(Integer.valueOf(getIntent().getStringExtra("row"))).getReferenceapproval().toUpperCase());

        adminApproval.setText(GlobalVariable.getInstance().getPendingTicketStatus()
                .get(Integer.valueOf(getIntent().getStringExtra("row"))).getAdminapproval().toUpperCase());

    }
}
