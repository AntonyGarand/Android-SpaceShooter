package io.garand.antony.jeuandroid.Misc;

/**
 * Created by Antony on 22/nov./2015.
 */
import io.garand.antony.framework.Image;
import io.garand.antony.framework.Music;
import io.garand.antony.framework.Sound;
import io.garand.antony.jeuandroid.GameObject.Player;
import io.garand.antony.jeuandroid.GameObject.PlayerController;
import io.garand.antony.jeuandroid.Screens.Level;

public class Assets {

    //Singleton class
    public static Assets data = new Assets();
    private Assets(){}
    /*
    * Main Menu / Loading Screen
    */

    public static final int screenWidth = 1280;
    public static final int screenHeight = 720;

    public static Image splashScreen;

    public Level currentLevel;

    public PlayerController player;

}