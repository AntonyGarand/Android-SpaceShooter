package io.garand.antony.jeuandroid. GameObject;

import io.garand.antony.framework.Image;
import io.garand.antony.jeuandroid.Misc.Vector2;
import io.garand.antony.jeuandroid.Misc.Vector2f;

/**
 * Created by Antony on 2015-11-27.
 */
public class Background extends GameObject {

    public Background(Image sprite){
        super(sprite);
        position = new Vector2f(0,0);
        direction = new Vector2f(0, 2);
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        //Reset the position if we're too far
        int spriteHeight = sprite.getHeight();
        if(position.y > spriteHeight){
            position.y -= spriteHeight;
        }
    }

}
