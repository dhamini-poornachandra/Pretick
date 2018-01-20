package com.project.msrit.pretick.presentation.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.msrit.pretick.R;
import com.project.msrit.pretick.data.network.model.GlobalVariable;
import com.project.msrit.pretick.data.network.service.RestService;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AdminTicketDetailsActivity extends AppCompatActivity {

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

    @BindView(R.id.approve)
    Button approve;

    @BindView(R.id.reject)
    Button reject;

    String approvalStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        ButterKnife.bind(this);

        if (getIntent().getBooleanExtra("viewOnly", false)) {
            approve.setVisibility(View.GONE);
            reject.setVisibility(View.GONE);

            ticketNo.setText(GlobalVariable.getInstance().getAdminApprovedTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getTicketno());

            orgName.setText(GlobalVariable.getInstance().getAdminApprovedTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getOrganization().toUpperCase());

            date.setText(GlobalVariable.getInstance().getAdminApprovedTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getDate());

            startTime.setText(GlobalVariable.getInstance().getAdminApprovedTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getStarttime());

            endTime.setText(GlobalVariable.getInstance().getAdminApprovedTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getEndtime());

            slot.setText(GlobalVariable.getInstance().getAdminApprovedTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getSlot().toUpperCase());

            contactperson.setText(GlobalVariable.getInstance().getAdminApprovedTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getContactperson().toUpperCase());

            referenceApproval.setText(GlobalVariable.getInstance().getAdminApprovedTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getReferenceapproval().toUpperCase());

            adminApproval.setText(GlobalVariable.getInstance().getAdminApprovedTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getAdminapproval().toUpperCase());

        } else {
            approve.setVisibility(View.VISIBLE);
            reject.setVisibility(View.VISIBLE);

            ticketNo.setText(GlobalVariable.getInstance().getAdminPendingTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getTicketno());

            orgName.setText(GlobalVariable.getInstance().getAdminPendingTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getOrganization().toUpperCase());

            date.setText(GlobalVariable.getInstance().getAdminPendingTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getDate());

            startTime.setText(GlobalVariable.getInstance().getAdminPendingTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getStarttime());

            endTime.setText(GlobalVariable.getInstance().getAdminPendingTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getEndtime());

            slot.setText(GlobalVariable.getInstance().getAdminPendingTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getSlot().toUpperCase());

            contactperson.setText(GlobalVariable.getInstance().getAdminPendingTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getContactperson().toUpperCase());

            referenceApproval.setText(GlobalVariable.getInstance().getAdminPendingTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getReferenceapproval().toUpperCase());

            adminApproval.setText(GlobalVariable.getInstance().getAdminPendingTicketStatus()
                    .get(Integer.valueOf(getIntent().getStringExtra("row"))).getAdminapproval().toUpperCase());
        }

    }

    @OnClick(R.id.approve)
    public void approve() {
        approvalStatus = "approved";
        sendApproval();
    }

    @OnClick(R.id.reject)
    public void reject() {
        approvalStatus = "rejected";
        sendApproval();
    }

    public void sendApproval() {

        SharedPreferences sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        RestService rs = new RestService();
        rs.postAdminTicketApproval(
                ticketNo.getText().toString(),
                approvalStatus,
                date.getText().toString(),
                startTime.getText().toString(),
                endTime.getText().toString(),
                sharedPreferences.getString("username", "9739626595"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Void>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AdminTicketDetailsActivity.this, "Approval failed", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(retrofit2.Response<Void> response) {
                        if (response.code() == HttpsURLConnection.HTTP_NOT_MODIFIED) {
                            Toast.makeText(AdminTicketDetailsActivity.this, "Approval failed " + response.code(), Toast.LENGTH_LONG).show();
                        } else {
                            adminApproval.setText(approvalStatus.toUpperCase());
                            Toast.makeText(AdminTicketDetailsActivity.this, "Approval success " + response.code(), Toast.LENGTH_LONG).show();
                        }

                        Log.d("Response code", String.valueOf(response.code()));

                    }

                });
    }
}
