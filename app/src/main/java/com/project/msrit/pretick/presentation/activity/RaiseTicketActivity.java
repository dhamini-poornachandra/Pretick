package com.project.msrit.pretick.presentation.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.project.msrit.pretick.R;
import com.project.msrit.pretick.data.network.model.GlobalVariable;
import com.project.msrit.pretick.data.network.service.RestService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RaiseTicketActivity extends AppCompatActivity {

    Boolean isStartTime;

    @BindView(R.id.date)
    TextView dateText;

    @BindView(R.id.start_time_text)
    TextView startTimeText;

    @BindView(R.id.end_time_text)
    TextView endTimeText;

    @BindView(R.id.inviter_spinner)
    Spinner inviterSpinner;

    @BindView(R.id.organisation_name)
    EditText orgName;

    @BindView(R.id.two_wheeler)
    RadioButton twoWheeler;

    @BindView(R.id.four_wheeler)
    RadioButton fourWheeler;

    Calendar myCalendar = Calendar.getInstance();
    HashMap<String, String> contactPersonHash = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_ticket);
        ButterKnife.bind(this);


        List<String> list = new ArrayList<String>();

        for (int i = 0; i < GlobalVariable.getInstance().getContactPersons().size(); i++) {
            list.add(GlobalVariable.getInstance().getContactPersons().get(i).getName());
            contactPersonHash.put(GlobalVariable.getInstance().getContactPersons().get(i).getName(),
                    GlobalVariable.getInstance().getContactPersons().get(i).getUserid());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        inviterSpinner.setAdapter(dataAdapter);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateText.setText(sdf.format(myCalendar.getTime()));
    }

    @OnClick(R.id.set_date)
    public void setDate() {
        DatePickerDialog datePicker = new DatePickerDialog(RaiseTicketActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePicker.show();
    }

    @OnClick(R.id.start_time)
    public void setStartTime() {
        isStartTime = true;
        displayTimePopOver();
    }

    private void displayTimePopOver() {

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(RaiseTicketActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if (isStartTime) {
                    if (selectedHour < 8) {
                        Snackbar.make(getCurrentFocus(), "Parking opens at 8AM", Snackbar.LENGTH_LONG).show();
                    } else {
                        startTimeText.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                    }
                } else {
                    if (selectedHour >= 20) {
                        Snackbar.make(getCurrentFocus(), "Parking closes at 8PM", Snackbar.LENGTH_LONG).show();
                    } else {
                        endTimeText.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                    }
                }
            }
        }, 0, 0, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    @OnClick(R.id.end_time)
    public void setEndTime() {
        isStartTime = false;
        displayTimePopOver();
    }

    @OnClick(R.id.create_ticket)
    public void raiseTicket() {

        if (validate()) {
            SharedPreferences sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

            RestService rs = new RestService();
            rs.postRaiseTicket(dateText.getText().toString(),
                    startTimeText.getText().toString(),
                    endTimeText.getText().toString(),
                    contactPersonHash.get(inviterSpinner.getSelectedItem().toString()),
                    twoWheeler.isChecked() ? "twowheeler" : "fourwheeler",
                    orgName.getText().toString(),
                    sharedPreferences.getString("username", "9739626595"))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Response<Void>>() {
                        @Override
                        public void onCompleted() {
                            Toast.makeText(RaiseTicketActivity.this, "Raise ticket success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(RaiseTicketActivity.this, "Raise ticket failed", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNext(retrofit2.Response<Void> response) {
                            if (response.code() == HttpsURLConnection.HTTP_CREATED) {
                                Toast.makeText(RaiseTicketActivity.this, "Raise ticket success", Toast.LENGTH_LONG).show();
                                if (getIntent().getBooleanExtra("faculty", false)) {
                                    startActivity(new Intent(RaiseTicketActivity.this, FacultyDashBoardActivity.class));
                                } else {
                                    startActivity(new Intent(RaiseTicketActivity.this, GuestDashboardActivity.class));
                                }

                            } else {
                                Toast.makeText(RaiseTicketActivity.this, "Raise ticket failed", Toast.LENGTH_LONG).show();
                            }
                        }

                    });
        }

    }

    private Boolean validate() {
        if (!dateText.getText().toString().equals("Date: ") && !startTimeText.getText().toString().equals("Start Time: ") &&
                !endTimeText.getText().toString().equals("End Time: ") && !orgName.getText().toString().isEmpty()) {
            if (endTimeText.getText().toString().compareTo(startTimeText.getText().toString()) < 0) {
                Toast.makeText(this, "End time should be greater than start time", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            //please enter all fields
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
