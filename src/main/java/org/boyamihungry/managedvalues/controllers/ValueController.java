package org.boyamihungry.managedvalues.controllers;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface ValueController<T extends Number> {

    T getValue();

    default PVector draw(final PApplet app, PVector origin) {
        // noop for default impl
        return new PVector(0,0);
    }

}
