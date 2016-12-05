package org.boyamihungry.managedvalues;

import controlP5.Controller;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface CP5Controller<T> extends ValueController {

    Controller<T> getControl();
}
