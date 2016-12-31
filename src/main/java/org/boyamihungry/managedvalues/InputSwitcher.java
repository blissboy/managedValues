package org.boyamihungry.managedvalues;

import org.boyamihungry.managedvalues.controllers.InputSink;

/**
 * Created by patwheaton on 12/27/16.
 */
public interface InputSwitcher {

    /**
     * Notifies the switcher that a sink is no longer taking input.
     * @param sink
     */
    public void closeSink(InputSink sink);

}
