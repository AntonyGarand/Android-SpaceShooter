package io.garand.antony.jeuandroid. GameObject;

import io.garand.antony.framework.Image;
import io.garand.antony.jeuandroid.Misc.Assets;
import io.garand.antony.jeuandroid.Misc.Vector2f;

/**
 * Created by Antony on 2015-11-27.
 */
public class Asteroid extends Living{

    private float   rotationValue,
                    rotationSpeed;

    public Asteroid(Image sprite, int _health) {
        super(sprite);
        health = _health;
    }

    @Override
    protected void die() {
    }

    public void reset(Vector2f _position, Vector2f _direction, float rotation, Image _sprite){
        position = _position;
        direction = _direction;
        rotationValue = rotation;
        sprite = _sprite;
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

        if(position.x > Assets.screenWidth || position.x <= -5){
            position.x %= Assets.screenWidth;
        }

        if(position.y > Assets.screenHeight){
            die();
        }
    }

    public void setValues(Vector2f _position, Vector2f _direction, float _rotationSpeed, int _health, Image _sprite){
        position = _position;
        direction = _direction;
        rotationSpeed = _rotationSpeed;
        health = _health;
        sprite = _sprite;
    }

}
