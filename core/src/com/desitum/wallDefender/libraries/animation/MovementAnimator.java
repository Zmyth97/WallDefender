package com.desitum.wallDefender.libraries.animation;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.wallDefender.libraries.interpolation.AccelerateDecelerateInterpolator;
import com.desitum.wallDefender.libraries.interpolation.BounceInterpolator;

/**
 * Created by kody on 2/24/15.
 * can be used by kody and people in []
 */
public class MovementAnimator implements Animator {

    private float startPos;
    private float endPos;
    private float travelDistance;
    private float currentPosition;

    private float duration;
    private float timeInAnimation;

    private float currentDelay;
    private float animationDelay;

    private boolean running;
    private boolean ran;
    private boolean controllingX;
    private boolean controllingY;
    private com.desitum.wallDefender.libraries.interpolation.Interpolator interpolator;
    private Sprite controllingSprite;

    public MovementAnimator(float startPos, float endPos, float duration, int interpolator){
        this.controllingSprite = null;
        this.startPos = startPos;
        this.endPos = endPos;
        this.travelDistance = endPos - startPos;
        this.currentPosition = startPos;

        this.duration = duration;

        setupInterpolator(interpolator);
    }

    public MovementAnimator(Sprite sprite, float startPos, float endPos, float duration, float delay, int interpolator, boolean controlX, boolean controlY){
        this.controllingSprite = sprite;
        this.startPos = startPos;
        this.endPos = endPos;
        this.travelDistance = endPos - startPos;
        this.currentPosition = startPos;

        this.animationDelay = delay;
        this.currentDelay = 0;

        this.controllingX = controlX;
        this.controllingY = controlY;

        this.duration = duration;

        setupInterpolator(interpolator);
    }

    public void setSprite(Sprite control, boolean controlX, boolean controlY){
        this.controllingSprite = control;

        this.controllingX = controlX;
        this.controllingY = controlY;
    }

    public void update(float delta){
        if (!running){
            return;
        }

        if (currentDelay < animationDelay){
            currentDelay += delta;
            return;
        }

        timeInAnimation += delta/duration;
        if (timeInAnimation >= 1){
            timeInAnimation = 1;
            stop();
        }

        currentPosition = interpolator.getInterpolation(timeInAnimation) * travelDistance + startPos;

        if (this.controllingSprite != null){
            if (this.controllingX){
                this.controllingSprite.setX(getCurrentPos());
            }
            if (this.controllingY) {
                this.controllingSprite.setY(getCurrentPos());
            }
        }
    }

    public float getCurrentPos(){
        return currentPosition;
    }

    public void start(boolean isProtected){
        this.currentDelay = 0;
        this.timeInAnimation = 0;
        if (isProtected && !ran){
            running = true;
        } else if (!isProtected) {
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
        } else if (interpolator == com.desitum.wallDefender.libraries.interpolation.Interpolation.LINEAR_INTERPOLATOR){
            this.interpolator = com.desitum.wallDefender.libraries.interpolation.LinearInterpolator.$();
        }
    }

    public void reset(){
        ran = false;
        running = false;
        currentPosition = startPos;
        timeInAnimation = 0;
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
        return getCurrentPos();
    }

    @Override
    public Animator duplicate() {
        return new MovementAnimator(this.controllingSprite, this.startPos, this.endPos, this.duration, this.animationDelay, com.desitum.wallDefender.libraries.interpolation.Interpolation.getInterpolatorNum(this.interpolator), this.controllingX, this.controllingY);
    }

    public float getStartPos() {
        return startPos;
    }

    public void setStartPos(float startPos) {
        this.startPos = startPos;
    }

    public float getEndPos() {
        return endPos;
    }

    public void setEndPos(float endPos) {
        this.endPos = endPos;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getCurrentDelay() {
        return currentDelay;
    }

    public void setCurrentDelay(float currentDelay) {
        this.currentDelay = currentDelay;
    }

    public boolean isControllingX() {
        return controllingX;
    }

    public void setControllingX(boolean controllingX) {
        this.controllingX = controllingX;
    }

    public boolean isControllingY() {
        return controllingY;
    }

    public void setControllingY(boolean controllingY) {
        this.controllingY = controllingY;
    }

    public Sprite getControllingSprite() {
        return controllingSprite;
    }

    public void setControllingSprite(Sprite controllingSprite) {
        this.controllingSprite = controllingSprite;
    }

    public float getDistance(){
        return travelDistance;
    }
}
