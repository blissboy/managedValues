package org.boyamihungry.managedvalues;

import processing.core.PApplet;
import processing.core.PVector;

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

    default Object getComputedValue(final Function<T, Object> computation) {
        return computation.apply(getValue());
    }

    default PVector draw(final PApplet app) {
        String text = "Oscilator with frequency " + getFrequency();
        app.text(text,0,0);
        return new PVector(app.textWidth(text), app.textAscent() + app.textDescent());
    }
}
