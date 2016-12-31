package org.boyamihungry.managedvalues.controllers;

/**
 * Created by patwheaton on 12/27/16.
 */
public interface InputSink {

    /**
     * Method to turn off input to this controller, likely because some other controller wants to accept input as well.
     * @return true if this sink is now disabled, false if not acceptable.
     */
    public boolean disableSinkIfAcceptable();

}
