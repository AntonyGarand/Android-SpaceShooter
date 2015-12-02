package io.garand.antony.jeuandroid.Screens;

import io.garand.antony.framework.Screen;
import io.garand.antony.framework.implementation.AndroidGame;


public class GameStart extends AndroidGame {
    @Override
    public Screen getInitScreen() {

        return new SplashScreen(this);
    }

}