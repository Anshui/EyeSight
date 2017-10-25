package cn.ac.hfcas.sight.application;

import android.app.Application;

import cn.ac.hfcas.sight.tools.Define;
import cn.ac.hfcas.sight.tools.SharedTool;

/**
 * Created by Static on 2017/09/25.
 *
 */

public class SightApplication extends Application {

    private boolean isLogin;
    private SharedTool sharedTool;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {

        sharedTool = new SharedTool(getApplicationContext());

        isLogin = sharedTool.getShareBoolean(Define.IS_LOGIN,false);

    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
