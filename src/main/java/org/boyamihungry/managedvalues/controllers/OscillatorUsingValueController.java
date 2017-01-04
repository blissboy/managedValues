package org.boyamihungry.managedvalues.controllers;

import org.boyamihungry.managedvalues.ProcessingUtilities;
import org.boyamihungry.managedvalues.valuegenerators.Oscillator;
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

    default PVector draw(final PApplet app, PVector origin) {
        String text = getOscillator().getName() + " using controller";
        PVector drawAt = origin.copy();
        drawAt.add(0, ProcessingUtilities.drawText(app, text, drawAt).y);
        drawAt.add(0, ProcessingUtilities.drawText(app, "current value: " + this.getValue(), drawAt ).y);
        return drawAt.sub(origin);
    }
}
