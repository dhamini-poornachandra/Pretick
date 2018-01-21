package com.project.msrit.pretick.presentation.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
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

public class FacultyDashBoardActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dash_board);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
    }

    @OnClick(R.id.view_pending_requests)
    public void pnd() {
        RestService rs = new RestService();
        rs.getFacultyPendingRequests(sharedPreferences.getString("username", "9739626595"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Ticketstatus>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("failed faculty pending", "failed");
                    }

                    @Override
                    public void onNext(List<Ticketstatus> response) {
                        sharedPreferences.getString("username", "9739626595");
                        if (response != null) {
                            GlobalVariable.getInstance().setFacultyPendingTicketStatus(response);

                            startActivity(new Intent(getApplicationContext(), FacultyPendingTicketsListActivity.class));
                        } else {
                            Toast.makeText(FacultyDashBoardActivity.this, "No tickets yet", Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }

    @OnClick(R.id.view_approved_requests)
    public void approvedRequests() {
        RestService rs = new RestService();
        rs.getFacultyApprovedRequests(sharedPreferences.getString("username", "9739626595"))
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
                        sharedPreferences.getString("username", "9739626595");
                        if (response != null) {
                            GlobalVariable.getInstance().setFacultyApprovedTicketStatus(response);

                            startActivity(new Intent(FacultyDashBoardActivity.this, FacultyApprovedTicketsListActivity.class));
                        } else {
                            Toast.makeText(FacultyDashBoardActivity.this, "No tickets yet", Toast.LENGTH_LONG).show();
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
                        startActivity(new Intent(FacultyDashBoardActivity.this, LoginActivity.class));
                        finishAffinity();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }
}
