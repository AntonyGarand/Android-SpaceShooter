package io.garand.antony.jeuandroid.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.garand.antony.framework.Graphics;
import io.garand.antony.framework.implementation.AndroidGraphics;
import io.garand.antony.jeuandroid.Misc.Assets;
import io.garand.antony.jeuandroid.Misc.Vector2f;

/**
 * Created by Antony on 2015-11-27.
 */
public class AsteroidController{

    Random rnd;

    int     asteroidCount,
            asteroidRemainingCount,
            asteroidCap,

            asteroidBigModelCount,
            asteroidMediumModelCount,
            asteroidSmallModelCount;

    String  asteroidStaticNameBig,
            asteroidStaticNameMedium,
            asteroidStaticNameSmall;

    float   timeToNextAsteroid,
            asteroidMinTime,
            asteroidSpawnRate;

    List<Asteroid>  asteroidPoolFree,
                    asteroidPoolUsed;

    AndroidGraphics g;

    public AsteroidController(AndroidGraphics _g, int currentLevel) {
        g = _g;
        //Todo: Dynamic seed

        setStaticValues(currentLevel);

        asteroidCap = 15;
        asteroidCount = 0;
        asteroidRemainingCount = 0;
    }

    public void setStaticValues(int currentLevel){

        rnd = new Random(42);

        asteroidMinTime = 50f;
        asteroidSpawnRate = 50f + (400f / currentLevel);
        timeToNextAsteroid = asteroidMinTime + asteroidSpawnRate * rnd.nextFloat();

        asteroidPoolFree = new ArrayList<>();
        asteroidPoolUsed = new ArrayList<>();

        asteroidBigModelCount = 2;
        asteroidMediumModelCount = 3;
        asteroidSmallModelCount = 1;

        asteroidStaticNameBig = "AsteroidBig";
        asteroidStaticNameMedium = "AsteroidMedium";
        asteroidStaticNameSmall = "AsteroidSmall";
    }

    public void update(float deltaTime){
        //Updating the asteroids
        for (Asteroid asteroid: asteroidPoolUsed) {
            asteroid.update(deltaTime);
        }

        if(timeToNextAsteroid < 0){
            spawnAsteroid();
            timeToNextAsteroid += asteroidSpawnRate + rnd.nextFloat();
        }
    }

    public void spawnAsteroid(){
        //Do not spawn an asteroid if we're over the level cap
        if(asteroidPoolUsed.size() > asteroidCap){
            return;
        }

        Asteroid asteroid;

        //If we have asteroids available, use it
        if(!asteroidPoolFree.isEmpty()){
            asteroid = asteroidPoolFree.remove(0);
        }
        //Else, create a new one
        else {
            //Generate an new asteroidController with a random "big" model
            asteroid = new Asteroid(null, 0);
        }

        //Random angle between 30 and 150
        float rotAngle = rnd.nextFloat() * 90 + 45f;
        float speedX = (float)Math.cos(Math.toRadians(rotAngle)) * 3;
        float speedY = rnd.nextFloat() + 1f;

        //Resets the asteroid and assign it a random sprite
        asteroid.setValues(new Vector2f(rnd.nextFloat() * Assets.screenWidth, 0), new Vector2f(speedX, speedY), rnd.nextFloat() * 2, 2, g.newImage(asteroidStaticNameBig + rnd.nextInt(asteroidBigModelCount) + ".png", Graphics.ImageFormat.ARGB4444));
        asteroidPoolUsed.add(asteroid);

    }

    public void draw(){
        for (Asteroid asteroid: asteroidPoolUsed) {
            Vector2f astPosition = asteroid.getPosition();
            g.drawRotatedImage(asteroid.sprite, (int)astPosition.x, (int)astPosition.y, asteroid.getRotation());
        }
    }

}
