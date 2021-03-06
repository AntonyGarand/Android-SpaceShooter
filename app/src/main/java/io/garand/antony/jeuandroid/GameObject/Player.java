package io.garand.antony.jeuandroid.GameObject;

import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.garand.antony.framework.Graphics;
import io.garand.antony.framework.Image;
import io.garand.antony.framework.Sound;
import io.garand.antony.framework.implementation.AndroidGraphics;
import io.garand.antony.jeuandroid.Misc.Animation;
import io.garand.antony.jeuandroid.Misc.Assets;
import io.garand.antony.jeuandroid.Misc.Vector2;
import io.garand.antony.jeuandroid.Misc.Vector2f;

/**
 * Created by Antony on 26/nov./2015.
 */
public class Player extends Living{

    Map<String, Animation> status;
    Animation[] animations;
    Animation currentAnimation;

    float accelerationSpeed,
            speedCap,
            resistance,
            timeSinceLastMoved,
            delayToStopMove,

            timeToNextBullet,
            bulletDelay;

    Vector2 bulletSpawnPosition;

    boolean isMoving,
            canShoot;

    Image bulletSprite;

    Sound shootSound;

    public Player(Image spriteSheet, Image _bulletSprite, Sound _shootSound){
        //TODO: Not hardcoded spawn position?
        super(spriteSheet, new Vector2f(605, 550));

        health = 5;
        loadAnimations(spriteSheet);
        accelerationSpeed = 0.3f;
        speedCap = 10f;
        resistance = 1f;
        timeSinceLastMoved = 0f;
        delayToStopMove = 7f;

        canShoot = true;
        timeToNextBullet = 0f;
        bulletDelay = 15f;
        shootSound = _shootSound;

        //Todo: Pass image in constructor
        bulletSprite = _bulletSprite;
        bulletSpawnPosition = new Vector2(20,0);

    }

    public void loadAnimations(Image spriteSheet){
        //TODO: Split the animation spritesheet to save memory
        status =  new HashMap<String, Animation>();
        animations = new Animation[4];

        //Idle
        animations[0] = new Animation(spriteSheet);
        animations[0].addFrame(39, 43, 78, 86, 7.0f);
        animations[0].addFrame(39, 86, 78, 129, 7.0f);
        status.put("idle", animations[0]);

        //Right
        animations[1] = new Animation(spriteSheet);
        animations[1].addFrame(78, 43, 117, 83, 7.0f);
        animations[1].addFrame(78, 86, 117, 129, 7.0f);
        status.put("right", animations[1]);

        //Left
        animations[2] = new Animation(spriteSheet);
        animations[2].addFrame(0, 43, 39, 83, 7.0f);
        animations[2].addFrame(0, 86, 39, 129, 7.0f);
        status.put("left", animations[2]);

        //Stopped
        animations[3] = new Animation(spriteSheet);
        animations[3].addFrame(38, 0, 78, 43, 999f);
        status.put("stop", animations[3]);

        currentAnimation = status.get("idle");
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);

        //Checking the boundaries
        if(position.x > 1275){
            position.x = 0;
        }
        else if(position.x < -40){
            position.x = 1275;
        }

        if(!isMoving) {
            if (direction.x != 0) {
                if (direction.x > 0) {
                    direction.x -= resistance;
                    if (direction.x < resistance) {
                        direction.x = 0;
                        currentAnimation = status.get("idle");
                    }
                } else {
                    direction.x += resistance;
                    if (direction.x > -resistance) {
                        direction.x = 0;
                        currentAnimation = status.get("idle");
                    }
                }
            }
        }
        else {
            timeSinceLastMoved += deltaTime;
            if(timeSinceLastMoved > delayToStopMove){
                isMoving = false;
            }
        }

        currentAnimation.update(deltaTime);


        if(!canShoot){
            timeToNextBullet -= deltaTime;
            if(timeToNextBullet <= 0){
                canShoot = true;
            }
        }
    }

    @Override
    protected void die() {
        Assets.data.currentLevel.playerDeath();
    }

    public void moveLeft(float deltaTime){
        direction.x -=accelerationSpeed * deltaTime;
        if(direction.x < -speedCap){
            direction.x = -speedCap;
        }
        currentAnimation = status.get("left");
        isMoving();
    }
    public void moveRight(float deltaTime){
        direction.x +=accelerationSpeed * deltaTime;
        if(direction.x > speedCap){
            direction.x = speedCap;
        }
        currentAnimation = status.get("right");
        isMoving();
    }

    private void isMoving(){
        isMoving = true;
        timeSinceLastMoved = 0f;
    }

    public Image getSprite(){
        return currentAnimation.getSprite();
    }

    public Rect getRect(){
        return currentAnimation.currentRectangle();
    }

    public void shoot(){
        if(canShoot){
            canShoot = false;
            timeToNextBullet = bulletDelay;
            spawnBullet();
        }
    }

    void spawnBullet(){
        Assets.data.bulletController.spawnBullet(bulletSprite, new Vector2f(position.x + bulletSpawnPosition.x, position.y), new Vector2f(0f, -5f));
        shootSound.play(1f);
    }

}
