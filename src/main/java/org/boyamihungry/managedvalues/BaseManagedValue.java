package org.boyamihungry.managedvalues;

import lombok.NonNull;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by patwheaton on 10/9/16.
 */
public  class BaseManagedValue<T extends Number> implements ManagedValue<T> {

    private final String key;
    private final Range<T> range;
    private final ManagedValueControlPanel controlPanel;

    public BaseManagedValue( @NonNull String key, @NonNull Range<T> range, PApplet app) {
        this.key = key;
        this.range = range;
        this.controlPanel = new BaseManagedValueControlPanel<T>(app, this);
    }

    public BaseManagedValue(@NonNull  String key, @NonNull Range<T> range, ManagedValueControlPanel controlPanel) {
        this.key = key;
        this.range = range;
        this.controlPanel = controlPanel;
    }


    /**
     * Gets the key for this value.
     *
     * @return
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * Gets the range for this value.
     *
     * @return
     */
    @Override
    public Range<T> getRange() {
        return range;
    }

    private Set<ValueController<T>> possibleControllers = new HashSet<>();
    ValueController<T> currentController = new ValueController<T>() {
        @Override
        public T getValue() {
            return getRange().getDefault();
        }
    };

    /**
     * Adds a value controller (after validation) for this value. This controller can then be used
     * (if validation is passed) by using {@link #setValueController(ValueController)}
     *
     * @param potentialController
     * @return false if this controller cannot be used.
     */
    @Override
    public boolean addValueController(@NonNull ValueController<T> potentialController) {
        if ( validateController(potentialController)) {
            possibleControllers.add(potentialController);
            return true;
        } else {
            return false;
        }
    }

    boolean validateController(ValueController<T> controller) {
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
    public ValueController<T> getCurrentValueController() {
        return currentController;
    }

    /**
     * Get the value controllers that can be used to control (set) this value.
     *
     * @return
     */
    @Override
    public Collection<ValueController<T>> getAvailableValueControllers() {
        return Collections.unmodifiableSet(possibleControllers);
    }

    @Override
    public T getValue() {
        return currentController.getValue();
    }

    @Override
    public PVector drawControlPanel() {
        return this.controlPanel.draw(new PVector(0,0), new PVector(0,0), new PVector(0,0));
    }

    public static class Range <T extends Number> implements ManagedValue.Range<T> {

        final T max;
        final T min;
        final T defaultValue;

        public Range(@NonNull T min, @NonNull T max, @NonNull T defaultValue) {

            Number minn = min;
            Number maxx = max;
            Number defaultt = defaultValue;

            if (minn.doubleValue() < maxx.doubleValue() && defaultt.doubleValue() <= maxx.doubleValue() && defaultt.doubleValue() >= minn.doubleValue()) {
                this.max = max;
                this.min = min;
                this.defaultValue = defaultValue;
            } else {
                throw new IllegalArgumentException("rule violated: min < defaultValue < max");
            }
        }

        @Override
        public T getMax() {
            return max;
        }

        @Override
        public T getMin() {
            return min;
        }

        @Override
        public T getDefault() {
            return defaultValue;
        }

        @Override
        public String toString() {
            return "FloatRange{" +
                    "max=" + max +
                    ", min=" + min +
                    ", defaultValue=" + defaultValue +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseManagedValue<?> that = (BaseManagedValue<?>) o;

        if (!key.equals(that.key)) return false;
        if (!range.equals(that.range)) return false;
        if (!controlPanel.equals(that.controlPanel)) return false;
        if (possibleControllers != null ? !possibleControllers.equals(that.possibleControllers) : that.possibleControllers != null)
            return false;
        return currentController != null ? currentController.equals(that.currentController) : that.currentController == null;
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + range.hashCode();
        result = 31 * result + controlPanel.hashCode();
        result = 31 * result + (possibleControllers != null ? possibleControllers.hashCode() : 0);
        result = 31 * result + (currentController != null ? currentController.hashCode() : 0);
        return result;
    }
}
