package org.boyamihungry.managedvalues.valuegenerators;

/**
 * Created by patwheaton on 12/9/16.
 */
public interface Oscillator<T extends Number> extends ValueGenerator<T> {

    static final float DEFAULT_FREQUENCY = 60000f;
    public String getDescription();
    public float getFrequency();
    public String getName();
    public T getValue();
}
