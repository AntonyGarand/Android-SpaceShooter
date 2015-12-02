package io.garand.antony.jeuandroid.Screens;

/**
 * Created by Antony on 2015-11-27.
 */

import android.graphics.Rect;
import android.util.Log;

import io.garand.antony.framework.Game;
import io.garand.antony.framework.Graphics;
import io.garand.antony.framework.Image;
import io.garand.antony.framework.Input;
import io.garand.antony.framework.Pool.PoolObjectFactory;
import io.garand.antony.framework.Screen;
import io.garand.antony.framework.Graphics.ImageFormat;
import io.garand.antony.framework.implementation.AndroidGraphics;
import io.garand.antony.jeuandroid.GameObject.AsteroidController;
import io.garand.antony.jeuandroid.GameObject.Background;
import io.garand.antony.jeuandroid.GameObject.EnemyController;
import io.garand.antony.jeuandroid.GameObject.PlayerController;
import io.garand.antony.jeuandroid.Misc.Assets;
import io.garand.antony.jeuandroid.Misc.Functions;
import io.garand.antony.jeuandroid.Misc.Vector2;
import io.garand.antony.jeuandroid.Misc.Vector2f;
import io.garand.antony.jeuandroid.UI.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Level extends Screen {

    public static enum gameState {
        Playing, Paused, Dead
    }

    gameState state;

    int     enemyCount,
            enemyAliveCount,
            enemyRemainingCount,
            enemyCap,

            currentLevel;

    float   timeToNextEnemy,
            enemySpawnRate,
            totalTime;

    Button  buttonLeft,
            buttonRight,
            buttonShoot;

    AsteroidController asteroidController;

    List<EnemyController>   enemyPoolFree,
                            enemyPoolUsed;

    Image background;

    Background cloud;

    Random rnd;

    public Level(Game game) {
        super(game);
        currentLevel = 1;
        createLevel();
        loadGraphics(game.getGraphics());
    }

    public Level(Game game, int level){
        super(game);
        currentLevel = level;
        createLevel();
        loadGraphics(game.getGraphics());
    }

    void loadGraphics(Graphics g){

        background = g.newImage("BackgroundSky.png", ImageFormat.RGB565);
        cloud = new Background(g.newImage("BackgroundSmoke.png", ImageFormat.ARGB4444));

        Image asteroidSprite = g.newImage("BackgroundSmoke.png", ImageFormat.ARGB4444);

        Assets.data.player = new PlayerController(g.newImage("ship.png", ImageFormat.ARGB4444));

        //Image _sprite, Vector2 _position, Vector2 _size
        buttonLeft = new Button(g.newImage("left.png", ImageFormat.ARGB8888), new Vector2(35, 600), new Vector2(150,100));
        buttonRight = new Button(g.newImage("right.png", ImageFormat.ARGB8888), new Vector2(200, 600), new Vector2(150,100));
    }

    private void createLevel(){
        state = gameState.Playing;
        //TODO: Dynamic seed
        // A seed is used for the futur replay potential
        rnd = new Random(42);

        int enemyPerLevel = 5;
        int asteroidRatio = 2;
        enemyCap = 10;

        //Not level 5 or 10
        if(currentLevel % 5 != 0){
            enemyCount = currentLevel * enemyPerLevel;
            enemyRemainingCount = enemyCount;
        }
        //Level 10+10x (Boss)
        else if(currentLevel % 10 == 0){
            //TODO: Boss
        }
        //Level 5+10x
        else{
            enemyCount = 0;
        }
        enemyRemainingCount = enemyCount;
        enemyAliveCount = 0;

        asteroidController = new AsteroidController((AndroidGraphics)game.getGraphics(), currentLevel);

        enemySpawnRate = 100f + (400f / currentLevel);

        timeToNextEnemy = enemySpawnRate + rnd.nextFloat();

        enemyPoolFree = new ArrayList<>();
        enemyPoolUsed = new ArrayList<>();
    }

    private void checkButtons(float deltaTime){
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (Functions.checkButton(event, buttonLeft)) {
                Assets.data.player.moveLeft(deltaTime);
            }
            else if (Functions.checkButton(event, buttonRight)) {
                Assets.data.player.moveRight(deltaTime);
            }

        }
    }

    public void playerDeath(){
        state = gameState.Dead;
    }

    @Override
    public void update(float deltaTime) {

        timeToNextEnemy -= deltaTime;
        if(timeToNextEnemy < 0){
            spawnEnemy();
            timeToNextEnemy += enemySpawnRate + rnd.nextFloat();
        }
        checkButtons(deltaTime);
        //Updating the player
        Assets.data.player.update(deltaTime);

        //Updating the background
        cloud.update(deltaTime);
    }

    void spawnEnemy(){
        //TODO: Spawn a new ennemy
        Log.d("Level", "Spawn new enemy");
    }

    //Todo: Remove? Or keep for boss?
    void spawnAsteroid(){

        asteroidController.spawnAsteroid();
    }


    @Override
    public void paint(float deltaTime) {

        //1. Draw background
        AndroidGraphics g = (AndroidGraphics) game.getGraphics();

        g.drawImage(background, 0, 0);
        //Draw both "smoke" effects for a never ending scrolling effect
        g.drawImage(cloud.sprite, (int)cloud.getPosition().x, (int)cloud.getPosition().y);
        g.drawImage(cloud.sprite, (int)cloud.getPosition().x,(int) cloud.getPosition().y - cloud.sprite.getHeight());

        //2. Draw projectiles
        //3. Draw ennemies

        //3.1: Asteroids
        asteroidController.draw();

        //4. Draw player
        Rect playerRect = Assets.data.player.getRect();
        Vector2f playerPos = Assets.data.player.getPosition();
        //Drawing the image with drawImage(sprite, posX, posY, rectX, rectY, rectWidth, rectHeight);
        g.drawImage(Assets.data.player.getSprite(), (int)playerPos.x, (int)playerPos.y, playerRect.left, playerRect.top, playerRect.width(), playerRect.height());

        //5. Draw UI
        g.drawImage(buttonLeft.sprite, buttonLeft.position.x, buttonLeft.position.y);
        g.drawImage(buttonRight.sprite, buttonRight.position.x, buttonRight.position.y);

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
        state = gameState.Paused;
    }

    void requestAsteroid(){
        asteroidController.spawnAsteroid();
    }

    public void enemyDeath(){
        enemyAliveCount--;
    }
}
