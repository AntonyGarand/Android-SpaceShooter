package io.garand.antony.jeuandroid.Misc;

import android.graphics.Rect;

import java.sql.Time;

/**
 * Created by Antony on 23/nov./2015.
 */
public class AnimationFrame {
    public Rect SourceRect;

    public float length;

    public AnimationFrame(){
        SourceRect = new Rect();
        length = 0f;
    }

    public AnimationFrame(int left, int top, int right, int bot, float _length){
        SourceRect = new Rect(left, top, right, bot);
        length = _length;
    }

}
