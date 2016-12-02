package org.boyamihungry.managedvalues;

import java.util.Collection;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface ManagedValue<T extends Number> {

    /**
     * Gets the key for this value.
     * @return
     */
    String getKey();

    /**
     * Gets the value.
     * @return
     */
    T getValue();


    /**
     * Gets the range for this value.
     * @return
     */
    Range<T> getRange();


    public interface Range<R extends Number> {
        R getMax();

        R getMin();

        R getDefault();
    }

    //List<T> getHistory();

    /**
     * Adds a value controller (after validation) for this value. This controller can then be used
     * (if validation is passed) by using {@link #setValueController(ValueController)}
     * @param potentialController
     * @return false if this controller cannot be used.
     *
     */
     boolean addValueController(ValueController<T> potentialController);

    /**
     * Set the value contoller that will control (set) this managed value. See
     * @param controller
     *
     * @throws IllegalAccessException if the controller is not allowed to control the value.
     */
     void setValueController(ValueController<T> controller) throws IllegalAccessException;

    /**
     * retrieves the current value controller.
     * @return
     */
    ValueController<T> getCurrentValueController();

    /**
     * Get the value controllers that can be used to control (set) this value.
     * @return
     */
    Collection<ValueController<T>> getAvailableValueControllers();




}
