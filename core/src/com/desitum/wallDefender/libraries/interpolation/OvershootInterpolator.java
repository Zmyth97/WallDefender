package com.desitum.wallDefender.libraries.interpolation;

/**
 * Created by dvan6234 on 2/24/2015.
 */
/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

import com.badlogic.gdx.utils.Pool;

/**
 * An interpolator where the change overshoots the target and springs back to the target position.
 * <p/>
 * The factor defines the rate of overshoot.
 *
 * @author Moritz Post <moritzpost@gmail.com>
 */
public class OvershootInterpolator implements com.desitum.wallDefender.libraries.interpolation.Interpolator {

    private static final float DEFAULT_FACTOR = 1.1f;

    private static final Pool<OvershootInterpolator> pool = new Pool<OvershootInterpolator>(4, 100) {
        @Override
        protected OvershootInterpolator newObject() {
            return new OvershootInterpolator();
        }
    };

    private float factor;

    private double doubledFactor;

    OvershootInterpolator() {
        // hide constructor
    }

    /**
     * Gets a new {@link BounceInterpolator} from a maintained pool of {@link com.desitum.wallDefender.libraries.interpolation.Interpolator}s.
     *
     * @param factor the factor controlling the rate of overshoot energy change
     * @return the obtained {@link BounceInterpolator}
     */
    public static OvershootInterpolator $(float factor) {
        OvershootInterpolator inter = pool.obtain();
        inter.factor = factor;
        inter.doubledFactor = factor * 2;
        return inter;
    }

    /**
     * Gets a new {@link BounceInterpolator} from a maintained pool of {@link com.desitum.wallDefender.libraries.interpolation.Interpolator}s.
     * <p/>
     * The initial factor is set to <code>{@value BounceInterpolator#DEFAULT_FACTOR}</code>.
     *
     * @return the obtained {@link BounceInterpolator}
     */
    public static OvershootInterpolator $() {
        return $(DEFAULT_FACTOR);
    }

    @Override
    public void finished() {
        pool.free(this);
    }

    public float getInterpolation(float t) {
        t -= 1.0f;
        return t * t * ((factor + 1) * t + factor) + 1.0f;
    }

    @Override
    public com.desitum.wallDefender.libraries.interpolation.Interpolator copy() {
        return $(factor);
    }
}
