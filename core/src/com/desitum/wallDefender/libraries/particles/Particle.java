package com.desitum.wallDefender.libraries.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 * Created by kody on 4/7/15.
 * can be used by kody and people in []
 */
public class Particle extends Sprite {

    private ArrayList<com.desitum.wallDefender.libraries.animation.Animator> animators;

    private float lifetime;

    public Particle(Texture texture, float lifetime, float width, float height, float x, float y, ParticleSettings ps){
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();

        this.animators = new ArrayList<com.desitum.wallDefender.libraries.animation.Animator>();

        ps.getXAnimator().setSprite(this, true, false);
        ps.getYAnimator().setSprite(this, false, true);

        this.animators.add(ps.getXAnimator());
        this.animators.add(ps.getYAnimator());

        startAllAnimators();

        this.lifetime = lifetime;
    }


    public void update(float delta){
        for (com.desitum.wallDefender.libraries.animation.Animator anim: animators){
            anim.update(delta);
        }

        lifetime -= delta;
    }

    public float getLifetime(){
        return lifetime;
    }

    public void startAllAnimators(){
        for (com.desitum.wallDefender.libraries.animation.Animator anim: animators){
            anim.start(false);
        }
    }
}
