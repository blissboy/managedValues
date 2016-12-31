package org.boyamihungry.managedvalues;

import processing.core.PApplet;

/**
 * Created by patwheaton on 12/27/16.
 */
public class ProcessingUtilities {


    static boolean mouseOverCircle(PApplet app, int x, int y, float diameter) {
      return (app.dist(app.mouseX, app.mouseY, x, y) < diameter*0.5);
    }

    /**
     *
     * @param app the processing PApplet that this is running within
     * @param x x coordinate of the top left corner of the rectangle
     * @param y y coordinate of the top left corner of the rectangle
     * @param width
     * @param height
     * @return
     */
    static boolean mouseOverRect(PApplet app, int x, int y, int width, int height) {
      return (app.mouseX >= x && app.mouseX <= x+width && app.mouseY >= y && app.mouseY <= y+height);
    }
}
