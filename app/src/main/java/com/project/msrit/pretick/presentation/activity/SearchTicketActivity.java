package com.project.msrit.pretick.presentation.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.project.msrit.pretick.R;
import com.project.msrit.pretick.data.network.model.Ticketstatus;
import com.project.msrit.pretick.data.network.service.RestService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dhamini-poorna-chandra on 9/1/2018.
 */

public class SearchTicketActivity extends AppCompatActivity {

    @BindView(R.id.search_bar)
    SearchView searchBar;

    @BindView(R.id.search_button)
    Button searchButton;

    @BindView(R.id.ticket_no)
    TextView ticketNo;

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.start_time)
    TextView startTime;

    @BindView(R.id.end_time)
    TextView endTime;

    @BindView(R.id.slot)
    TextView slot;

    @BindView(R.id.ticket_details)
    RelativeLayout ticketDetails;

    @BindView(R.id.ticket_status_image)
    ImageView ticketStatusImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ticket);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.search_button)
    public void search() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchBar.getWindowToken(), 0);

        RestService rs = new RestService();
        rs.getTicketDetails(searchBar.getQuery().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Ticketstatus>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("The response", "Error");
                    }

                    @Override
                    public void onNext(Ticketstatus response) {
                        if (response != null) {
                            if (response.getAdminapproval().equals("approved") && response.getReferenceapproval().equals("approved")) {
                                ticketDetails.setVisibility(View.VISIBLE);

                                ticketStatusImage.setImageResource(R.drawable.ic_tick);
                                Log.d("The response", response.toString());

                                ticketNo.setText(response.getTicketno());

                                date.setText(response.getDate());

                                startTime.setText(response.getStarttime());

                                endTime.setText(response.getEndtime());

                                slot.setText(response.getSlot());
                            } else {
                                ticketDetails.setVisibility(View.GONE);

                                ticketStatusImage.setImageResource(R.drawable.ic_cross);
                            }

                        } else {
                            ticketDetails.setVisibility(View.GONE);
                            ticketStatusImage.setImageResource(R.drawable.ic_cross);
                            Log.d("The response", response.toString());
                        }

                    }

                });
    }
}
