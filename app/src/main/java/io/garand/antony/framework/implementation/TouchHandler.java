package io.garand.antony.framework.implementation;

/**
 * Created by Antony on 22/nov./2015.
 */
import java.util.List;

import android.view.View.OnTouchListener;

import io.garand.antony.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();
}