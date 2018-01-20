package com.project.msrit.pretick.presentation.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.project.msrit.pretick.R;
import com.project.msrit.pretick.data.network.model.ContactPerson;
import com.project.msrit.pretick.data.network.model.GlobalVariable;
import com.project.msrit.pretick.data.network.model.Ticketstatus;
import com.project.msrit.pretick.data.network.service.RestService;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GuestDashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_dashboard);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.view_ticket_status)
    public void viewRequestStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        RestService rs = new RestService();
        rs.getGuestTicketRequests(sharedPreferences.getString("username", "9739626595"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Ticketstatus>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(GuestDashboardActivity.this,
                                "Failed to get contact person details", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Ticketstatus> response) {

                        GlobalVariable.getInstance().setGuestTicketStatus(response);

                        startActivity(new Intent(getApplicationContext(), GuestTicketStatusListActivity.class));

                    }

                });

    }

    @OnClick(R.id.raise_ticket)
    public void raiseRequest() {
        RestService rs = new RestService();
        rs.getContactPersons()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ContactPerson>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(GuestDashboardActivity.this,
                                "Failed to get contact person details", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<ContactPerson> response) {
                        GlobalVariable.getInstance().setContactPersons(response);
                        startActivity(new Intent(getApplicationContext(), RaiseTicketActivity.class));


                    }

                });

    }
}
