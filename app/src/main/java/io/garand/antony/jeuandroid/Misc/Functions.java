package io.garand.antony.jeuandroid.Misc;

import android.util.Log;

import io.garand.antony.framework.Input;
import io.garand.antony.jeuandroid.UI.Button;

/**
 * Created by Antony on 24/nov./2015.
 */
public final class Functions {

    public static boolean checkButton(Input.TouchEvent event, Button button){
        if(button.isCircle){
            return inBounds(event, button.position.x, button.position.y, button.radius);
        }
        else{
            return inBounds(event,button.position.x, button.position.y, button.sprite.getWidth(), button.sprite.getHeight());
        }
    }
    public static boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    public static boolean inBounds(Input.TouchEvent event, int x, int y, int radius){
        //Checking the distance between the center of the circle and the touch input
        return distBetweenPoints(event.x, event.y, x, y) < radius;
    }

    public static float distBetweenPoints(int x1, int y1, int x2, int y2){
        //Pythagore
        return (float)Math.sqrt((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
    }


}
