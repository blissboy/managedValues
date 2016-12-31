package org.boyamihungry.managedvalues.valuegenerators;

import java.util.concurrent.Callable;

public class SinusoidalOscillatorBuilder<T extends Float> {
    private String name = "dynamic frequency sinusoidal oscillator";
    private Callable<Float> frequencyGetter;
    private Callable<Long> timecodeGetter = new Callable<Long>() {
        @Override
        public Long call() throws Exception {
            return System.currentTimeMillis();
        }
    };

    public SinusoidalOscillatorBuilder<T> withFrequency(float frequency) {
        if ( frequency != 0 ) {
            this.frequencyGetter = () -> {return frequency;};
            return this;
        } else {
            throw new IllegalArgumentException("Can't have a zero frequency oscillator");
        }
    }

    public SinusoidalOscillatorBuilder<T> withName(String name) {
        this.name = name;
        return this;
    }

    public SinusoidalOscillatorBuilder<T> withFrequencyGetter(Callable<Float> frequencyGetter) {
        this.frequencyGetter = frequencyGetter;
        return this;
    }

    public SinusoidalOscillatorBuilder<T> withTimecodeGetter(Callable<Long> timecodeGetter) {
        this.timecodeGetter = timecodeGetter;
        return this;
    }

    public SinusoidalOscillator<T> build() {
        return new SinusoidalOscillator(name, frequencyGetter, timecodeGetter);
    }
}