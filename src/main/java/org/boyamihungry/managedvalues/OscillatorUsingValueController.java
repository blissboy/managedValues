package org.boyamihungry.managedvalues;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.function.Function;

/**
 * Created by patwheaton on 9/24/16.
 */
public interface OscillatorUsingValueController<T extends Number> extends ValueController<T> {

    public Oscillator<? extends Number> getOscillator();

    /**
     * Gives a value between the min and max of the range of the managed value.
     * @return
     */
    public T getValue();

    default Object getComputedValue(final Function<T, Object> computation) {
        return computation.apply(getValue());
    }

    default PVector draw(final PApplet app) {
        float lineHeight = (app.textAscent() + app.textDescent()) * 1.05f;
        String text = "Oscilator with frequency " + getOscillator().getFrequency();

        app.text(text,0,0);
        app.text("current value: " + this.getValue(), 0, lineHeight);
        return new PVector(app.textWidth(text), 2f * lineHeight);
    }
}