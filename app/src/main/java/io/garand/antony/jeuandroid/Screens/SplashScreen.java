package io.garand.antony.jeuandroid.Screens;

/**
 * Created by Antony on 2015-11-27.
 */

import io.garand.antony.framework.Game;
import io.garand.antony.framework.Graphics;
import io.garand.antony.framework.Screen;
import io.garand.antony.framework.Graphics.ImageFormat;
import io.garand.antony.jeuandroid.Misc.Assets;


public class SplashScreen extends Screen {

    public SplashScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.splashScreen = g.newImage("Splash.png", ImageFormat.ARGB8888);
        game.setScreen(new LoadingScreen(game));
    }

    @Override
    public void paint(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }

}
