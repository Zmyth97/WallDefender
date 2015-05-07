package com.desitum.wallDefender.objects.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.wallDefender.screens.GameScreen;

/**
 * Created by Zmyth97 on 5/6/2015.
 */
public class Enemy extends Sprite {

    public static final int BASIC = 1;
    public static final int MEDIUM = 2;
    public static final int HEAVY = 3;
    public static final int ELITE = 4;

    private float speed;
    private float health;

    public Enemy(Texture texture, int enemyType, float x, float y, float width, float height) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        switch (enemyType){
            case BASIC:
                speed = 10;
                health = 10;
                break;
            case MEDIUM:
                speed = 8;
                health = 14;
                break;
            case HEAVY:
                speed = 6;
                health = 16;
                break;
            case ELITE:
                speed = 4;
                health = 18;
                break;
        }

        this.setSize(width, height);
        this.setX(x);
        this.setY(y);
    }

    public void update(float delta){
        if(getX() < (GameScreen.FRUSTUM_WIDTH - GameScreen.FRUSTUM_WIDTH/2.575)){
            this.setX(getX() + speed * delta);
        }
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

    public float getSpeed(){
        return speed;
    }

    public void setSpeed(float speedAddition){
        this.speed = speedAddition;
    }
}
