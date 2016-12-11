package org.boyamihungry.managedvalues;

/**
 * Created by patwheaton on 12/9/16.
 */
public interface ValueGenerator<T extends Number> {
    T getValue();
}
