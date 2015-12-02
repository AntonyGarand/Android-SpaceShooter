package io.garand.antony.jeuandroid.UI;


import io.garand.antony.framework.Image;
import io.garand.antony.jeuandroid.Misc.Functions;
import io.garand.antony.jeuandroid.Misc.Vector2;
/**
 * Created by Antony on 2015-11-27.
 */
public class Button {
    public Image sprite;
    public Vector2 position;
    public Vector2 size;
    public int radius;
    public boolean isCircle;


    public Button(Image _sprite, Vector2 _position, Vector2 _size){
        sprite = _sprite;
        position = _position;
        size = _size;
        isCircle = false;
    }

    public Button(Image _sprite, Vector2 _position, int _radius){
        sprite = _sprite;
        position = _position;
        radius = _radius;
        isCircle = true;
    }

}
