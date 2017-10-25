package cn.ac.hfcas.sight.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.ac.hfcas.sight.R;
import cn.ac.hfcas.sight.activity.ResultActivity;
import cn.ac.hfcas.sight.db.SightDBControl;
import cn.ac.hfcas.sight.entity.UserLogin;
import cn.ac.hfcas.sight.tools.Define;
import cn.ac.hfcas.sight.tools.SharedTool;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "zzh";

    private ArrayList<ImageView> imageViews = new ArrayList<>();

    private static final int HORIZONTAL = 11;
    private static final int VERTICAL = 22;
    private static final int SLOPE_UP = 33;
    private static final int SLOPE_DOWN = 44;
    private static final int CLOCKWISE = 55;
    private static final int ANTICLOCKWISE = 66;

    private static final int FIRST_STEP = 1;
    private static final int SECOND_STEP = 2;
    private static final int THIRD_STEP = 3;

    private long startTime,endTime;

    private int horizontalAvgTime,verticalAvgTime,slopeUPAvgTime,
                slopeDownAvgTime,clockwiseAvgTime,anticlockwiseAvgTime;

    private int state,step;

    private ImageView img01,img02,img03,img04,img05,img06,img07,img08,
            img09,img10,img11,img12,img13,img14,img15,img16,
            img17,img18,img19,img20,img21,img22,img23,img24,img25;

    private Button startTest,result;

    private TextView prestart,remainTime,stateNotice;

    private SightDBControl dbControl;
    private UserLogin userLogin;
    private SharedTool sharedTool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.test_activity);

        init();
    }

    private void init() {
        img01 = (ImageView) findViewById(R.id.img01);
        img02 = (ImageView) findViewById(R.id.img02);
        img03 = (ImageView) findViewById(R.id.img03);
        img04 = (ImageView) findViewById(R.id.img04);
        img05 = (ImageView) findViewById(R.id.img05);
        img06 = (ImageView) findViewById(R.id.img06);
        img07 = (ImageView) findViewById(R.id.img07);
        img08 = (ImageView) findViewById(R.id.img08);
        img09 = (ImageView) findViewById(R.id.img09);
        img10 = (ImageView) findViewById(R.id.img10);
        img11 = (ImageView) findViewById(R.id.img11);
        img12 = (ImageView) findViewById(R.id.img12);
        img13 = (ImageView) findViewById(R.id.img13);
        img14 = (ImageView) findViewById(R.id.img14);
        img15 = (ImageView) findViewById(R.id.img15);
        img16 = (ImageView) findViewById(R.id.img16);
        img17 = (ImageView) findViewById(R.id.img17);
        img18 = (ImageView) findViewById(R.id.img18);
        img19 = (ImageView) findViewById(R.id.img19);
        img20 = (ImageView) findViewById(R.id.img20);
        img21 = (ImageView) findViewById(R.id.img21);
        img22 = (ImageView) findViewById(R.id.img22);
        img23 = (ImageView) findViewById(R.id.img23);
        img24 = (ImageView) findViewById(R.id.img24);
        img25 = (ImageView) findViewById(R.id.img25);

        imageViews.add(img01);
        imageViews.add(img02);
        imageViews.add(img03);
        imageViews.add(img04);
        imageViews.add(img05);
        imageViews.add(img06);
        imageViews.add(img07);
        imageViews.add(img08);
        imageViews.add(img09);
        imageViews.add(img10);
        imageViews.add(img11);
        imageViews.add(img12);
        imageViews.add(img13);
        imageViews.add(img14);
        imageViews.add(img15);
        imageViews.add(img16);
        imageViews.add(img17);
        imageViews.add(img18);
        imageViews.add(img19);
        imageViews.add(img20);
        imageViews.add(img21);
        imageViews.add(img22);
        imageViews.add(img23);
        imageViews.add(img24);
        imageViews.add(img25);

        for (int i=0;i<imageViews.size();i++){
            imageViews.get(i).setOnClickListener(this);
            imageViews.get(i).setVisibility(View.INVISIBLE);
        }

        startTest = (Button) findViewById(R.id.start_test);
        startTest.setOnClickListener(this);

        result = (Button) findViewById(R.id.result);
        result.setOnClickListener(this);

        prestart = (TextView) findViewById(R.id.prestart);
        remainTime = (TextView) findViewById(R.id.remain_time);
        stateNotice = (TextView) findViewById(R.id.state_notice);

        dbControl = new SightDBControl(this);
        sharedTool = new SharedTool(this);

        userLogin = new UserLogin();
        userLogin.setUserName(sharedTool.getShareString(Define.USERNAME,null));
        userLogin.setUserPwd(sharedTool.getShareString(Define.USERPWD,null));
    }

    private void getAvgTime(){
        horizontalAvgTime = horizontalAvgTime/3;
        Log.i(TAG,"水平平均用时"+horizontalAvgTime+"毫秒");
        verticalAvgTime = verticalAvgTime/3;
        Log.i(TAG,"垂直平均用时"+verticalAvgTime+"毫秒");
        slopeUPAvgTime = slopeDownAvgTime/3;
        Log.i(TAG,"斜向上平均用时"+slopeUPAvgTime+"毫秒");
        slopeDownAvgTime = slopeDownAvgTime/3;
        Log.i(TAG,"斜向下平均用时"+slopeDownAvgTime+"毫秒");
        clockwiseAvgTime = clockwiseAvgTime/3;
        Log.i(TAG,"顺时针平均用时"+clockwiseAvgTime+"毫秒");
        anticlockwiseAvgTime = anticlockwiseAvgTime/3;
        Log.i(TAG,"逆时针平均用时"+anticlockwiseAvgTime+"毫秒");
    }


    @Override
    public void onClick(View view) {
        int stepTime;
        switch (view.getId()){
            case R.id.start_test:
                startTest.setEnabled(false);
                prestart.setVisibility(View.VISIBLE);
                countDownTimer.start();
                break;
            case R.id.result:
                checkResult();
                finish();
                break;
            case R.id.img01:
                img01.setVisibility(View.INVISIBLE);
                if (state == SLOPE_DOWN){
                    startTime = System.currentTimeMillis();
                    img07.setVisibility(View.VISIBLE);
                    stateNotice.setText("正在进行第"+step+"轮左上右下方向检测...");
                } else if (state == CLOCKWISE){
                    img02.setVisibility(View.VISIBLE);
                } else if (state == ANTICLOCKWISE){
                    state = HORIZONTAL;
                    endTime = System.currentTimeMillis();
                    stepTime = (int)(endTime-startTime);
                    anticlockwiseAvgTime += stepTime;
                    if (step == FIRST_STEP){
                        step = SECOND_STEP;
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage(R.string.first_step);
                        alertDialog.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                img11.setVisibility(View.VISIBLE);
                                stateNotice.setText("正在进行第"+step+"轮检测...");
                            }
                        });
                        alertDialog.setCancelable(false);
                        alertDialog.show();
                    }else if (step == SECOND_STEP){
                        step = THIRD_STEP;
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage(R.string.second_step);
                        alertDialog.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                img11.setVisibility(View.VISIBLE);
                                stateNotice.setText("正在进行第"+step+"轮检测...");
                            }
                        });
                        alertDialog.setCancelable(false);
                        alertDialog.show();
                    } else if (step == THIRD_STEP){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage(R.string.third_step);
                        alertDialog.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                stateNotice.setVisibility(View.INVISIBLE);
                                result.setEnabled(true);
                            }
                        });
                        alertDialog.setCancelable(false);
                        alertDialog.show();
                        getAvgTime();
                    }
                }
                break;
            case R.id.img02:
                img02.setVisibility(View.INVISIBLE);
                if (state == CLOCKWISE){
                    img03.setVisibility(View.VISIBLE);
                } else if (state == ANTICLOCKWISE){
                    img01.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img03:
                img03.setVisibility(View.INVISIBLE);
                if (state == VERTICAL){
                    startTime = System.currentTimeMillis();
                    img08.setVisibility(View.VISIBLE);
                    stateNotice.setText("正在进行第"+step+"轮垂直方向检测...");
                } else if (state == CLOCKWISE){
                    img04.setVisibility(View.VISIBLE);
                } else if (state == ANTICLOCKWISE){
                    img02.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img04:
                img04.setVisibility(View.INVISIBLE);
                if (state == CLOCKWISE){
                    img05.setVisibility(View.VISIBLE);
                } else if (state == ANTICLOCKWISE){
                    img03.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img05:
                img05.setVisibility(View.INVISIBLE);
                if (state == SLOPE_UP){
                    state = SLOPE_DOWN;
                    endTime = System.currentTimeMillis();
                    stepTime = (int)(endTime-startTime);
                    slopeUPAvgTime += stepTime;
                    img01.setVisibility(View.VISIBLE);
                } else if (state == CLOCKWISE){
                    img15.setVisibility(View.VISIBLE);
                } else if (state == ANTICLOCKWISE){
                    img04.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img06:
                break;
            case R.id.img07:
                img07.setVisibility(View.INVISIBLE);
                img13.setVisibility(View.VISIBLE);
                break;
            case R.id.img08:
                img08.setVisibility(View.INVISIBLE);
                img13.setVisibility(View.VISIBLE);
                break;
            case R.id.img09:
                img09.setVisibility(View.INVISIBLE);
                img05.setVisibility(View.VISIBLE);
                break;
            case R.id.img10:
                break;
            case R.id.img11:
                img11.setVisibility(View.INVISIBLE);
                if (state == HORIZONTAL){
                    startTime = System.currentTimeMillis();
                    img12.setVisibility(View.VISIBLE);
                    stateNotice.setText("正在进行第"+step+"轮水平方向检测...");
                } else if (state == CLOCKWISE){
                    startTime = System.currentTimeMillis();
                    img01.setVisibility(View.VISIBLE);
                    stateNotice.setText("正在进行第"+step+"轮顺时针方向检测...");
                } else if (state == ANTICLOCKWISE){
                    startTime = System.currentTimeMillis();
                    img21.setVisibility(View.VISIBLE);
                    stateNotice.setText("正在进行第"+step+"轮逆时针方向检测...");
                }
                break;
            case R.id.img12:
                img12.setVisibility(View.INVISIBLE);
                img13.setVisibility(View.VISIBLE);
                break;
            case R.id.img13:
                img13.setVisibility(View.INVISIBLE);
                if (state == HORIZONTAL){
                    img14.setVisibility(View.VISIBLE);
                } else if (state == VERTICAL){
                    img18.setVisibility(View.VISIBLE);
                } else if (state == SLOPE_UP){
                    img09.setVisibility(View.VISIBLE);
                } else if (state == SLOPE_DOWN){
                    img19.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img14:
                img14.setVisibility(View.INVISIBLE);
                img15.setVisibility(View.VISIBLE);
                break;
            case R.id.img15:
                img15.setVisibility(View.INVISIBLE);
                if (state == HORIZONTAL){
                    endTime = System.currentTimeMillis();
                    stepTime = (int)(endTime-startTime);
                    horizontalAvgTime += stepTime;
                    state = VERTICAL;
                    img03.setVisibility(View.VISIBLE);
                } else if (state == CLOCKWISE){
                    img25.setVisibility(View.VISIBLE);
                } else if (state == ANTICLOCKWISE){
                    img05.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img16:
                break;
            case R.id.img17:
                img17.setVisibility(View.INVISIBLE);
                img13.setVisibility(View.VISIBLE);
                break;
            case R.id.img18:
                img18.setVisibility(View.INVISIBLE);
                img23.setVisibility(View.VISIBLE);
                break;
            case R.id.img19:
                img19.setVisibility(View.INVISIBLE);
                img25.setVisibility(View.VISIBLE);
                break;
            case R.id.img20:
                break;
            case R.id.img21:
                img21.setVisibility(View.INVISIBLE);
                if (state == SLOPE_UP){
                    startTime = System.currentTimeMillis();
                    img17.setVisibility(View.VISIBLE);
                    stateNotice.setText("正在进行第"+step+"轮左下右上方向检测...");
                } else if (state == CLOCKWISE){
                    state = ANTICLOCKWISE;
                    endTime = System.currentTimeMillis();
                    stepTime = (int)(endTime-startTime);
                    clockwiseAvgTime += stepTime;
                    img11.setVisibility(View.VISIBLE);
                } else if (state == ANTICLOCKWISE){
                    img22.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img22:
                img22.setVisibility(View.INVISIBLE);
                if (state == CLOCKWISE){
                    img21.setVisibility(View.VISIBLE);
                } else if (state == ANTICLOCKWISE){
                    img23.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img23:
                img23.setVisibility(View.INVISIBLE);
                if (state == VERTICAL){
                    state = SLOPE_UP;
                    endTime = System.currentTimeMillis();
                    stepTime = (int)(endTime-startTime);
                    verticalAvgTime += stepTime;
                    img21.setVisibility(View.VISIBLE);
                } else if (state == CLOCKWISE){
                    img22.setVisibility(View.VISIBLE);
                } else if (state == ANTICLOCKWISE){
                    img24.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img24:
                img24.setVisibility(View.INVISIBLE);
                if (state == CLOCKWISE){
                    img23.setVisibility(View.VISIBLE);
                } else if (state == ANTICLOCKWISE){
                    img25.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img25:
                img25.setVisibility(View.INVISIBLE);
                if (state == SLOPE_DOWN){
                    state = CLOCKWISE;
                    endTime = System.currentTimeMillis();
                    stepTime = (int)(endTime-startTime);
                    slopeDownAvgTime += stepTime;
                    img11.setVisibility(View.VISIBLE);
                } else if (state == CLOCKWISE){
                    img24.setVisibility(View.VISIBLE);
                } else if (state == ANTICLOCKWISE){
                    img15.setVisibility(View.VISIBLE);
                }
                break;
        }

    }

    private void checkResult() {
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("horizontalAvgTime",horizontalAvgTime);
        intent.putExtra("verticalAvgTime",verticalAvgTime);
        intent.putExtra("slopeUPAvgTime",slopeUPAvgTime);
        intent.putExtra("slopeDownAvgTime",slopeDownAvgTime);
        intent.putExtra("clockwiseAvgTime",clockwiseAvgTime);
        intent.putExtra("anticlockwiseAvgTime",anticlockwiseAvgTime);

        startActivity(intent);
    }

    private CountDownTimer countDownTimer = new CountDownTimer(5200,1000) {
        @Override
        public void onTick(long l) {
            remainTime.setText(l/1000+"");
        }

        @Override
        public void onFinish() {
            state = HORIZONTAL;
            step = FIRST_STEP;
            img11.setVisibility(View.VISIBLE);
            prestart.setVisibility(View.INVISIBLE);
            remainTime.setVisibility(View.INVISIBLE);
            stateNotice.setText("正在进行第"+step+"轮检测...");
        }
    };

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (userLogin.getUserName()!=null){
            dbControl.saveGrade(userLogin,horizontalAvgTime,verticalAvgTime,
                    slopeUPAvgTime,slopeDownAvgTime,clockwiseAvgTime,anticlockwiseAvgTime);
        }
    }
}
