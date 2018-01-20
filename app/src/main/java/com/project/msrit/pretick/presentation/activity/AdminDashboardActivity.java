package com.project.msrit.pretick.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

                        GlobalVariable.getInstance().setPendingTicketStatus(ticketStatus);
                        startActivity(new Intent(getApplicationContext(), PendingTicketListActivity.class));

                    }

                });
    }

}
