package com.project.msrit.pretick.presentation.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.project.msrit.pretick.R;
import com.project.msrit.pretick.data.network.model.GlobalVariable;
import com.project.msrit.pretick.data.network.model.Ticketstatus;
import com.project.msrit.pretick.data.network.service.RestService;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dhamini-poorna-chandra on 9/1/2018.
 */

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.view_pending_requests)
    public void viewRequests() {

        RestService rs = new RestService();
        rs.getPendingTickets()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Ticketstatus>>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("Demo", e.getMessage());
                    }

                    @Override
                    public void onNext(List<Ticketstatus> ticketStatus) {
                        if (ticketStatus != null) {
                            GlobalVariable.getInstance().setAdminPendingTicketStatus(ticketStatus);
                            startActivity(new Intent(getApplicationContext(), AdminPendingTicketListActivity.class));
                        } else {
                            Toast.makeText(AdminDashboardActivity.this, "No tickets yet", Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }

    @OnClick(R.id.view_approved_requests)
    public void approvedRequests() {
        RestService rs = new RestService();
        rs.getApprovedTickets()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Ticketstatus>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("failed faculty approved", "failed");
                    }

                    @Override
                    public void onNext(List<Ticketstatus> response) {

                        if (response != null) {
                            GlobalVariable.getInstance().setAdminApprovedTicketStatus(response);

                            startActivity(new Intent(AdminDashboardActivity.this, AdminApprovedTicketListActivity.class));
                        } else {
                            Toast.makeText(AdminDashboardActivity.this, "No tickets yet", Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Exit Application?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        startActivity(new Intent(AdminDashboardActivity.this, LoginActivity.class));
                        finishAffinity();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }
}
