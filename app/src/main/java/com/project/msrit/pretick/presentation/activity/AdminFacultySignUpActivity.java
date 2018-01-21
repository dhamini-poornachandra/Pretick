package com.project.msrit.pretick.presentation.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.project.msrit.pretick.R;
import com.project.msrit.pretick.data.network.service.RestService;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AdminFacultySignUpActivity extends AppCompatActivity {

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

    @BindView(R.id.admin)
    RadioButton admin;

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public boolean isValidEmail(CharSequence target) {
            return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
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
                        phoneNumber.getText().toString().length() == 10 &&
                        isValidEmail(emailId.getText().toString())) {
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

            RestService rs = new RestService();
            rs.getSignUp(firstName.getText().toString(),
                    lastName.getText().toString(),
                    organisationName.getText().toString(),
                    emailId.getText().toString(),
                    password.getText().toString(),
                    admin.isChecked() ? "admin" : "staff",
                    phoneNumber.getText().toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Response<Void>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(retrofit2.Response<Void> response) {
                            if (response.code() == HttpsURLConnection.HTTP_CREATED) {
                                Snackbar.make(getCurrentFocus(), "Sign up success", Snackbar.LENGTH_LONG).show();
                            } else {
                                Snackbar.make(getCurrentFocus(), "Sign up failed", Snackbar.LENGTH_LONG).show();
                            }
                        }

                    });
        } else {
            Snackbar.make(getCurrentFocus(), "Passwords don't match", Snackbar.LENGTH_LONG).show();
        }

    }

    @BindView(R.id.confirm_password)
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_faculty_sign_up);
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
}
