package com.desitum.wallDefender.libraries.animation;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.wallDefender.libraries.interpolation.AccelerateDecelerateInterpolator;
import com.desitum.wallDefender.libraries.interpolation.BounceInterpolator;

/**
 * Created by dvan6234 on 2/24/2015.
 */
public class ScaleAnimator implements Animator {

    private float duration;
    private float startScale;
    private float endScale;

    private float scaleSize;

    private float timeInAnimation;
    private float animationDelay;
    private float currentDelay;

    private boolean running;
    private boolean ran;
    private boolean growing;

    private com.desitum.wallDefender.libraries.interpolation.Interpolator interpolator;

    private Sprite controllingSprite;
    private boolean controllingX;
    private boolean controllingY;

    public ScaleAnimator(float duration, float startScale, float endScale, int interpolator){
        this.duration = duration;
        this.startScale = startScale;
        this.endScale = endScale;

        this.controllingSprite = null;

        timeInAnimation = 0;
        currentDelay = 0;
        animationDelay = 0;

        if (startScale > endScale){
            growing = false;
        } else {
            growing = true;
        }

        setupInterpolator(interpolator);
    }

    public ScaleAnimator(float duration, float delay, float startScale, float endScale, int interpolator){
        this.duration = duration;
        this.startScale = startScale;
        this.endScale = endScale;

        this.controllingSprite = null;

        timeInAnimation = 0;
        currentDelay = 0;
        animationDelay = delay;

        if (startScale > endScale){
            growing = false;
        } else {
            growing = true;
        }

        setupInterpolator(interpolator);
    }

    public ScaleAnimator(Sprite sprite, float duration, float delay, float startScale, float endScale, int interpolator, boolean controlWidth, boolean controlHeight){
        this.duration = duration;
        this.startScale = startScale;
        this.endScale = endScale;

        this.controllingSprite = sprite;
        this.controllingX = controlWidth;
        this.controllingY = controlHeight;

        timeInAnimation = 0;
        currentDelay = 0;
        animationDelay = delay;

        if (startScale > endScale){
            growing = false;
        } else {
            growing = true;
        }

        setupInterpolator(interpolator);
    }

    @Override
    public void update(float delta){
        if (!running){
            return;
        }
        if (currentDelay < animationDelay){
            currentDelay += delta;
            return;
        }

        timeInAnimation += delta / duration;

        if (timeInAnimation >= 1){
            timeInAnimation = 1;
            stop();
        }

        if (growing){
            scaleSize = startScale + (endScale - startScale) * interpolator.getInterpolation(timeInAnimation);
        } else {
            scaleSize = startScale - (startScale - endScale) * interpolator.getInterpolation(timeInAnimation);
        }

        if (controllingSprite != null){
            if (controllingY){
                this.controllingSprite.setScale(this.controllingSprite.getScaleX(), this.getScaleSize());
            }
            if (controllingX){
                this.controllingSprite.setScale(this.getScaleSize(), this.controllingSprite.getScaleY());
            }
        }
    }

    public void setSprite(Sprite control, boolean controlX, boolean controlY){
        this.controllingSprite = control;

        this.controllingX = controlX;
        this.controllingY = controlY;
    }

    @Override
    public boolean updateY() {
        return controllingY;
    }

    @Override
    public boolean updateX() {
        return controllingX;
    }

    @Override
    public float getAmount() {
        return getScaleSize();
    }

    public float getScaleSize(){
        return scaleSize;
    }

    public void start(boolean isProtected){
        if (isProtected && !ran){
            running = true;
        } else  if (!isProtected) {
            running = true;
        }
        ran = true;
    }

    public void stop(){
        running = false;
    }

    public boolean didFinish(){
        if (ran && !running){
            return true;
        }
        return false;
    }

    @Override
    public boolean isRunning(){
        return running;
    }

    private void setupInterpolator(int interpolator){
        if (interpolator == com.desitum.wallDefender.libraries.interpolation.Interpolation.ACCELERATE_INTERPOLATOR){
            this.interpolator = com.desitum.wallDefender.libraries.interpolation.AccelerateInterpolator.$();
        } else if (interpolator == com.desitum.wallDefender.libraries.interpolation.Interpolation.DECELERATE_INTERPOLATOR){
            this.interpolator = com.desitum.wallDefender.libraries.interpolation.DecelerateInterpolator.$();
        } else if (interpolator == com.desitum.wallDefender.libraries.interpolation.Interpolation.ANTICIPATE_INTERPOLATOR){
            this.interpolator = com.desitum.wallDefender.libraries.interpolation.AnticipateInterpolator.$();
        } else if (interpolator == com.desitum.wallDefender.libraries.interpolation.Interpolation.OVERSHOOT_INTERPOLATOR){
            this.interpolator = com.desitum.wallDefender.libraries.interpolation.OvershootInterpolator.$();
        } else if (interpolator == com.desitum.wallDefender.libraries.interpolation.Interpolation.ACCELERATE_DECELERATE_INTERPOLATOR){
            this.interpolator = AccelerateDecelerateInterpolator.$();
        } else if (interpolator == com.desitum.wallDefender.libraries.interpolation.Interpolation.BOUNCE_INTERPOLATOR){
            this.interpolator = BounceInterpolator.$();
        }
    }

    @Override
    public Animator duplicate() {
        return new ScaleAnimator(controllingSprite, duration, animationDelay, startScale, endScale, com.desitum.wallDefender.libraries.interpolation.Interpolation.getInterpolatorNum(interpolator), controllingX, controllingY);
    }
}
