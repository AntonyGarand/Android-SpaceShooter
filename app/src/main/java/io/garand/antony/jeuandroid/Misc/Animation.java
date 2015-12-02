package io.garand.antony.jeuandroid.Misc;

import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.lang.Object;

import io.garand.antony.framework.Image;

/**
 * Created by Antony on 23/nov./2015.
 * Manages an animation with a custom time and frame count
 * To use: apply the rect "currentRectangle()" to the spriteSheet and object sprite
 * Must be called with the update(float deltaTime); when required
 */

public class Animation {
    ArrayList<AnimationFrame> frames;
    int currentFrame;
    Image spriteSheet;
    float timeIntoAnimation;
    float timeIntoFrame;
    boolean isAnimating;


    public Animation(Image _spriteSheet){
        spriteSheet = _spriteSheet;
        currentFrame = 0;
        isAnimating = false;
        frames = new ArrayList<AnimationFrame>();
    }

    public float duration(){
        float length = 0;
        for (AnimationFrame frame : frames) {
            length += frame.length;
        }
        return  length;
    }

    public Rect currentRectangle(){
        return frames.get(currentFrame).SourceRect;
    }

    private float currentFrameDuration(){
        return frames.get(currentFrame).length;
    }

    public void update(float DeltaTime){
        timeIntoAnimation += DeltaTime;
        timeIntoFrame += DeltaTime;
        //Change frame
        float duration = currentFrameDuration();
        if(timeIntoFrame > duration){
            currentFrame ++;
            if(currentFrame > frames.size()-1){
                currentFrame = 0;
            }
            timeIntoFrame = 0;
        }

    }


    public void addFrame(int left, int top, int right, int bot, float duration){
        frames.add(new AnimationFrame(left, top, right, bot, duration));
    }

    public Image getSprite(){
        return spriteSheet;
    }


}
