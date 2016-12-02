package org.boyamihungry.managedvalues;

import lombok.NonNull;

import java.util.Optional;
import java.util.concurrent.Callable;

import static processing.core.PApplet.sin;
import static processing.core.PConstants.TWO_PI;

/**
 * Created by patwheaton on 9/25/16.
 */
public class SinusoidalOscillator<T extends Float> implements Oscillator {

    final String description = "A sinusoidal oscillator.";
    final Optional<Callable<Float>> frequencyGetter;
    final float frequency;

    public SinusoidalOscillator(float frequency) {
        if ( frequency != 0 ) {
            this.frequency = frequency;
            this.frequencyGetter = Optional.empty();
        } else {
            throw new IllegalArgumentException("Can't have a zero frequency oscillator");
        }
    }

    public SinusoidalOscillator(@NonNull Callable<Float>getter) {
        frequencyGetter = Optional.of(getter);
        frequency = -1;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public float getFrequency() {
        try {
            return frequencyGetter.isPresent() ? frequencyGetter.get().call() : frequency;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Float getValue() {

        double currentTimeMillis = (double)System.currentTimeMillis();
        float frequency = getFrequency();
//        double mod = currentTimeMillis % frequency;
//        float modSlashFreq = ((Number)mod).floatValue() / frequency;
//        float modSlashFreqTimes2Pi = modSlashFreq * TWO_PI;
//        float sinAllThis = sin(modSlashFreqTimes2Pi);
//
//        System.out.println("\ncurrentTimeMillis: " + currentTimeMillis  + " \n" +
//                        " frequency: " + frequency  + " \n" +
//                        "mod: " + mod  + " \n" +
//                        "modSlashFreq: " + modSlashFreq  + " \n" +
//                        "modSlashFreqTimes2Pi: " + modSlashFreqTimes2Pi  + " \n" +
//                        "sinAllThis: " + sinAllThis);
//


        return sin( ((float)(currentTimeMillis % frequency) / frequency) * TWO_PI);
//        System.out.println( "return value: " + value);
//
        //return value;
        //return sin( (System.currentTimeMillis() % getFrequency()) / TWO_PI);
    }



}
