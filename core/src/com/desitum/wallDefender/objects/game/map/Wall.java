package com.desitum.wallDefender.objects.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Zmyth97 on 5/5/2015.
 */
public class Wall extends Sprite {

    private static final float WALL_OVERLAP = -20.0f/60.0f;
    private static final float CORNER_OVERLAP = WALL_OVERLAP;

    private static final float WALL_EXTRA_HEIGHT = 1.0f + 1.0f/2.0f;
    private static final float CORNER_EXTRA_HEIGHT = WALL_EXTRA_HEIGHT;

    public static final int WALL = 1;
    public static final int CORNER_WALL = 2;

    private float health;

    public Wall(Texture texture, int wallType, float x, float y, float width, float height) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        switch (wallType){
            case WALL:
                height *= WALL_EXTRA_HEIGHT;
                y = y + height * WALL_OVERLAP;
                break;
            case CORNER_WALL:
                height *= CORNER_EXTRA_HEIGHT;
                y = y + height * CORNER_OVERLAP;
        }

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
