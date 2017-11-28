package com.project.msrit.pretick.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.project.msrit.pretick.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.organisation_name)
    EditText organisationName;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.email_id)
    EditText emailId;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.sign_up_button)
    Button signUpButton;

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
                if (!firstName.getText().toString().equals("") &&
                        !lastName.getText().toString().equals("") &&
                        !organisationName.getText().toString().equals("") &&
                        !phoneNumber.getText().toString().equals("") &&
                        !emailId.getText().toString().equals("") &&
                        !password.getText().toString().equals("") &&
                        !confirmPassword.getText().toString().equals("") &&
                        phoneNumber.getText().toString().length() == 10) {
                    signUpButton.setEnabled(true);
                    signUpButton.setAlpha(1.0f);
                } else {
                    signUpButton.setEnabled(false);
                    signUpButton.setAlpha(0.7f);
                }
            }
        }
    };

    private void validateFields() {
        if (password.getText().toString().equals(confirmPassword.getText().toString())) {
            //send sign up request
        } else {
            Snackbar snackbar = Snackbar
                    .make(getCurrentFocus(), "Passwords don't match", Snackbar.LENGTH_LONG);

            snackbar.show();
        }

    }

    @BindView(R.id.confirm_password)
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        firstName.addTextChangedListener(watcher);
        lastName.addTextChangedListener(watcher);
        organisationName.addTextChangedListener(watcher);
        phoneNumber.addTextChangedListener(watcher);
        emailId.addTextChangedListener(watcher);
        password.addTextChangedListener(watcher);
        confirmPassword.addTextChangedListener(watcher);
    }

    @OnClick(R.id.sign_up_button)
    public void signUp() {
        validateFields();
    }

    @OnClick(R.id.link_login)
    public void login() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
