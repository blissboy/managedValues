package org.boyamihungry.managedvalues;

import controlP5.Controller;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface CP5Presenter<T> extends ValuePresenter {

    Controller<T> getControl();
}
