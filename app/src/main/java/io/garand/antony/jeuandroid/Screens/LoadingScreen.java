package io.garand.antony.jeuandroid.Screens;

/**
 * Created by Antony on 2015-11-27.
 */

import android.util.Log;

import io.garand.antony.framework.Game;
import io.garand.antony.framework.Graphics;
import io.garand.antony.framework.Screen;
import io.garand.antony.framework.Graphics.ImageFormat;
import io.garand.antony.jeuandroid.Misc.Assets;


public class LoadingScreen extends Screen {

    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        game.setScreen(new MainMenu(game, g.newImage("buttonPlay.png", ImageFormat.ARGB4444),g.newImage("buttonHighscore.png", ImageFormat.ARGB4444)));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.splashScreen, 0,0);
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
