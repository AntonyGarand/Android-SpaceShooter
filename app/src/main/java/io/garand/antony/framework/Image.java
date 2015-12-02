package io.garand.antony.framework;

/**
 * Created by Antony on 22/nov./2015.
 */
import android.graphics.Bitmap;
import android.graphics.Rect;

import io.garand.antony.framework.Graphics.ImageFormat;

public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
    public Bitmap getCroppedImage(Rect part);
    }