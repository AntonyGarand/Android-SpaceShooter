package io.garand.antony.jeuandroid. GameObject;

import io.garand.antony.framework.Image;
import io.garand.antony.jeuandroid.Misc.Vector2;
import io.garand.antony.jeuandroid.Misc.Vector2f;

/**
 * Created by Antony on 2015-11-27.
 */
public abstract class GameObject {

    protected Vector2f position;
    protected Vector2f direction;
    public Image sprite;

    public GameObject(Image _sprite){
        sprite = _sprite;
        position = new Vector2f(0,0);
        direction = new Vector2f(0,0);
    }

    public GameObject(Image _sprite, Vector2f _position){
        sprite = _sprite;
        position = _position;
        direction = new Vector2f(0,0);
    }

    public GameObject(Image _sprite, Vector2f _position, Vector2f _direction){
        sprite = _sprite;
        position = _position;
        direction = _direction;
    }

    public Vector2f getPosition(){
        return position;
    }

    public void setPosition(Vector2f newPosition){
        position = newPosition;
    }

    public void update(float deltaTime){
        position.x += direction.x * deltaTime;
        position.y += direction.y * deltaTime;
    }

}
