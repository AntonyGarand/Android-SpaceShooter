package io.garand.antony.jeuandroid.Screens;

import io.garand.antony.framework.Game;
import io.garand.antony.framework.Graphics;
import io.garand.antony.framework.Image;
import io.garand.antony.framework.Screen;
import io.garand.antony.framework.implementation.AndroidGraphics;
import io.garand.antony.jeuandroid.GameObject.Background;
import io.garand.antony.jeuandroid.Misc.HighscoreDatabase;

/**
 * Created by Antony on 05/déc./2015.
 */
public class Highscore extends Screen {

    Image background;
    Background cloud;
    int[] scores;

    public Highscore(Game game) {
        super(game);
        HighscoreDatabase db = new HighscoreDatabase(null);
        scores = db.getScores();
        cloud = new Background(game.getGraphics().newImage("BackgroundSmoke.png", Graphics.ImageFormat.ARGB4444));
        background = game.getGraphics().newImage("BackgroundSky.png", Graphics.ImageFormat.RGB565);

        db.close();
    }

    @Override
    public void update(float deltaTime) {
        cloud.update(deltaTime);
    }

    @Override
    public void paint(float deltaTime) {

        Graphics g = game.getGraphics();

        g.drawImage(background, 0, 0);
        //Draw both "smoke" effects for a never ending scrolling effect
        g.drawImage(cloud.sprite, (int)cloud.getPosition().x, (int)cloud.getPosition().y);
        g.drawImage(cloud.sprite, (int)cloud.getPosition().x,(int) cloud.getPosition().y - cloud.sprite.getHeight());
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
