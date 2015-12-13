package io.garand.antony.jeuandroid.Misc;

/**
 * Created by Antony on 22/nov./2015.
 */
import io.garand.antony.framework.Image;
import io.garand.antony.framework.Music;
import io.garand.antony.framework.Sound;
import io.garand.antony.jeuandroid.GameObject.BulletController;
import io.garand.antony.jeuandroid.GameObject.Player;
import io.garand.antony.jeuandroid.GameObject.PlayerController;
import io.garand.antony.jeuandroid.Screens.Level;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

public class Assets {

    //Singleton class
    public static Assets data = new Assets();
    private Assets(){

    }
    public static void startAssets(Context ctx){

        data.highscoreDB = new HighscoreDatabase(ctx);

    }
    /*
    * Main Menu / Loading Screen
    */

    public static final int screenWidth = 1280;
    public static final int screenHeight = 720;

    public static Image splashScreen;

    public Level currentLevel;

    public PlayerController player;

    public BulletController bulletController;

    public HighscoreDatabase highscoreDB;


}