package cn.ac.hfcas.sight.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Static on 2017/09/25.
 *
 */

public class SightDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "sight.db";
    public static final String TABLE_NAME = "User";

    public SightDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql =
                "create table if not exists "+TABLE_NAME+
                        " (_id integer primary key," +
                        " userName text," +
                        " userPwd text," +
                        " userPhone text," +
                        " eyeSight real," +
                        " sex text," +
                        " userAge integer," +
                        " height integer," +
                        " weight real," +
                        " visionProblem text," +
                        " horizontal integer," +
                        " vertical integer," +
                        " slopeUp integer," +
                        " slopeDown integer," +
                        " clockwise integer," +
                        " anticlockwise integer);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
