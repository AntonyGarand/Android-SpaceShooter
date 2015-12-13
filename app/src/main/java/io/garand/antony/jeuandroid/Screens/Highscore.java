package io.garand.antony.jeuandroid.Screens;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import io.garand.antony.framework.Game;
import io.garand.antony.framework.Graphics;
import io.garand.antony.framework.Image;
import io.garand.antony.framework.Screen;
import io.garand.antony.framework.implementation.AndroidGraphics;
import io.garand.antony.jeuandroid.GameObject.Background;
import io.garand.antony.jeuandroid.Misc.Assets;
import io.garand.antony.jeuandroid.Misc.HighscoreDatabase;

/**
 * Created by Antony on 05/déc./2015.
 */
public class Highscore extends Screen {

    Image background;
    Background cloud;
    int[] scores;
    Paint displayPaint;

    public Highscore(Game game) {
        super(game);

        scores = Assets.data.highscoreDB.getScores();
        Log.d("Highscore", "Got " + scores.length + " scores! First is " + scores[0] + " and last is " + scores[scores.length - 1]);
        cloud = new Background(game.getGraphics().newImage("BackgroundSmoke.png", Graphics.ImageFormat.ARGB4444));
        background = game.getGraphics().newImage("BackgroundSky.png", Graphics.ImageFormat.RGB565);

        displayPaint = new Paint();
        displayPaint.setTextSize(50);
        displayPaint.setTextAlign(Paint.Align.LEFT);
        displayPaint.setAntiAlias(true);
        displayPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC));

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

        for (int i = 0; i < scores.length; i++) {
            displayPaint.setColor( Color.rgb(20 * i, 255 - 20 * i, 0));
            g.drawString(i + ": " + scores[i], 620, 65 * i + 75, displayPaint);
        }
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

        Graphics g = game.getGraphics();
        game.setScreen(new MainMenu(game, g.newImage("buttonPlay.png", Graphics.ImageFormat.ARGB4444),g.newImage("buttonHighscore.png", Graphics.ImageFormat.ARGB4444)));
    }
}
