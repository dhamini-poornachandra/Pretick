package com.project.msrit.pretick.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.SearchView;

import com.project.msrit.pretick.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dhamini-poorna-chandra on 9/1/2018.
 */

public class SearchTicketActivity extends AppCompatActivity {

    @BindView(R.id.search_bar)
    SearchView searchBar;

    @BindView(R.id.search_button)
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ticket);
        ButterKnife.bind(this);
    }
}
