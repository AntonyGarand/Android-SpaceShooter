package io.garand.antony.jeuandroid.Screens;

/**
 * Created by Antony on 2015-11-27.
 */

import android.util.Log;

import java.util.List;

import io.garand.antony.framework.Game;
import io.garand.antony.framework.Graphics;
import io.garand.antony.framework.Image;
import io.garand.antony.framework.Input;
import io.garand.antony.framework.Screen;
import io.garand.antony.jeuandroid.Misc.Assets;
import io.garand.antony.jeuandroid.Misc.Functions;
import io.garand.antony.jeuandroid.Misc.Vector2;
import io.garand.antony.jeuandroid.UI.Button;

public class MainMenu extends Screen {

    public MainMenu(Game game) {
        super(game);
        Log.d("Menu", "Main menu loaded");
    }
    private Button playButton, highscoreButton;


    public MainMenu(Game game, Image _play, Image _highscore) {
        super(game);
        playButton = new Button(_play, new Vector2(907, 429),new Vector2(219, 113));
        highscoreButton = new Button(_highscore, new Vector2(120, 446),new Vector2(373, 70));
    }

    @Override
    public void update(float deltaTime) {
        checkButtons();
    }

    public void checkButtons(){

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            try {
                Input.TouchEvent event = touchEvents.get(i);
                if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                    if (Functions.checkButton(event, playButton)) {
                        play();
                    }
                    else if(Functions.checkButton(event, highscoreButton)){
                        highscore();
                    }
                }
            }
            catch(IndexOutOfBoundsException ex){
                Log.e("MainMenu", "Touch out of bound! Wtf?");
            }
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawImage(Assets.splashScreen, 0, 0);

        if(highscoreButton != null) {
            g.drawImage(playButton.sprite, playButton.position.x, playButton.position.y);
            g.drawImage(highscoreButton.sprite, highscoreButton.position.x, highscoreButton.position.y);
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

    }

    public void play(){
        Log.d("Menu", "Pressed the Play button");
        game.setScreen(new Level(game));
    }

    public void highscore(){
        Log.d("Menu", "Pressed the Highscore button");
        game.setScreen(new Highscore(game));
    }

}
