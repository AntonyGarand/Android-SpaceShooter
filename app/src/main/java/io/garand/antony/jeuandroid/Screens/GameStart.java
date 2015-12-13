package io.garand.antony.jeuandroid.Screens;

import android.content.Context;
import android.util.Log;

import io.garand.antony.framework.Screen;
import io.garand.antony.framework.implementation.AndroidGame;
import io.garand.antony.jeuandroid.Misc.Assets;


public class GameStart extends AndroidGame {
    @Override
    public Screen getInitScreen() {

        Context ctx = this;
        Assets.startAssets(ctx);
        return new SplashScreen(this);
    }

}