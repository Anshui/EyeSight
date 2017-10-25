package cn.ac.hfcas.sight.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import cn.ac.hfcas.sight.R;
import cn.ac.hfcas.sight.application.SightApplication;
import cn.ac.hfcas.sight.tools.Define;
import cn.ac.hfcas.sight.tools.SharedTool;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private SightApplication sightApplication;

    private FloatingActionButton fab,user,test;
    private boolean fabOpened;
    private SightApplication sight;
    private SharedTool sharedTool;

    private static final int MENU_INVISIBLE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        user = (FloatingActionButton) findViewById(R.id.user);
        user.setOnClickListener(this);
        test = (FloatingActionButton) findViewById(R.id.test);
        test.setOnClickListener(this);
        sharedTool = new SharedTool(this);
        sight = (SightApplication) getApplication();

        fabOpened = false;
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                if (!fabOpened){
                    openMenu(view);
                } else {
                    closeMenu();
                }
                break;
            case R.id.user:
                if (sharedTool.getShareBoolean(Define.IS_LOGIN,false)){
                    Intent user = new Intent(this,UserActivity.class);
                    startActivity(user);
                } else {
                    Intent login = new Intent(this,LoginActivity.class);
                    startActivity(login);
                }
                closeMenu();
                break;
            case R.id.test:
                if (sharedTool.getShareBoolean(Define.IS_LOGIN,false)){
                    Intent test = new Intent(this,TestActivity.class);
                    startActivity(test);
                } else {
                    showNotice();
                }
                closeMenu();
                break;
            default:
                break;
        }

    }

    private void showNotice() {
        final AlertDialog.Builder notice = new AlertDialog.Builder(this);
        notice.setTitle(R.string.unlogin);
        notice.setMessage(R.string.login_notice);
        notice.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent login = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(login);
            }
        });
        notice.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent test = new Intent(getApplicationContext(),TestActivity.class);
                startActivity(test);
            }
        });
        notice.show();
    }

    private void closeMenu() {
        ObjectAnimator fabAnimator = ObjectAnimator.ofFloat(fab,"rotation",-135,20,-20,0);
        ObjectAnimator userAnimator = ObjectAnimator.ofFloat(user,"translationX",-200,-220,10,0);
        final ObjectAnimator testAnimator = ObjectAnimator.ofFloat(test,"translationX",-400,-420,10,0);
        fabAnimator.setDuration(500);
        userAnimator.setDuration(500);
        testAnimator.setDuration(500);
        testAnimator.start();
        fabAnimator.start();
        userAnimator.start();
        fabOpened = false;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MENU_INVISIBLE);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,500);

    }

    private void openMenu(View view) {
        user.setVisibility(View.VISIBLE);
        test.setVisibility(View.VISIBLE);
        ObjectAnimator fabAnimator = ObjectAnimator.ofFloat(fab,"rotation",0,-155,-115,-135);
        ObjectAnimator userAnimator = ObjectAnimator.ofFloat(user,"translationX",0,-220,-180,-200);
        ObjectAnimator testAnimator = ObjectAnimator.ofFloat(test,"translationX",0,-420,-380,-400);
        fabAnimator.setDuration(500);
        userAnimator.setDuration(500);
        testAnimator.setDuration(500);
        fabAnimator.start();
        userAnimator.start();
        testAnimator.start();
        fabOpened = true;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MENU_INVISIBLE:
                    if (!fabOpened){
                        user.setVisibility(View.INVISIBLE);
                        test.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }
    };


}
