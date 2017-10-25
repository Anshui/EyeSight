package cn.ac.hfcas.sight.control;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import cn.ac.hfcas.sight.activity.UserActivity;
import cn.ac.hfcas.sight.db.SightDBControl;
import cn.ac.hfcas.sight.entity.UserLogin;
import cn.ac.hfcas.sight.tools.Define;

/**
 * Created by Static on 2017/09/25.
 *
 */

public class UserLoginTask extends AsyncTask<UserLogin,Void,String>{

    private SightDBControl sightDBControl;
    private Context context;
    private UserLogin userLogin;
    private Handler handler;

    public static final int LOGIN_SUCCESS = 200;

    public UserLoginTask() {
    }

    public UserLoginTask(SightDBControl sightDBControl, Context context, UserLogin userLogin,Handler handler) {
        this.sightDBControl = sightDBControl;
        this.context = context;
        this.userLogin = userLogin;
        this.handler = handler;
    }

    @Override
    protected String doInBackground(UserLogin... userLogins) {

        String result = sightDBControl.login(userLogin);
        Message msg = new Message();
        msg.what = LOGIN_SUCCESS;
        msg.obj = result;
        handler.sendMessage(msg);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }
}
