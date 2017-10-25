package cn.ac.hfcas.sight.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import cn.ac.hfcas.sight.entity.Result;
import cn.ac.hfcas.sight.entity.UserInfo;
import cn.ac.hfcas.sight.entity.UserLogin;
import cn.ac.hfcas.sight.tools.Define;

/**
 * Created by Static on 2017/09/25.
 *
 */

public class SightDBControl {

    private Context context;
    private SightDBHelper dbHelper;

    public SightDBControl(Context context){
        this.context = context;
        dbHelper = new SightDBHelper(context);
    }

    public Boolean register(UserInfo userInfo){

        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(userInfo.getUserName());
        userLogin.setUserPwd(userInfo.getUserPwd());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        if (!hasUser(userLogin)){
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("userName", userInfo.getUserName());
                contentValues.put("userPwd", userInfo.getUserPwd());
                contentValues.put("userPhone", userInfo.getUserPhone());
                contentValues.put("eyeSight", userInfo.getEyeSight());
                contentValues.put("sex", userInfo.getSex());
                contentValues.put("userAge", userInfo.getUserAge());
                contentValues.put("height", userInfo.getHeight());
                contentValues.put("weight", userInfo.getWeight());
                contentValues.put("visionProblem", userInfo.getVisionProblem());

                db.insertOrThrow(SightDBHelper.TABLE_NAME,null,contentValues);
                db.setTransactionSuccessful();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                db.endTransaction();
                db.close();
            }
            return true;
        } else {
            Toast.makeText(context,"用户已存在",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void saveGrade(UserLogin userLogin,int horizontal,int vertical,int slopeUp,
                          int slopeDown,int clockwise,int anticlockwise){
        String[] where = {userLogin.getUserName()};

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();

        if (hasUser(userLogin)){

            ContentValues values = new ContentValues();
            values.put("horizontal",horizontal);
            values.put("vertical",vertical);
            values.put("slopeUp",slopeUp);
            values.put("slopeDown",slopeDown);
            values.put("clockwise",clockwise);
            values.put("anticlockwise",anticlockwise);

            db.update(SightDBHelper.TABLE_NAME,values,"userName=?",where);
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
        }
    }

    public boolean hasUser(UserLogin userLogin){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(SightDBHelper.TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            String userName = userLogin.getUserName();
            while(!cursor.isAfterLast()){
                String name = cursor.getString(cursor.getColumnIndex("userName"));
                cursor.moveToNext();
                if (name.equals(userName)){
                    cursor.close();
                    return true;
                }
            }
        }
        cursor.close();
        return false;
    }

    public String login(UserLogin userLogin){
        if (hasUser(userLogin)){
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(SightDBHelper.TABLE_NAME,null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                String userPwd = userLogin.getUserPwd();
                while (!cursor.isAfterLast()){
                    if (userPwd.equals(cursor.getString(cursor.getColumnIndex("userPwd")))){
                        cursor.close();
                        db.close();
                        return Define.LOGIN_SUCCESS;
                    }
                    cursor.moveToNext();
                }
                cursor.close();
                db.close();
                return Define.ERROR_PWD;
            }else{
                cursor.close();
                db.close();
                return Define.NO_DADA;
            }
        }
        return Define.NO_USER;
    }

    public UserInfo query(UserLogin userLogin){
        UserInfo info = new UserInfo();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from User where userName=?",new String[]{userLogin.getUserName()});
        if (cursor.moveToFirst()){
            Result result = new Result();
            info.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            info.setUserPwd(cursor.getString(cursor.getColumnIndex("userPwd")));
            info.setUserPhone(cursor.getString(cursor.getColumnIndex("userPhone")));
            info.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            info.setWeight(cursor.getDouble(cursor.getColumnIndex("weight")));
            info.setHeight(cursor.getInt(cursor.getColumnIndex("height")));
            info.setVisionProblem(cursor.getString(cursor.getColumnIndex("visionProblem")));
            info.setEyeSight(cursor.getDouble(cursor.getColumnIndex("eyeSight")));
            info.setUserAge(cursor.getInt(cursor.getColumnIndex("userAge")));
            result.setHorizontal(cursor.getInt(cursor.getColumnIndex("horizontal")));
            result.setVertical(cursor.getInt(cursor.getColumnIndex("vertical")));
            result.setSlopeUp(cursor.getInt(cursor.getColumnIndex("slopeUp")));
            result.setSlopeDown(cursor.getInt(cursor.getColumnIndex("slopeDown")));
            result.setClockwise(cursor.getInt(cursor.getColumnIndex("clockwise")));
            result.setAnticlockwise(cursor.getInt(cursor.getColumnIndex("anticlockwise")));
            info.setResult(result);
        }
        cursor.close();
        db.close();
        return info;
    }

}
