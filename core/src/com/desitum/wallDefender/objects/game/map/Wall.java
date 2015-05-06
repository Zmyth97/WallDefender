package com.desitum.wallDefender.objects.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Zmyth97 on 5/5/2015.
 */
public class Wall extends Sprite {

    private float health;

    public Wall(Texture texture, float x, float y, float width, float height) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());


        this.setSize(width, height);
        this.setX(x);
        this.setY(y);
    }

    private void doDamage(float amount){
        health -= amount;
        if (health <= 0){
            //Destroy Wall
        }
    }

    public float getHealth(){
        return health;
    }

    public void setHealth(float healthAddition){
        this.health = healthAddition;
    }
}
