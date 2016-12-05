package org.boyamihungry.managedvalues;

import processing.core.PVector;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface ManagedValueControlPanel {

    /**
     * Presents a value at the
     * @return
     * @param origin
     * @param minimumSize
     * @param maximumSize
     */
    PVector draw(PVector origin, PVector minimumSize, PVector maximumSize);


}
