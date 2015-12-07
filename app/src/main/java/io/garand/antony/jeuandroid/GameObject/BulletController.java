package io.garand.antony.jeuandroid.GameObject;

import java.util.ArrayList;
import java.util.List;

import io.garand.antony.framework.Image;
import io.garand.antony.framework.implementation.AndroidGraphics;
import io.garand.antony.jeuandroid.Misc.Functions;
import io.garand.antony.jeuandroid.Misc.Vector2f;

/**
 * Created by Antony on 05/déc./2015.
 */
public class BulletController {
    List<Bullet>    bulletPoolFree,
                    bulletPoolUsed,
                    bulletDeleteList;

    AndroidGraphics g;

    public BulletController(AndroidGraphics _g){
        g = _g;
        bulletPoolFree = new ArrayList<>();
        bulletPoolUsed = new ArrayList<>();
        bulletDeleteList = new ArrayList<>();
    }

    public void update(float deltaTime){
        //Updating the bullets
        for(Bullet bullet: bulletPoolUsed){
            bullet.update(deltaTime);
        }
        //Deleting the dead ones
        while(bulletDeleteList.size() != 0){
            Bullet bullet = bulletDeleteList.get(0);
            bulletPoolUsed.remove(bullet);
            bulletPoolFree.add(bullet);
            bulletDeleteList.remove(bullet);
        }


    }

    public void paint(){
        for (Bullet bullet: bulletPoolUsed) {
            Vector2f bulletPosition = bullet.getPosition();
            g.drawImage(bullet.sprite, (int) bulletPosition.x, (int) bulletPosition.y);
        }
    }

    public void spawnBullet(Image bulletSprite, Vector2f position, Vector2f direction){
        Bullet bullet;

        //If we have bullets available, use them
        if(!bulletPoolFree.isEmpty()){
            bullet = bulletPoolFree.remove(0);
        }
        //Else, create a new one
        else {
            bullet = new Bullet(null, null, null);
        }

        //Resets the bullet
        bullet.setValues(bulletSprite, position, direction);

        bulletPoolUsed.add(bullet);
    }

    public void onBulletDeath(Bullet bullet){
        bulletDeleteList.add(bullet);
    }

    public boolean checkCollision(Vector2f position, int radius){
        for (Bullet bullet : bulletPoolUsed) {
            //Todo: Check if we're in bound before doing pythagore?
            //      Could save cpu power
            if(Functions.distBetweenPoints((int)position.x, (int)position.y, (int)bullet.position.x, (int)bullet.position.y) < radius){
                onBulletDeath(bullet);
                return true;
            }
        }
        return false;
    }
}
