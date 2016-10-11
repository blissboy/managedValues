package org.boyamihungry.managedvalues;

import java.util.List;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface ManagedView  {

    /**
     * Renders the view.
     */
    void show();

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
    List<ManagedValue> getManagedValues();

    /**
     * Sets the class to use as the presenter for this
     * @param value
     * @param presenter
     */
    void setPresenterForValue(ManagedValue value, ValuePresenter presenter);
    List<ValuePresenter> getAvailablePresenters(Class clazz);
    List<ValuePresenter> getAvailablePresenters(ManagedValue value);  //convenience method

    ValuePresenter getPresenterForValue(ManagedValue value);


}
