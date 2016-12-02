package org.boyamihungry.managedvalues;

import processing.core.PApplet;

import java.util.function.Function;

/**
 * Created by patwheaton on 9/24/16.
 */
public interface Oscillator<T extends Number> extends ValueController<T> {

    static final float DEFAULT_FREQUENCY = 60000f;
    public String getDescription();

    public float getFrequency();

    /**
     * Gives a value between -1 and 1.
     * @return
     */
    public T getValue();

    default Object getComputedValue(Function<T, Object> computation) {
        return computation.apply(getValue());
    }

    default void draw(PApplet app) {
        app.text("Oscilator with frequency " + getFrequency(),0,0);
    }
}
