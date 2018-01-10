package com.project.msrit.pretick.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.project.msrit.pretick.R;
import com.project.msrit.pretick.data.network.model.SampleResponseModel;
import com.project.msrit.pretick.data.network.service.RestService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dhamini-poorna-chandra on 27/11/2017.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_phone_no)
    EditText phoneNumber;
    @BindView(R.id.input_password)
    EditText password;
    @BindView(R.id.login_button)
    Button loginButton;

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (getCurrentFocus() != null) {
                if (!phoneNumber.getText().toString().equals("") && !password.getText().toString().equals("")
                        && phoneNumber.getText().toString().length() == 10) {
                    loginButton.setEnabled(true);
                    loginButton.setAlpha(1.0f);
                } else {
                    loginButton.setEnabled(false);
                    loginButton.setAlpha(0.7f);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        phoneNumber.addTextChangedListener(watcher);
        password.addTextChangedListener(watcher);

        login();
    }

    private void login() {
        RestService rs = new RestService();
        rs.getCharacters()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SampleResponseModel>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("Demo", e.getMessage());
                    }

                    @Override
                    public void onNext(SampleResponseModel sampleResponseModel) {
                        Log.d("Got response", "sai ram");
                    }

                });
    }

    @OnClick(R.id.link_signup)
    public void signUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
