package io.garand.antony.jeuandroid. GameObject;

import io.garand.antony.framework.Image;
import io.garand.antony.jeuandroid.Misc.Vector2;
import io.garand.antony.jeuandroid.Misc.Vector2f;

/**
 * Created by Antony on 2015-11-27.
 */
public abstract class Living extends GameObject {
    protected int health;

    public Living(Image sprite){
        super(sprite);
    }

    public Living(Image sprite, Vector2f position){
        super(sprite, position);
    }

    public Living(Image sprite, Vector2f position, Vector2f direction){
        super(sprite, position, direction);
    }

    public Living(Image sprite, Vector2f position, Vector2f direction, int _health){
        super(sprite, position, direction);
        health = _health;
    }

    public void hit(int damage){
        health -= damage;
        if(health <= 0){
            die();
        }
    }

    protected abstract void die();
}
