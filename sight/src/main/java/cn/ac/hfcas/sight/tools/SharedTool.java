package cn.ac.hfcas.sight.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Static on 2017/09/26.
 *
 */

public class SharedTool {

    private SharedPreferences sharedPreferences;
    private final String SHARE_KEY = "sightshare";

    public SharedTool(Context context) {
        this.sharedPreferences = context.getSharedPreferences(
                SHARE_KEY,context.MODE_APPEND);
    }
    public boolean getShareBoolean(String key, boolean b) {
        return sharedPreferences.getBoolean(key, b);
    }

    public int getShareInt(String key, int i) {
        return sharedPreferences.getInt(key, i);
    }

    public String getShareString(String key, String s) {
        return sharedPreferences.getString(key, s);
    }

    public void setShareBoolean(String key, boolean value) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setShareInt(String key, int value) {
        Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void setShareString(String key, String value) {
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void setShareFloat(String key, float value){
        Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public float getShareFloat(String key, float value){
        return sharedPreferences.getFloat(key, value);
    }
}
