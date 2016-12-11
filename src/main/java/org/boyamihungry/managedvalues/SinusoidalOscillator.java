package org.boyamihungry.managedvalues;

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

    final String name;


    //  this is what we are doing: implement this value generator (the sinusoidal osc)
    //  and then fix the OscillatorUsingValueController to have an oscillator as a field
    //  and to use that field together with the range from the managed value to be able
    //  to return appropriate results from that class (OscillatorUsingValueController).
    //

    public SinusoidalOscillator(float frequency) {
        this (frequency + " Hz sinusoidal oscillator", frequency);
    }
    public SinusoidalOscillator(String name, float frequency) {
        this.name = name;
        if ( frequency != 0 ) {
            this.frequencyGetter = () -> {return frequency;};
        } else {
            throw new IllegalArgumentException("Can't have a zero frequency oscillator");
        }
    }
    public SinusoidalOscillator(@NonNull Callable<Float>getter) {
        this("dynamic frequency sinusoidal oscillator", getter);
    }
    public SinusoidalOscillator(String name, @NonNull Callable<Float>getter) {
        this.name = name;
        this.frequencyGetter = getter;
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
        //double currentTimeMillis = (double)System.currentTimeMillis();
 //herehere this seems to be broken
//        long time = System.currentTimeMillis();
//        StringBuilder builder =
//                new StringBuilder("performing on time: " + time )
//                        .append("\n\t time % freq = " + time % getFrequency() )
//                        .append("\n\t")
        double currentTimeMillis = (double)System.currentTimeMillis();
        double mod = currentTimeMillis % getFrequency();
        double modSlashFreq = mod / getFrequency();
        double modSlashFreqTimesTwoPi = modSlashFreq * TWO_PI;
        float ourReturn = sin((float)modSlashFreqTimesTwoPi);

        return sin((float)((((double)(System.currentTimeMillis()) % getFrequency()) / getFrequency()) * TWO_PI));

// here

//           double currentTimeMillis = (double)System.currentTimeMillis();
//        float frequency = getFrequency();
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


        //return sin( ((float)(currentTimeMillis % frequency) / frequency) * TWO_PI);
//        System.out.println( "return value: " + value);
//
        //return value;
        //return sin( (System.currentTimeMillis() % getFrequency()) / TWO_PI);
// to herw



    }

    @Override
    public String getName() {
        return name;
    }
}
