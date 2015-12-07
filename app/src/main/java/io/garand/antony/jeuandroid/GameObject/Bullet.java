package io.garand.antony.jeuandroid.GameObject;

import io.garand.antony.framework.Image;
import io.garand.antony.jeuandroid.Misc.Assets;
import io.garand.antony.jeuandroid.Misc.Vector2f;

/**
 * Created by Antony on 28/nov./2015.
 */
public class Bullet extends GameObject {

    static Image bulletSprite;

    public Bullet(Image _sprite, Vector2f _position, Vector2f _direction) {
        super(_sprite, _position, _direction);
    }
    public void setValues(Image _sprite, Vector2f _position, Vector2f _direction){
        sprite = _sprite;
        position = _position;
        direction = _direction;
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        //Checking if we're out of bounds
        if(position.y < 0){
            Assets.data.bulletController.onBulletDeath(this);
        }
    }

}
