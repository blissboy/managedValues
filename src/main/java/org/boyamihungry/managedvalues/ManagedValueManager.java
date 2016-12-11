package org.boyamihungry.managedvalues;

import org.boyamihungry.managedvalues.controllers.ValueController;
import org.boyamihungry.managedvalues.values.BaseManagedValue;
import org.boyamihungry.managedvalues.values.ManagedValue;
import processing.core.PApplet;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface ManagedValueManager {

    Map<String, ManagedValue> values = new HashMap<>();
    HashMap<Type, Set<ValueController<? extends Number>>> mappp = new HashMap<>();


    /**
     * Retrieves the value from a managed value.
     * @param valueKey
     * @param <T>
     * @return
     */
    default <T> T getValue(String valueKey) throws NoSuchManagedValueException {
        return (T)getManagedValue(valueKey).getValue();
    }

    /**
     * Retrieves the actual managed value instance (instead of the value).
     * @param valueKey
     * @return
     */
    default <T extends Number> ManagedValue<T> getManagedValue(String valueKey) {
        if ( values.containsKey(valueKey)) {
            return values.get(valueKey);
        } else {
            throw new NoSuchManagedValueException("No value with the key " + valueKey + " exists.");
        }
    }

    default List<ManagedValue> getManagedValues() {
        return Collections.unmodifiableList(values.values().stream().collect(Collectors.toList()));
    }

    default <T extends Number> ManagedValue<T> createManagedValue(String key, T min, T max, T defaultValue, PApplet app) {
        ManagedValue<T> value = new BaseManagedValue<T>(key, new BaseManagedValue.Range<T>(min, max, defaultValue), app);
        values.put(key, value);
        return value;
    }

    default <T extends Number> void addValueController(ValueController<T> controller,
                                                       Class<T> controllerClass) {
        if ( validateController(controller)) {
            mappp.get(controllerClass).add(controller);
        } else {
            throw new ManagedValueManager.InvalidControllerException("Controller was invalid: " + controller);
        }
    }

    default <T extends Number> void setValueControllerForManagedValue(String mvKey, ValueController<T> controller) throws NoSuchManagedValueException, IllegalAccessException {
        values.get(mvKey).setValueController(controller);
    }


    public class InvalidControllerException extends RuntimeException {
        public InvalidControllerException(String message) {
            super(message);
        }
    }

    public class NoSuchManagedValueException extends RuntimeException {
        public NoSuchManagedValueException(String message) {
            super(message);
        }
    }

    /* below are things that will normally be overridden */

    public String getName();

    <T extends Number> boolean validateController(ValueController<T> controller);
}
