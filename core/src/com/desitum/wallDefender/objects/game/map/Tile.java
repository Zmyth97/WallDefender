package com.desitum.wallDefender.objects.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Zmyth97 on 5/5/2015.
 */
public class Tile extends Sprite {

    public Tile(Texture texture, float x, float y, float width, float height){
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        this.setSize(width, height);
        this.setX(x);
        this.setY(y);
    }
}
