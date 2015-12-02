package io.garand.antony.framework;

/**
 * Created by Antony on 22/nov./2015.
 */

public interface Audio {
    public Music createMusic(String file);

    public Sound createSound(String file);
}
