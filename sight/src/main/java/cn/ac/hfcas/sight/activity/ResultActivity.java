package cn.ac.hfcas.sight.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.ac.hfcas.sight.R;
import cn.ac.hfcas.sight.tools.Define;
import cn.ac.hfcas.sight.tools.TestResult;

public class ResultActivity extends AppCompatActivity {

    private TextView horizontal,vartical,slopeUp,slopeDown,clockwise,anticlockwise,report;

    private int horizontal_result,vertical_result,slope_up_result,
                slope_down_result,clockwise_result,anticlockwise_result;

    private TestResult testResult = new TestResult();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();
        getData();
        init();
        setDate();
    }

    private void init() {
        horizontal = (TextView) findViewById(R.id.horizontal_result);
        vartical = (TextView) findViewById(R.id.vertiacl_result);
        slopeUp = (TextView) findViewById(R.id.slope_up_result);
        slopeDown = (TextView) findViewById(R.id.slope_down_result);
        clockwise = (TextView) findViewById(R.id.clockwise_result);
        anticlockwise = (TextView) findViewById(R.id.anticlockwise_result);
        report = (TextView) findViewById(R.id.report);

    }

    private void getData() {
        Intent intent = getIntent();
        horizontal_result = intent.getIntExtra("horizontalAvgTime",0);
        vertical_result = intent.getIntExtra("verticalAvgTime",0);
        slope_up_result = intent.getIntExtra("slopeUPAvgTime",0);
        slope_down_result = intent.getIntExtra("slopeDownAvgTime",0);
        clockwise_result = intent.getIntExtra("clockwiseAvgTime",0);
        anticlockwise_result = intent.getIntExtra("anticlockwiseAvgTime",0);

    }

    private void setDate(){
        testResult = new TestResult(
                horizontal_result,vertical_result,slope_up_result,
                slope_down_result,clockwise_result,anticlockwise_result
        );
        horizontal.setText(testResult.getHorizontalLevel());
        vartical.setText(testResult.getVerticalLevel());
        slopeUp.setText(testResult.getSlopeUpLevel());
        slopeDown.setText(testResult.getSlopeDownLevel());
        clockwise.setText(testResult.getClockwiseLevel());
        anticlockwise.setText(testResult.getAnticlcokwiseLevel());
        report.setText(getReport());

    }

    public StringBuilder getReport(){
        StringBuilder report = new StringBuilder();
        if (testResult.getHorizontalLevel().equals(Define.A)){
            report.append("外直肌运动能力优秀，内直肌运动能力优秀。\n");
        } else if (testResult.getHorizontalLevel().equals(Define.B)){
            report.append("外直肌运动能力良好，内直肌运动能力良好。\n");
        } else if (testResult.getHorizontalLevel().equals(Define.C)){
            report.append("外直肌运动能力一般，内直肌运动能力一般。\n");
        } else if (testResult.getHorizontalLevel().equals(Define.D)){
            report.append("外直肌运动能力较差，内直肌运动能力较差。\n");
        }

        if (testResult.getVerticalLevel().equals(Define.A)){
            report.append("上直肌运动能力优秀，下直肌运动能力优秀。\n");
        } else if (testResult.getVerticalLevel().equals(Define.B)){
            report.append("上直肌运动能力良好，下直肌运动能力良好。\n");
        } else if (testResult.getVerticalLevel().equals(Define.C)){
            report.append("上直肌运动能力一般，下直肌运动能力一般。\n");
        } else if (testResult.getVerticalLevel().equals(Define.D)){
            report.append("上直肌运动能力较差，下直肌运动能力较差。\n");
        }

        if (testResult.getAvg().equals(Define.A)){
            report.append("上斜肌运动能力优秀，下斜肌运动能力优秀。");
        } else if (testResult.getAvg().equals(Define.B)){
            report.append("上斜肌运动能力良好，下斜肌运动能力良好。");
        } else if (testResult.getAvg().equals(Define.C)){
            report.append("上斜肌运动能力一般，下斜肌运动能力一般。");
        } else if (testResult.getAvg().equals(Define.D)){
            report.append("上斜肌运动能力较差，下斜肌运动能力较差。");
        }

        return report;
    }
}
