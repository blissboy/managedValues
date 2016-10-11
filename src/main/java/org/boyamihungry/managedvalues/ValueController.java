package org.boyamihungry.managedvalues;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface ValueController<T> {

    void setValue(T value);

    public static ValueController<?> NOOP_CONTROLLER = new ValueController() {
        @Override
        public void setValue(Object value) {
            // noop
        }
    };
}
