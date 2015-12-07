package io.garand.antony.jeuandroid.GameObject;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.garand.antony.framework.Graphics;
import io.garand.antony.framework.Sound;
import io.garand.antony.framework.implementation.AndroidGraphics;
import io.garand.antony.jeuandroid.Misc.Assets;
import io.garand.antony.jeuandroid.Misc.Vector2;
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
                    asteroidPoolUsed,
                    asteroidDeleteList;

    Sound asteroidExplode;

    AndroidGraphics g;

    public AsteroidController(AndroidGraphics _g, int currentLevel, Sound explosion) {
        g = _g;
        //Todo: Dynamic seed

        asteroidExplode = explosion;
        setStaticValues(currentLevel);

        asteroidCap = 15;
        asteroidCount = 0;
        asteroidRemainingCount = 0;
    }

    public void setStaticValues(int currentLevel){

        rnd = new Random(42);

        asteroidMinTime = 50f;
        asteroidSpawnRate = 50f;// + (400f / currentLevel);
        timeToNextAsteroid = asteroidMinTime + asteroidSpawnRate * rnd.nextFloat();

        asteroidPoolFree = new ArrayList<>();
        asteroidPoolUsed = new ArrayList<>();
        asteroidDeleteList = new ArrayList<>();


        asteroidBigModelCount = 2;
        asteroidMediumModelCount = 3;
        asteroidSmallModelCount = 1;

        asteroidStaticNameBig = "AsteroidBig";
        asteroidStaticNameMedium = "AsteroidMedium";
        asteroidStaticNameSmall = "AsteroidSmall";
    }

    public void update(float deltaTime){
        //Updating the asteroids
        int asteroidSize = asteroidPoolUsed.size();
        for(Asteroid asteroid : asteroidPoolUsed){
            asteroid.update(deltaTime);


            if(checkCollision(asteroid.getMidPosition())){
                asteroidDeath(asteroid);
                Assets.data.currentLevel.addScore();
            }
        }

        //To prevent breaking the update loop, we delete them after the update
        while(asteroidDeleteList.size() != 0){
            Asteroid asteroid = asteroidDeleteList.get(0);
            asteroidPoolUsed.remove(asteroid);
            asteroidPoolFree.add(asteroid);
            asteroidDeleteList.remove(asteroid);
            asteroidCount--;
        }

        //Spawning a new one if required
        if(timeToNextAsteroid < 0){
            Log.d("Asteroid", "Spawn asteroid");
            spawnAsteroid();
            timeToNextAsteroid = asteroidMinTime + asteroidSpawnRate * rnd.nextFloat();
        }

        timeToNextAsteroid-=deltaTime;
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
            asteroid = new Asteroid(null, 0, this);
        }

        //Random angle between 30 and 150
        float rotAngle = rnd.nextFloat() * 90 + 45f;
        float speedX = (float)Math.cos(Math.toRadians(rotAngle)) * 3f;
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

    public void asteroidDeath(Asteroid asteroid) {
        //The asteroid will be deleted on the next Delete job
        asteroidDeleteList.add(asteroid);
        asteroidExplode.play(1f);
    }

    public boolean checkCollision(Vector2f position){

        //TODO: Fix following bugs:
        //1. Certain bullets will speed up randomly
        //2. Certain asteroids won't be hit on contact
        //3. Certain "blank" spots will destroy an asteroid.
        int collisionRadius = 75;
        return Assets.data.bulletController.checkCollision(position, collisionRadius);
    }

}
