package com.desitum.wallDefender.libraries.particles;

import java.util.Random;

/**
 * Created by kody on 4/8/15.
 * can be used by kody and people in []
 */
public class ParticleSettings {

    private float speedX;
    private float speedY;

    private com.desitum.wallDefender.libraries.animation.MovementAnimator xAnimator;
    private com.desitum.wallDefender.libraries.animation.MovementAnimator yAnimator;

    public ParticleSettings(float x, float y, float objectWidth, float objectHeight, float minSpeedX, float maxSpeedX, float minSpeedY, float maxSpeedY, float duration){
        Random random = new Random();
        speedX = random.nextFloat() * (maxSpeedX - minSpeedX) + minSpeedX;
        speedX *= 5;
        speedY = random.nextFloat() * (maxSpeedY - minSpeedY) + minSpeedY;
        speedY *= 5;

        float startPosX = random.nextFloat() * (objectWidth) + x;
        float startPosY = random.nextFloat() * (objectHeight) + y;

        xAnimator = new com.desitum.wallDefender.libraries.animation.MovementAnimator(startPosX, startPosX + random.nextFloat() * 5, duration, com.desitum.wallDefender.libraries.interpolation.Interpolation.DECELERATE_INTERPOLATOR);
        yAnimator = new com.desitum.wallDefender.libraries.animation.MovementAnimator(startPosY, startPosY + random.nextFloat() * 5, duration, com.desitum.wallDefender.libraries.interpolation.Interpolation.ACCELERATE_INTERPOLATOR);
    }

    public com.desitum.wallDefender.libraries.animation.Animator getXAnimator(){
        return xAnimator;
    }

    public com.desitum.wallDefender.libraries.animation.Animator getYAnimator(){
        return yAnimator;
    }
}
