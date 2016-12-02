package org.boyamihungry.managedvalues;

import processing.core.PVector;

import java.util.Collection;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface ManagedView  {

    /**
     * Renders the view.
     * @param origin
     * @param size
     */
    void show(PVector origin, PVector size);

    /**
     * Adds a value to be displayed in the view with the first presenter for the class of the value.
     * @param value
     */
    void addManagedValue(ManagedValue value);

    /**
     * Removes a value from being displayed in the view.
     * @param value
     */
    void removeManagedValue(ManagedValue value);

    /**
     * Retrieves a list of all the managed values in the view.
     * @return
     */
    Collection<ManagedValue> getManagedValues();

    /**
     * Sets the class to use as the presenter for a value
     * @param value
     * @param presenter
     */
    <T extends Number> void setPresenterForValue(ManagedValue<T> value, ValuePresenter presenter, boolean makeItTheOnlyPresenter);

    /**
     * Convienience method to sets the class to use as the presenter for a value, from the key for the value.
     * @param valueKey
     * @param presenter
     */
    void setPresenterForValue(String valueKey, ValuePresenter presenter, boolean makeItTheOnlyPresenter);


    Collection<Class> getAvailablePresenters(Class clazz);
    Collection<Class> getAvailablePresenters(ManagedValue value);  //convenience method

    Collection<ValuePresenter> getPresenterForValue(ManagedValue value);

    String getName();
}
