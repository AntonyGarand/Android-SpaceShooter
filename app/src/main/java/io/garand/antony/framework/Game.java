package io.garand.antony.framework;

/**
 * Created by Antony on 22/nov./2015.
 */
public interface Game {
    public Audio getAudio();
    public Input getInput();
    public FileIO getFileIO();
    public Graphics getGraphics();
    public void setScreen(Screen screen);
    public Screen getCurrentScreen();
    public Screen getInitScreen();

}
