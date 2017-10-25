package cn.ac.hfcas.sight.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.ac.hfcas.sight.R;
import cn.ac.hfcas.sight.control.UserLoginTask;
import cn.ac.hfcas.sight.db.SightDBControl;
import cn.ac.hfcas.sight.entity.UserLogin;
import cn.ac.hfcas.sight.tools.Define;
import cn.ac.hfcas.sight.tools.SharedTool;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener{

    // UI references.
    private AutoCompleteTextView userName;
    private EditText userPwd;
    private View mProgressView;
    private View mLoginFormView;
    private TextView register;
    private Button login;
    private SightDBControl dbControl;

    private SharedTool sharedTool;

    private String result;

    private UserLoginTask userLoginTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        userName = (AutoCompleteTextView) findViewById(R.id.username);
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(userName.getText())&&!TextUtils.isEmpty(userPwd.getText())){
                    login.setEnabled(true);
                } else {
                    login.setEnabled(false);
                }
            }
        });
        userPwd = (EditText) findViewById(R.id.password);
        userPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(userName.getText())&&!TextUtils.isEmpty(userPwd.getText())){
                    login.setEnabled(true);
                } else {
                    login.setEnabled(false);
                }
            }
        });
        mProgressView = findViewById(R.id.login_progress);
        mLoginFormView = findViewById(R.id.login_form);
        register = (TextView) findViewById(R.id.login_register);
        register.setOnClickListener(this);
        login = (Button) findViewById(R.id.email_sign_in_button);
        login.setOnClickListener(this);

        sharedTool = new SharedTool(this);

        dbControl = new SightDBControl(this);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
}
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.email_sign_in_button:
                login();
                break;
            case R.id.login_register:
                Intent register = new Intent(this,RegisterActivity.class);
                startActivity(register);
                finish();
                break;
        }
    }

    private void login() {
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(userName.getText().toString());
        userLogin.setUserPwd(userPwd.getText().toString());

        userLoginTask = new UserLoginTask(dbControl,this,userLogin,handler);
        userLoginTask.execute();
//        showProgress(true);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    result = (String) msg.obj;
                    if (result != null && result.equals(Define.LOGIN_SUCCESS)){
                        sharedTool.setShareString(Define.USERNAME,userName.getText().toString());
                        sharedTool.setShareString(Define.USERPWD,userPwd.getText().toString());
                        sharedTool.setShareBoolean(Define.IS_LOGIN,true);
                        Intent intent = new Intent(getApplicationContext(),UserActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}

