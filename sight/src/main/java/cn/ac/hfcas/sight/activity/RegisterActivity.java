package cn.ac.hfcas.sight.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;

import cn.ac.hfcas.sight.R;
import cn.ac.hfcas.sight.db.SightDBControl;
import cn.ac.hfcas.sight.entity.UserInfo;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText username,password,phone;
    private Button age,height,weight,sight,visionProblem,register;
    private RadioButton male,female;
    private SightDBControl control;

    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        username = (EditText) findViewById(R.id.username);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(password.getText())){
                    register.setEnabled(true);
                } else {
                    register.setEnabled(false);
                }
            }
        });
        password = (EditText) findViewById(R.id.password);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(password.getText())){
                    register.setEnabled(true);
                } else {
                    register.setEnabled(false);
                }
            }
        });
        phone = (EditText) findViewById(R.id.phone);
        age = (Button)findViewById(R.id.age);
        age.setOnClickListener(this);
        height = (Button)findViewById(R.id.height);
        height.setOnClickListener(this);
        weight = (Button)findViewById(R.id.weight);
        weight.setOnClickListener(this);
        sight = (Button)findViewById(R.id.sight);
        sight.setOnClickListener(this);
        visionProblem = (Button)findViewById(R.id.vision_problem);
        visionProblem.setOnClickListener(this);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
        male = (RadioButton) findViewById(R.id.male);
        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    userInfo.setSex(male.getText().toString());
                }
            }
        });
        female = (RadioButton) findViewById(R.id.female);
        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    userInfo.setSex(female.getText().toString());
                }
            }
        });

        control = new SightDBControl(this);
        userInfo = new UserInfo();
    }

    private boolean getEnable() {
        if(!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(password.getText())){
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.age:
                showAgeDialog();
                break;
            case R.id.weight:
                showWeightDialog();
                break;
            case R.id.height:
                showHeightDialog();
                break;
            case R.id.sight:
                showSightDialog();
                break;
            case R.id.vision_problem:
                showVisionDialog();
                break;
            case R.id.register:
                registerUser();
                break;
            default:
                break;
        }
    }

    private void registerUser() {
        if (control.register(getUserInfo())){
            Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
        }

    }

    private UserInfo getUserInfo() {
        userInfo.setUserName(username.getText().toString());
        userInfo.setUserPwd(password.getText().toString());
        userInfo.setUserPhone(phone.getText().toString());
        if (!TextUtils.isEmpty(age.getText())) {
            userInfo.setUserAge(Integer.parseInt(age.getText().toString()));
        }
        if (!TextUtils.isEmpty(height.getText())) {
            userInfo.setHeight(Integer.parseInt(height.getText().toString()));
        }
        if (!TextUtils.isEmpty(weight.getText())) {
            userInfo.setWeight(Double.parseDouble(weight.getText().toString()));
        }
        if (!TextUtils.isEmpty(sight.getText())) {
            userInfo.setEyeSight(Double.parseDouble(sight.getText().toString()));
        }
        userInfo.setVisionProblem(visionProblem.getText().toString());

        return userInfo;
    }

    private void showVisionDialog() {
        final String[] vision = {"正常","近视","远视"};
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this,R.layout.list_adapter,R.id.text,vision
        );


        final AlertDialog.Builder visionDialog = new AlertDialog.Builder(this);
        visionDialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                visionProblem.setText(vision[which]);
            }
        });
        visionDialog.show();
    }

    private void showSightDialog() {
        final Double[] eyeSight = {0.1,0.12,0.15,0.2,0.3,0.4,0.5,0.6,0.8,1.0,1.2,1.5,2.0};
        ArrayAdapter adapter = new ArrayAdapter<Double>(
                this,R.layout.list_adapter,R.id.text,eyeSight
        );

        final AlertDialog.Builder sightDialog = new AlertDialog.Builder(this);
        sightDialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sight.setText(eyeSight[which]+"");
            }
        });
        sightDialog.show();
    }

    private void showHeightDialog() {
        final NumberPicker heightPicker = new NumberPicker(this);
        heightPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        heightPicker.setMaxValue(200);
        heightPicker.setMinValue(20);
        heightPicker.setValue(140);
        if (!TextUtils.isEmpty(height.getText())){
            heightPicker.setValue(Integer.parseInt(height.getText().toString()));
        }

        final AlertDialog.Builder heightDialog = new AlertDialog.Builder(this);
        heightDialog.setView(heightPicker);
        heightDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                height.setText(heightPicker.getValue()+"");
            }
        });
        heightDialog.show();
    }

    private void showWeightDialog() {
        final NumberPicker weightPicker = new NumberPicker(this);
        weightPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        weightPicker.setMaxValue(100);
        weightPicker.setMinValue(20);
        weightPicker.setValue(50);
        if (!TextUtils.isEmpty(weight.getText())){
            weightPicker.setValue(Integer.parseInt(weight.getText().toString()));
        }

        final AlertDialog.Builder weightDialog = new AlertDialog.Builder(this);
        weightDialog.setView(weightPicker);
        weightDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                weight.setText(weightPicker.getValue()+"");
            }
        });
        weightDialog.show();
    }

    private void showAgeDialog() {
        final NumberPicker agePicker = new NumberPicker(this);
        agePicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        agePicker.setMaxValue(30);
        agePicker.setMinValue(3);
        agePicker.setValue(18);
        if (!TextUtils.isEmpty(age.getText())){
            agePicker.setValue(Integer.parseInt(age.getText().toString()));
        }

        final AlertDialog.Builder ageDialog = new AlertDialog.Builder(this);
        ageDialog.setView(agePicker);
        ageDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                age.setText(agePicker.getValue()+"");
            }
        });
        ageDialog.show();
    }
}
