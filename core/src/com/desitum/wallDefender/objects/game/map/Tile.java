package com.desitum.wallDefender.objects.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Zmyth97 on 5/5/2015.
 */
public class Tile extends Sprite {

    private static final float GRASS_OVERLAP = -20.0f/60.0f;
    private static final float STONE_OVERLAP = GRASS_OVERLAP;

    private static final float GRASS_EXTRA_HEIGHT = 1.0f + 1.0f/2.0f;
    private static final float STONE_EXTRA_HEIGHT = GRASS_EXTRA_HEIGHT;

    public static final int GRASS_TILE = 1;
    public static final int STONE_TILE = 2;

    public Tile(Texture texture, int tileType, float x, float y, float width, float height){
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        switch (tileType){
            case GRASS_TILE:
                height *= GRASS_EXTRA_HEIGHT;
                y = y + height * GRASS_OVERLAP;
                break;
            case STONE_TILE:
                height *= STONE_EXTRA_HEIGHT;
                y = y + height * STONE_OVERLAP;
        }
        this.setSize(width, height);
        this.setX(x);
        this.setY(y);
    }
}
