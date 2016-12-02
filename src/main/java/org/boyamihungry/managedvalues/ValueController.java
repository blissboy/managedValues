package org.boyamihungry.managedvalues;

import processing.core.PApplet;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface ValueController<T extends Number> {

    T getValue();

    default void draw(PApplet app) {
        // noop for default impl
    }

}
