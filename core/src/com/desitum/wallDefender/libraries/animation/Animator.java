package com.desitum.wallDefender.libraries.animation;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by dvan6234 on 2/24/2015.
 */
public interface Animator {
    public void update(float delta);
    public void start(boolean isProtected);
    public void setSprite(Sprite control, boolean controlx, boolean controly);
    public boolean updateY();
    public boolean updateX();
    public float getAmount();
    public Animator duplicate();
    public boolean isRunning();
}
