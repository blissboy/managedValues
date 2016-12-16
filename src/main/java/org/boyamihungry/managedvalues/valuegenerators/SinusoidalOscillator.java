package org.boyamihungry.managedvalues.valuegenerators;

import lombok.NonNull;

import java.util.concurrent.Callable;

import static processing.core.PApplet.sin;
import static processing.core.PConstants.TWO_PI;

/**
 * Created by patwheaton on 9/25/16.
 */
public class SinusoidalOscillator<T extends Float> implements Oscillator {

    final String description = "A sinusoidal oscillator.";

    // doing this so we can create oscillators who's frequency is controlled by another entity.
    final Callable<Float> frequencyGetter;

    // this allows us to have both time and frame based oscillators. This callable gets the number of "time" units
    // elapsed since the oscillator was started.
    final Callable<Long> timecodeGetter;

    final String name;

    public SinusoidalOscillator(String name,
                                @NonNull Callable<Float>frequencyGetter,
                                @NonNull Callable<Long> timecodeGetter) {
        this.name = name;
        this.frequencyGetter = frequencyGetter;
        this.timecodeGetter = timecodeGetter;
    }


    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public float getFrequency() {
        try {
            return frequencyGetter.call();
        } catch (Exception e) {
            //TODO: logging
            e.printStackTrace();
            return 0f;
        }
    }

    @Override
    public Float getValue() {
        try {
            return sin((float) ((((double) timecodeGetter.call() % getFrequency()) / getFrequency()) * TWO_PI));
        } catch (Exception e) {
            return 0f;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
