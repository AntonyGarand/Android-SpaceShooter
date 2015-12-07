package io.garand.antony.jeuandroid. GameObject;

import android.graphics.Rect;

import io.garand.antony.framework.Image;
import io.garand.antony.framework.Sound;
import io.garand.antony.framework.implementation.AndroidGraphics;
import io.garand.antony.jeuandroid.Misc.Assets;
import io.garand.antony.jeuandroid.Misc.Vector2;
import io.garand.antony.jeuandroid.Misc.Vector2f;

/**
 * Created by Antony on 2015-11-27.
 */
public class PlayerController {

    Player player;

    public PlayerController(Image sprite, Image bulletSprite, Sound shootSound){
        if(player == null){
            player = new Player(sprite, bulletSprite, shootSound);
        }
        Assets.data.player = this;
    }

    public void update(float deltaTime){
        if(player != null) {
            player.update(deltaTime);
        }
    }

    public void moveLeft(float deltaTime){
        if(player != null) {
            player.moveLeft(deltaTime);
        }
    }
    public void moveRight(float deltaTime){
        if(player != null) {
            player.moveRight(deltaTime);
        }
    }

    public Image getSprite(){
        if(player != null) {
            return player.getSprite();
        }
        else{
            return null;
        }
    }
    public Vector2f getPosition(){
        if(player != null){
            return player.getPosition();
        }
        else
        {
            return null;
        }
    }
    public Rect getRect(){
        if(player != null){
            return player.getRect();
        }
        else{
            return null;
        }
    }

    public void shoot(){
        player.shoot();
    }

}