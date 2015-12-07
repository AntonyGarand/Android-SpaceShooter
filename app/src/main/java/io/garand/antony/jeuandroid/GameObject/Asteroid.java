package io.garand.antony.jeuandroid. GameObject;

import io.garand.antony.framework.Image;
import io.garand.antony.framework.Sound;
import io.garand.antony.jeuandroid.Misc.Assets;
import io.garand.antony.jeuandroid.Misc.Vector2f;

/**
 * Created by Antony on 2015-11-27.
 */
public class Asteroid extends Living{

    private float   rotationValue,
                    rotationSpeed;

    public AsteroidController controller;

    public Asteroid(Image sprite, int _health, AsteroidController _controler) {
        super(sprite);
        health = _health;
        controller = _controler;
    }

    public float getRotation(){
        return rotationValue;
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        rotationValue += rotationSpeed * deltaTime;
        if(rotationValue > 360){
            rotationValue %= 360;
        }
        else if(rotationValue <= -360){
            rotationValue %= 360;
        }

        if(position.x > Assets.screenWidth){
            position.x %= Assets.screenWidth;
        } else if(position.x <= -5){
            position.x = Assets.screenWidth;
        }

        if(position.y > Assets.screenHeight){
            die();
            //Player let an asteroid pass
            Assets.data.currentLevel.removeLives();
        }
    }

    public void setValues(Vector2f _position, Vector2f _direction, float _rotationSpeed, int _health, Image _sprite){
        position = _position;
        direction = _direction;
        rotationSpeed = _rotationSpeed;
        health = _health;
        sprite = _sprite;
    }

    @Override
    protected void die() {
        controller.asteroidDeath(this);
    }

    public Vector2f getMidPosition(){
        Vector2f midPos = new Vector2f(position.x, position.y);
        midPos.x += sprite.getWidth() / 2;
        midPos.y += sprite.getHeight() / 2;
        return midPos;
    }
}
