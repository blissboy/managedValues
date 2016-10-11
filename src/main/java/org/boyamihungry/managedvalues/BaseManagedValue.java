package org.boyamihungry.managedvalues;

import lombok.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by patwheaton on 10/9/16.
 */
public abstract class BaseManagedValue<T> implements ManagedValue<T> {

    private Set<ValueController<T>> possibleControllers = new HashSet<>();
    ValueController<T> currentController = (ValueController<T>)ValueController.NOOP_CONTROLLER;

    /**
     * Adds a value controller (after validation) for this value. This controller can then be used
     * (if validation is passed) by using {@link #setValueController(ValueController)}
     *
     * @param potentialController
     * @return false if this controller cannot be used.
     */
    @Override
    public boolean addValueController(ValueController<T> potentialController) {
        possibleControllers.add(potentialController);
        return true;
    }

    /**
     * Set the value contoller that will control (set) this managed value. Note that this method will call
     * {@link #addValueController(ValueController)} if the passed controller is not already in the list of controllers.
     *
     * @param controller
     * @throws IllegalAccessException if the controller is not allowed to control the value.
     */
    @Override
    public void setValueController(@NonNull ValueController<T> controller) throws IllegalAccessException {
        if ( possibleControllers.contains(controller)) {
            currentController = controller;
        } else {
            if ( addValueController(controller)) {
                currentController = controller;
            } else {
                throw new IllegalAccessException("The controller " + controller + " is not a valid controller for " + this.toString());
            }
        }
    }

    /**
     * retrieves the current value controller.
     *
     * @return
     */
    @Override
    public ValueController getCurrentValueController() {
        return currentController;
    }

    /**
     * Get the value controllers that can be used to control (set) this value.
     *
     * @return
     */
    @Override
    public Collection<ValueController> getAvailableValueControllers() {
        return Collections.unmodifiableSet(possibleControllers);
    }
}
