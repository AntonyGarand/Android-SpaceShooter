package io.garand.antony.jeuandroid.Misc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Antony on 05/déc./2015.
 */
public class HighscoreDatabase extends SQLiteOpenHelper {

    static final String dbName = "highscore";
    static final String HS_Name = "highscore";
    static final String HS_ID = "id";
    static final String HS_Score = "score";


    public HighscoreDatabase(Context context) {
        super(context, dbName, null, 1);
        Log.d("Database", "Started DB");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "Created db");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + HS_Name + " ( " +
                        HS_ID + " INTEGER PRIMARY KEY , " +
                        HS_Score + " INTEGER" +
                        " );"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HS_Name);
        onCreate(db);
    }

    public int[] getScores(){
        SQLiteDatabase db = getReadableDatabase();
        if(db != null) {
            Cursor c = db.query(HS_Name, new String[]{HS_Score}, null, null, null, null, HS_Score + " DESC");
            //Cursor c = db.rawQuery("SELECT * FROM " + HS_Name + " ORDER BY " + HS_Score + " DESC;", null);
            //Log.d("Database", "Get query: " + "SELECT * FROM " + HS_Name + " ORDER BY " + HS_Score + " DESC;");
            c.moveToFirst();
            int length = c.getCount() > 10 ? 10 : c.getCount();
            int index = c.getColumnIndex(HS_Score);
            int[] values = new int[length];
            for (int i = 0; i < length; i++) {
                values[i] = c.getInt(index);
                c.moveToNext();
            }
            return values;
        }
        else {
            return null;
        }

    }

    public void addScore(int score) {

        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO " + HS_Name + "( " + HS_Score + " ) VALUES ( " + score + " );");
        }
        db.close();
    }

    public void resetDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + HS_Name);
        onCreate(db);
    }
}
