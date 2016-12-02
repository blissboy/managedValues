package org.boyamihungry.managedvalues;

import processing.core.PVector;

/**
 * Created by patwheaton on 10/9/16.
 */
public interface ValuePresenter {

    static String ID = "VALUE_PRESENTER";


    /**
     * Presents a value at the
     * @return
     * @param origin
     * @param minimumSize
     * @param maximumSize
     */
    PVector presentValue(PVector origin, PVector minimumSize, PVector maximumSize);


}
