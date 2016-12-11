package org.boyamihungry.managedvalues.valuegenerators;

/**
 * Created by patwheaton on 12/9/16.
 */
public interface ValueGenerator<T extends Number> {
    T getValue();
}
