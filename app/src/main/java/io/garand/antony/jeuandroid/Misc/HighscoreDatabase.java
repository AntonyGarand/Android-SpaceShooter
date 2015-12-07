package io.garand.antony.jeuandroid.Misc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Antony on 05/déc./2015.
 */
public class HighscoreDatabase extends SQLiteOpenHelper {

    static final String dbName = "highscore";
    static final String HS_Name = "highscore";
    static final String HS_ID = "id";
    static final String HS_Score = "score";

    SQLiteDatabase db;

    public HighscoreDatabase(Context context) {
        super(context,dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS " + HS_Name + "(" +
                    HS_ID + " INTEGER PRIMARY KEY , " +
                    HS_Score + " INTEGER" +
                    ")"
        );
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HS_Name);
        onCreate(db);
    }

    public int[] getScores(){
        if(db != null) {
            Cursor c = db.rawQuery("SELECT * FROM " + HS_Name + " ORDER BY " + HS_Score + " DESC", null);
            c.moveToFirst();
            int length = c.getCount() > 10 ? 10 : c.getCount();
            int[] values = new int[length];
            for (int i = 0; i < length; i++) {
                values[i] = c.getInt(1);
            }
            return values;
        }
        else
            return null;

    }

}
